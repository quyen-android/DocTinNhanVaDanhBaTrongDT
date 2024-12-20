package com.example.doctinnhanvadanhbatrongdt.model;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name;
    private String Phone;

    public Contact(String phone, String name) {
        Phone = phone;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    @Override
    public String toString() {
        return "Tên:'" + name + '\'' + ", Phone='" + this.Phone;
    }
}
