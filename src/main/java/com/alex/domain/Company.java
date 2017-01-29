package com.alex.domain;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Temporal(TemporalType.DATE)
    @Column(name = "last_sent")
    private Date lastTimeSent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LongProperty idProperty(){
        return new SimpleLongProperty(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StringProperty nameProperty(){
        return new SimpleStringProperty(name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StringProperty emailProperty(){
        return new SimpleStringProperty(email);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public StringProperty phoneProperty(){
        return new SimpleStringProperty(phone);
    }

    public Date getLastTimeSent() {
        return lastTimeSent;
    }

    public void setLastTimeSent(Date lastTimeSent) {
        this.lastTimeSent = lastTimeSent;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", lastTimeSent=" + lastTimeSent +
                '}';
    }
}
