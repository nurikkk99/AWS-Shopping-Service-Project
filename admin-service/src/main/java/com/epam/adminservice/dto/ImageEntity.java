package com.epam.adminservice.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "s3_uri")
    private String imageURI;

    @ManyToOne
    @JoinColumn(name = "good_id")
    public GoodEntity goodEntity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public GoodEntity getGoodEntity() {
        return goodEntity;
    }

    public void setGoodEntity(GoodEntity goodEntity) {
        this.goodEntity = goodEntity;
    }
}
