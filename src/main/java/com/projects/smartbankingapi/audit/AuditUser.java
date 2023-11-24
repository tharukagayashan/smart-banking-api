package com.projects.smartbankingapi.audit;

public class AuditUser {
    private String username;

    public AuditUser(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
