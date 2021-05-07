package com.vimal.gpslab;

public class Details {
    String name,mobile,address,gender,licno;

    public Details(String name, String mobile, String address, String gender, String licno) {
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.gender = gender;
        this.licno = licno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLicno(String licno) {
        this.licno = licno;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getLicno() {
        return licno;
    }
}
