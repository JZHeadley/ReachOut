package com.jzheadley.reachout.models.dataobjects;

import android.location.Location;

import java.net.URL;

/**
 * Created by pjhud on 2/18/2017.
 */

public class Picture {
    private int pictureId;
    private String description;
    private double longitude;
    private double lattitude;
    private Object image; //TODO: How are we going to represent this?
    private URL imageUrl;


    public Picture(int pictureId, String description,
                   double longitude, double lattitude, URL imageUrl) {
        this.pictureId = pictureId;
        this.longitude = longitude;
        this.lattitude = lattitude;
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


    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }
}
