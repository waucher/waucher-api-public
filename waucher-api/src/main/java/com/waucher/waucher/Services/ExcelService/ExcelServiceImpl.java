package com.waucher.waucher.Services.ExcelService;

import com.waucher.waucher.DAL.Enums.IssueStatus;
import com.waucher.waucher.DAL.Repositories.IssueRepository;
import com.waucher.waucher.Services.ExcelService.Interfaces.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ExcelServiceImpl implements ExcelService {

    private IssueRepository issueRepo;

    public ExcelServiceImpl(IssueRepository issueRepo) {
        this.issueRepo = issueRepo;
    }

    public void createTable(){
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        var completedIssues = issueRepo.findAllByStatus(IssueStatus.Done);

        var headFont = wb.createFont();
        headFont.setBold(true);
        var headStyle = wb.createCellStyle();
        headStyle.setFont(headFont);
        Row head = sheet.createRow(0);
        head.setRowStyle(headStyle);
        head.createCell(0).setCellValue("Дата заявки");
        head.createCell(1).setCellValue("ФИО сотрудника");
        head.createCell(2).setCellValue("Сумма");
        head.createCell(3).setCellValue("Описание");

        for(var i = 0; i < completedIssues.size(); ++i){
            Row row = sheet.createRow(i + 1);

            var issue = completedIssues.get(i);
            var employee = issue.getEmployee();
            var employeeMiddleName = employee.getMiddlename() == null ? "" : employee.getMiddlename();
            var employeeFullName = String.format("%s %s %s", employee.getFirstname(), employeeMiddleName, employee.getLastname());
            row.createCell(0).setCellValue(issue.getCreatedAt());
            row.createCell(1).setCellValue(employeeFullName);
            row.createCell(2).setCellValue(issue.getAmount());
            row.createCell(3).setCellValue(issue.getDescription());
        }

        try (FileOutputStream out = new FileOutputStream("result.xls")) {
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
