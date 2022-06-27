package com.ecloud.wallpic.datamodels;

import java.io.Serializable;
import java.util.List;

public class Tag implements Serializable {
    private int id;
    private String name;

    private List<PictureItem> photos;

    public List<PictureItem> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PictureItem> photos) {
        this.photos = photos;
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
}
