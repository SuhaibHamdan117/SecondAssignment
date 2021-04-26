package edu.cs.birzeit.secondassignment;

public class personal {

    private String first;
    private String last;
    private String email;
    private String phone;
    private String address;
    private String spin;
    private String gender;

    public personal(){}

    public personal(String first, String last, String email,String gender, String phone, String address, String spin) {
        this.first = first;
        this.last = last;
        this.email = email;
        this.gender=gender;
        this.phone = phone;
        this.address = address;
        this.spin = spin;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpin() {
        return spin;
    }

    public void setSpin(String spin) {
        this.spin = spin;
    }




}
