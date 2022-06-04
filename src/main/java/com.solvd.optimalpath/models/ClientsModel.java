package com.solvd.optimalpath.models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ClientsModel {
    private int id;
    private String firstName;
    private String lastName;
    private String passportNum;
    private String phoneNum;
   // private List<Tickets> tickets = new LinkedList<>();

public ClientsModel() {

}

    public ClientsModel(int id, String firstName, String lastName, String passportNum, String phoneNum) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNum = passportNum;
        this.phoneNum = phoneNum;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "ClientsModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passportNum='" + passportNum + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}

