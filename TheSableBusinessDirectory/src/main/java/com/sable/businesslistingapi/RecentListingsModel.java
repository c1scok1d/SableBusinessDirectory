package com.sable.businesslistingapi;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class RecentListingsModel extends ArrayList<Parcelable> implements Parcelable {
    public static final int IMAGE_TYPE = 1;
    public String title, status, category, featured_image, bldgno, street, city, state, country, zipcode, phone, email, website, twitter, facebook, video,
            hours, content, image, logo, timestamp, link, isOpen, reviews, userName, userEmail, userImage, userId;
    public int id, ratingCount;
    public Double latitude, longitude;
    Float rating;
    Boolean featured;



    public RecentListingsModel(int imageType,
                               Integer id,
                               String title,
                               String link,
                               String category,
                               Boolean featured,
                               String featuredImage,
                               Float rating,
                               Integer ratingCount,
                               String businessHours,
                               String isOpen,
                               String logo,
                               String content,
                               String image){

        this.id = id;
        this.title = title;
        this.link = link;
        this.category = category;
        this.featured = featured;
        this.featured_image = featuredImage;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.hours = businessHours;
        this.isOpen = isOpen;
        this.logo = logo;
        this.content = content;
        this.image = image;
    }




    //write object values to parcel for storage
    public void writeToParcel(Parcel dest, int flags){
        //write all properties to the parcel
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(link);
        dest.writeString(category);
        dest.writeBoolean(featured);
        dest.writeString(featured_image);
        dest.writeFloat(rating);
        dest.writeInt(ratingCount);
        dest.writeString(hours);
        dest.writeString(isOpen);
        dest.writeString(logo);
        dest.writeString(content);
        dest.writeString(image);
        dest.writeString(timestamp);


    }

    //constructor used for parcel
    public RecentListingsModel(Parcel parcel){
        //read and set saved values from parcel
        id = parcel.readInt();
        title = parcel.readString();
        link = parcel.readString();
        status = parcel.readString();
        category = parcel.readString();
        featured = parcel.readBoolean();
        featured_image = parcel.readString();
        rating = parcel.readFloat();
        ratingCount = parcel.readInt();
        hours = parcel.readString();
        isOpen = parcel.readString();
        reviews = parcel.readString();
        logo = parcel.readString();
        content = parcel.readString();
        image = parcel.readString();
        timestamp = parcel.readString();
    }

    //creator - used when un-parceling our parcle (creating the object)
    public static final Creator<RecentListingsModel> CREATOR = new Creator<RecentListingsModel>(){

        @Override
        public RecentListingsModel createFromParcel(Parcel parcel) {
            return new RecentListingsModel(parcel);
        }

        @Override
        public RecentListingsModel[] newArray(int size) {
            return new RecentListingsModel[0];
        }
    };

    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }
}

