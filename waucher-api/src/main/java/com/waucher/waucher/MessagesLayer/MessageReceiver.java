package com.waucher.waucher.MessagesLayer;

import com.waucher.waucher.DAL.Enums.IssueStatus;
import com.waucher.waucher.DAL.Models.Employee;
import com.waucher.waucher.DAL.Models.Issue;
import com.waucher.waucher.DAL.Repositories.EmployeeRepository;
import com.waucher.waucher.DAL.Repositories.IssueRepository;
import com.waucher.waucher.DAL.Repositories.ValidOrganizationRepository;
import com.waucher.waucher.Services.IssueProcessing.Interfaces.IssueProcessingService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.UUID;

@Component
public class MessageReceiver {

    private final ValidOrganizationRepository validOrganizationRepo;

    private final IssueRepository issueRepo;

    private final EmployeeRepository employeeRepo;

    private final IssueProcessingService issueProcessingService;

    @Autowired
    public MessageReceiver(ValidOrganizationRepository validOrganizationRepo, IssueRepository issueRepo, EmployeeRepository employeeRepo, IssueProcessingService issueProcessingService) {
        this.validOrganizationRepo = validOrganizationRepo;
        this.issueRepo = issueRepo;
        this.employeeRepo = employeeRepo;
        this.issueProcessingService = issueProcessingService;
    }

    @JmsListener(destination = "waucherApi")
    public void processFnsResultMessage(String content) {
        var result = new JSONObject(content);

        UUID userUUID;

        try{
            userUUID = UUID.fromString(result.getString("userUUID"));
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
        var employee = employeeRepo.findById(userUUID);

        UUID issueUUID;

        try{
            issueUUID = UUID.fromString(result.getString("issueUUID"));
        } catch (Exception e){
            e.printStackTrace();
            return;
        }

        Issue issue;

        try{
            issue = issueRepo.findById(issueUUID).get();
        } catch (Exception e){
            e.printStackTrace();
            return;
        }

        String ticketId;
        try{
            ticketId = result.getString("ticketId");
        } catch (Exception e){
            e.printStackTrace();
            issueProcessingService.declineIssue(issueUUID,"Ошибка обработки чека");
            return;
        }

        if(ticketId.equals("erorr")){
            issueProcessingService.declineIssue(issueUUID,"Отправлен несуществующий чек");
            return;
        }

        if(issueRepo.findByTicketId(ticketId).isPresent()){
            issueProcessingService.declineIssue(issueUUID,"Чек уже существует в системе и был обработан");
            return;
        }

        var receipt = new JSONObject(result.getString("receipt"));

        if(validOrganizationRepo.findByInn(receipt.getJSONObject("organization").getString("inn")).isEmpty()){
            issueProcessingService.declineIssue(issueUUID, "Чек из организации не входящей в список компенсируемых");
            return;
        }

        var ticketData = receipt.getJSONObject("ticket").getJSONObject("document").getJSONObject("receipt");
        var amount = (double)ticketData.getInt("ecashTotalSum") / 100;
        issue.setAmount(amount);

        var description = new StringBuilder();

        var buyDate = new Date(ticketData.getLong("dateTime"));
        description.append(String.format("Дата покупки: %s\nПокупки:\n", buyDate));

        var items = ticketData.getJSONArray("items");

        for (var i = 0; i < items.length(); ++i){
            var item = items.getJSONObject(i);
            var amountItem =  item.getDouble("price") / 100;
            description.append(String.format("%s: %s;\n", item.getString("name"), amountItem));
        }

        issue.setDescription(description.toString());
        issue.setCheckValid(true);
        issue.setTicketId(ticketId);
        issueRepo.save(issue);
        issueProcessingService.completeIssue(issueUUID);
        System.out.println("good");
    }
}
