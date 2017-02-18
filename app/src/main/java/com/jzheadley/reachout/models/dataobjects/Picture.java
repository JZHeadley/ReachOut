package com.jzheadley.reachout.models.dataobjects;

import android.location.Location;

import java.net.URL;

/**
 * Created by pjhud on 2/18/2017.
 */

public class Picture {
    private int pictureId;
    private String description;
    private Location location;
    private Object image; //TODO: How are we going to represent this?
    private URL imageUrl;


    public Picture(int pictureId, String description,
                   Location location, URL imageUrl) {
        this.pictureId = pictureId;
        this.location = location;
        this.image = null;
        this.imageUrl = imageUrl;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }
}
