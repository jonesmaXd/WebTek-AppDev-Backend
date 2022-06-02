package no.ntnu.WebTek.AppDevbackend.model;

import javax.persistence.*;

@Entity(name = "product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String duration;
    private int price;
    private String timeOfDay;
    private String language;
    private String groupSize;
    private String Date;

    public Product(String title, String description, String duration, int price, String timeOfDay, String language, String groupSize, String date) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.price = price;
        this.timeOfDay = timeOfDay;
        this.language = language;
        this.groupSize = groupSize;
        this.Date = date;
    }

    public Product(String title, String description, String duration, int price) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.price = price;
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(String groupSize) {
        this.groupSize = groupSize;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
