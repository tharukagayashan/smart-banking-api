package com.projects.smartbankingapi.audit;

import com.projects.smartbankingapi.audit.AuditListener;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners({AuditListener.class})
public class AuditModel {

    @CreatedBy
    @Column(name = "CREATED_BY", updatable = false)
    private String createdBy;
    @LastModifiedBy
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @CreatedDate
    @Column(name = "CREATED_ON", updatable = false)
    private Date createdOn;
    @LastModifiedDate
    @Column(name = "UPDATED_ON")
    private Date updatedOn;
}
