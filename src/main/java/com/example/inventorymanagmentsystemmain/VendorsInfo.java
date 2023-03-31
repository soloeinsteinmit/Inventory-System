package com.example.inventorymanagmentsystemmain;

public class VendorsInfo {
    private int number;
    private String name;
    private String vendor_id;
    private String status;
    private String gender;
    private String date_registered;
    private String telephone_number;



    public VendorsInfo(int number, String name, String vendor_id, String status, String gender,
                       String date_registered, String telephone_number){
        this.number = number;
        this.name = name;
        this.vendor_id = vendor_id;
        this.status = status;
        this.gender =gender;
        this.date_registered = date_registered;
        this.telephone_number = telephone_number;
    }

    public VendorsInfo(int number, String name, String status, String gender,
                       String date_registered, String telephone_number) {
        this.number = number;
        this.name = name;
        this.status = status;
        this.gender = gender;
        this.date_registered = date_registered;
        this.telephone_number = telephone_number;
    }

    public VendorsInfo(String name, String status, String gender,
                       String telephone_number, String date_registered){
        this.name = name;
        this.status = status;
        this.gender =gender;
        this.date_registered = date_registered;
        this.telephone_number = telephone_number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate_registered() {
        return date_registered;
    }

    public void setDate_registered(String date_registered) {
        this.date_registered = date_registered;
    }

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }

}
