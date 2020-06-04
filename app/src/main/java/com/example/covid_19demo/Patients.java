package com.example.covid_19demo;

public class Patients {
        String name;
    String phone;
    String join;
    String date;
    String month;
    String year;
    String res;
    String mail;

        public Patients(){

        }

    public Patients(String name, String phone, String res) {
        this.name = name;
        this.phone = phone;
        this.res = res;
    }

    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getJoin() {
            return join;
        }

        public void setJoin(String join) {
            this.join = join;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getRes() {
            return res;
        }

        public void setRes(String res) {
            this.res = res;
        }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
