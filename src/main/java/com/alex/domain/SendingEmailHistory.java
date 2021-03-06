package com.alex.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sending_email_history")
public class SendingEmailHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "company_id")
    private long companyId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sent_date", columnDefinition = "DATETIME")
    private Date sentDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    @Override
    public String toString() {
        return "SendingEmailHistory{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", sentDate=" + sentDate +
                '}';
    }
}
