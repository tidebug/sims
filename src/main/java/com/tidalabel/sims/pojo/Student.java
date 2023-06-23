package com.tidalabel.sims.pojo;

public class Student {
    private String stu_ref;
    private String stu_name;
    private String stu_age;
    private String stu_id;
    private String stu_sex;
    private String stu_phonenumber;
    private String stu_email;
    private String stu_passwd;

    public Student() {
    }

    public Student(String stu_ref, String stu_name, String stu_age, String stu_id, String stu_sex, String stu_phonenumber, String stu_email, String stu_passwd) {
        this.stu_ref = stu_ref;
        this.stu_name = stu_name;
        this.stu_age = stu_age;
        this.stu_id = stu_id;
        this.stu_sex = stu_sex;
        this.stu_phonenumber = stu_phonenumber;
        this.stu_email = stu_email;
        this.stu_passwd = stu_passwd;
    }

    public String getStu_ref() {
        return stu_ref;
    }

    public void setStu_ref(String stu_ref) {
        this.stu_ref = stu_ref;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_age() {
        return stu_age;
    }

    public void setStu_age(String stu_age) {
        this.stu_age = stu_age;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_sex() {
        return stu_sex;
    }

    public void setStu_sex(String stu_sex) {
        this.stu_sex = stu_sex;
    }

    public String getStu_phonenumber() {
        return stu_phonenumber;
    }

    public void setStu_phonenumber(String stu_phonenumber) {
        this.stu_phonenumber = stu_phonenumber;
    }

    public String getStu_email() {
        return stu_email;
    }

    public void setStu_email(String stu_email) {
        this.stu_email = stu_email;
    }

    public String getStu_passwd() {
        return stu_passwd;
    }

    public void setStu_passwd(String stu_passwd) {
        this.stu_passwd = stu_passwd;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stu_ref='" + stu_ref + '\'' +
                ", stu_name='" + stu_name + '\'' +
                ", stu_age='" + stu_age + '\'' +
                ", stu_id='" + stu_id + '\'' +
                ", stu_sex='" + stu_sex + '\'' +
                ", stu_phonenumber='" + stu_phonenumber + '\'' +
                ", stu_email='" + stu_email + '\'' +
                ", stu_passwd='" + stu_passwd + '\'' +
                '}';
    }
}
