package com.macinternetservices.sablebusinessdirectory;

public class WooModel {
    public String name, description, price, image, link;
    public static final int IMAGE_TYPE = 1;
    public int  type, rating, ratingCount;
    public String averageRating;

    public WooModel(int mtype, String mname, String mlink, String mrating, Integer ratingCount, String mdesc, String mprice, String mimage) {

        //String rating = Integer.toString(mratingCount);
       // doubleStars = Integer.parseInt(mstars);
        //float stars = Float.parseFloat(mstars);
        this.type = mtype;
        this.name = mname;
        this.link = mlink;
        this.description = mdesc;
        this.averageRating = mrating;
        this.ratingCount = ratingCount;
        this.price = mprice;
        this.image = mimage;
    }
}
