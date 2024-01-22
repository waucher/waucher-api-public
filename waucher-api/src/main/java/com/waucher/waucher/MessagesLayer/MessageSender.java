package com.waucher.waucher.MessagesLayer;

import com.waucher.waucher.MessagesLayer.Messages.FnsServiceMessage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
    private final JmsTemplate jmsTemplate;

    @Autowired
    public MessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void SendNumberMessage(FnsServiceMessage message) {
        var messageBody = new JSONObject();
        messageBody.put("userUUID", message.getUserUUID());
        messageBody.put("issueUUID", message.getIssueUUID());
        messageBody.put("qrData", message.getQrData());
        jmsTemplate.convertAndSend("fnsconnect", messageBody.toString());
    }
}
