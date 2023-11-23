package com.projects.smartbankingapi.audit;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

public class AuditListener {

    @PrePersist
    public void setCreatedOn(AuditModel entity) {
        entity.setCreatedBy(entity.getCreatedBy());
        entity.setCreatedOn(new Date());
    }

    @PreUpdate
    public void setUpdatedOn(AuditModel entity) {
        entity.setUpdatedBy(entity.getUpdatedBy());
        entity.setUpdatedOn(new Date());
    }

}
