package com.luis.vacants.model;

import java.util.Date;

/**
 *
 * @author Luis
 */
public class Vacant {
    private int id;
    private String name;
    private Date date;
    private String description;
    private String detail;

    public Vacant(int id) {
        this.id = id;
        this.date = new Date();//Asiga por defecto la fecha del servidor
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date publicationDate) {
        this.date = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Vacant{" + "id=" + id + ", publicationDate=" + date + ", description=" + description + ", detail=" + detail + '}';
    }
    
}
