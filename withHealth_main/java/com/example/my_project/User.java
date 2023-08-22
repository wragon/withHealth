package com.example.my_project;

import java.text.DateFormat;

public class User {
    private String gid;
    private String Nickname;
    private String gender;
    private DateFormat birth;
    private int height;
    private int weight;
    private String Laptime;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public DateFormat getBirth() {
        return birth;
    }

    public void setBirth(DateFormat birth) {
        this.birth = birth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getLaptime() {
        return Laptime;
    }

    public void setLaptime(String laptime) {
        Laptime = laptime;
    }
}
