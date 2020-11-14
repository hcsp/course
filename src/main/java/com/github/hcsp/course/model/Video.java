package com.github.hcsp.course.model;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "video", schema = "public")
public class Video extends BaseEntity {
    private String name;
    private String description;
    private String url;
    private Course course;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
