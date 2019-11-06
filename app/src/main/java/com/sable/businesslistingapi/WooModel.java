package com.sable.businesslistingapi;

public class WooModel {
    public String name, description, price, image, ratingCount;
    public static final int IMAGE_TYPE = 1;
    public int  type;
    float averageRating;

    public WooModel(int mtype, String mname, String mdesc, String mstars, int mratingCount, String mprice, String mimage) {

        String rating = Integer.toString(mratingCount);
       // doubleStars = Integer.parseInt(mstars);
        float stars = Float.parseFloat(mstars);
        this.type = mtype;
        this.name = mname;
        this.description = mdesc;
        this.averageRating = stars;
        this.ratingCount = rating;
        this.price = mprice;
        this.image = mimage;
    }
}
