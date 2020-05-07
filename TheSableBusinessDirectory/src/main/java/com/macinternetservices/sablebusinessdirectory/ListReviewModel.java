package com.macinternetservices.sablebusinessdirectory;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class ListReviewModel extends ArrayList<Parcelable> implements Parcelable {
    public static final int IMAGE_TYPE = 1;
    public String longitude, status, category, ratingTitle, latitude, city, state, country, zipcode, phone, email, website, twitter, facebook, video,
            hours, content, image, author, date, link, isOpen;
    public int ratingNumber, id, ratingCount;
    //public Double latitude, longitude;



    public ListReviewModel(int imageType,
                           Integer id,
                           String content,
                           String link,
                           String author,
                           String city,
                          // Integer ratings,
                           String state,
                           String country,
                           String latitude,
                           String longitude,
                           String ratingTitle,
                           Integer ratingNumber,
                           String date
                           ){

        this.id = id;
        this.link = link;
        this.author = author;
        //this.image = image;
        this.city = city;
        this.state = state;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ratingTitle = ratingTitle;
        this.ratingNumber = ratingNumber;
        this.content = content;
        this.date = date;
   }




    //write object values to parcel for storage
    public void writeToParcel(Parcel dest, int flags){
        //write all properties to the parcel
        dest.writeInt(id);
        dest.writeString(link);
        dest.writeString(author);
        dest.writeString(date);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(country);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(ratingTitle);
        dest.writeInt(ratingNumber);
        dest.writeString(content);
    }

    //constructor used for parcel
    public ListReviewModel(Parcel parcel){
        //read and set saved values from parcel
        id = parcel.readInt();
        link = parcel.readString();
        author = parcel.readString();
        date = parcel.readString();
        city = parcel.readString();
        state = parcel.readString();
        country = parcel.readString();
        latitude = parcel.readString();
        longitude = parcel.readString();
        ratingTitle = parcel.readString();
        ratingNumber = parcel.readInt();
        content = parcel.readString();
    }
    //creator - used when un-parceling our parcle (creating the object)
    public static final Creator<ListReviewModel> CREATOR = new Creator<ListReviewModel>(){

        @Override
        public ListReviewModel createFromParcel(Parcel parcel) {
            return new ListReviewModel(parcel);
        }

        @Override
        public ListReviewModel[] newArray(int size) {
            return new ListReviewModel[0];
        }
    };

    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }
}

