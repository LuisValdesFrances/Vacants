package com.luis.vacants.model;

import java.util.Date;

/**
 *
 * @author Luis
 */
public class Vacant {
    private int id;
    private Date publicationDate;
    private String description;
    private String detail;

    public Vacant(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
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
        return "Vacant{" + "id=" + id + ", publicationDate=" + publicationDate + ", description=" + description + ", detail=" + detail + '}';
    }
    
}
