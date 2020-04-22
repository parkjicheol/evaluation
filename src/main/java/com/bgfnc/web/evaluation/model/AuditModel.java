package com.bgfnc.web.evaluation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "register_date", "update_date" }, allowGetters = true)
public abstract class AuditModel implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "register_date", nullable = false, updatable = false)
    @CreatedDate
    private Calendar registerDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", nullable = false, updatable = false)
    @LastModifiedDate
    private Calendar updateDate;

    public AuditModel() {
        Calendar calendar = Calendar.getInstance();
        this.registerDate = calendar;
        this.updateDate = calendar;
    }
}
