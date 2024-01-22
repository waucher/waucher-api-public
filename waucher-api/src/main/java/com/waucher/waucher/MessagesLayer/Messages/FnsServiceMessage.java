package com.waucher.waucher.MessagesLayer.Messages;

import java.util.UUID;

public class FnsServiceMessage {
    private UUID userUUID;

    private UUID issueUUID;

    private String qrData;

    public FnsServiceMessage(UUID userUUID, UUID issueUUID, String qrData) {
        this.userUUID = userUUID;
        this.issueUUID = issueUUID;
        this.qrData = qrData;
    }

    public String getQrData() {
        return qrData;
    }

    public void setQrData(String qrData) {
        this.qrData = qrData;
    }

    public UUID getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(UUID userUUID) {
        this.userUUID = userUUID;
    }

    public UUID getIssueUUID() {
        return issueUUID;
    }

    public void setIssueUUID(UUID issueUUID) {
        this.issueUUID = issueUUID;
    }
}
