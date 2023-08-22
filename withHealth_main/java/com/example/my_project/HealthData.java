package com.example.my_project;


public class HealthData {
    //정보 저장하는 클래스
    //정보 : 할 운동, 중량
    private String title, weight, set;

    public HealthData(){}

    public HealthData(String weight){
        this.weight = weight;
    }

    public HealthData(String weight, String set){
        this.weight = weight;
        this.set = set;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    @Override
    public String toString() {
        return "User{" +
                "Title='" + title + '\'' +
                ", set='" + set + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}