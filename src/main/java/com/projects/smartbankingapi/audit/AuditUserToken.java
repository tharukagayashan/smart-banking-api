package com.projects.smartbankingapi.audit;

public class AuditUserToken {

    private String token;

    public AuditUserToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
