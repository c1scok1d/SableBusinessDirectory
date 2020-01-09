package com.sable.businesslistingapi;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class RecentReviewListingsModel extends ArrayList<Parcelable> implements Parcelable {
    public static final int IMAGE_TYPE = 1;
    public String title, status, category, featured_image, bldgno, street, city, state, country, zipcode, phone, email, website, twitter, facebook, video,
            hours, latitude, longitude, ratingTitle, content, image, logo, timestamp, authorName, link, isOpen, reviews, userName, userEmail, userImage, userId;
    public int id, ratingCount;
    //public Double latitude, longitude;
    Integer rating;
    Boolean featured;



    public RecentReviewListingsModel(int imageType,
                                     Integer id,
                                     String link,
                                     String authorName,
                                     Integer ratingNumber,
                                     String dateCreated,
                                     String image){

        this.id = id;
        this.title = content;
        this.link = link;
        this.authorName = authorName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ratingTitle = ratingTitle;
        this.rating = ratingNumber;
        this.timestamp = dateCreated;
        this.image = image;
    }




    //write object values to parcel for storage
    public void writeToParcel(Parcel dest, int flags){
        //write all properties to the parcel
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(link);
        dest.writeString(authorName);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(country);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(ratingTitle);
        dest.writeInt(rating);
        dest.writeString(timestamp);
        dest.writeString(image);
    }

    //constructor used for parcel
    public RecentReviewListingsModel(Parcel parcel){
        //read and set saved values from parcel
        id = parcel.readInt();
        title = parcel.readString();
        link = parcel.readString();
        authorName = parcel.readString();
        city = parcel.readString();
        state = parcel.readString();
        country = parcel.readString();
        latitude = parcel.readString();
        longitude = parcel.readString();
        ratingTitle = parcel.readString();
        rating = parcel.readInt();
        timestamp = parcel.readString();
        image = parcel.readString();

    }

    //creator - used when un-parceling our parcle (creating the object)
    public static final Creator<RecentReviewListingsModel> CREATOR = new Creator<RecentReviewListingsModel>(){

        @Override
        public RecentReviewListingsModel createFromParcel(Parcel parcel) {
            return new RecentReviewListingsModel(parcel);
        }

        @Override
        public RecentReviewListingsModel[] newArray(int size) {
            return new RecentReviewListingsModel[0];
        }
    };

    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }
}

