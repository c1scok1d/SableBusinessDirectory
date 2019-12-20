package com.sable.businesslistingapi;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class ListReviewModel extends ArrayList<Parcelable> implements Parcelable {
    public static final int IMAGE_TYPE = 1;
    public String longitude, status, category, ratingTitle, latitude, city, state, country, zipcode, phone, email, website, twitter, facebook, video,
            hours, content, image, logo, timestamp, link, isOpen;
    public int ratingNumber, id, ratingCount;
    //public Double latitude, longitude;



    public ListReviewModel(int imageType,
                           Integer id,
                           String content,
                           String link,
                           String status,
                           String city,
                           String state,
                           String country,
                           String latitude,
                           String longitude,
                           String ratingTitle,
                           Integer ratingNumber/*,
                           ArrayList<String> image,
                           Integer rating*/
                           ){

        this.id = id;
        this.link = link;
        this.status = status;
        this.city = city;
        this.state = state;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ratingTitle = ratingTitle;
        this.ratingNumber = ratingNumber;
   }




    //write object values to parcel for storage
    public void writeToParcel(Parcel dest, int flags){
        //write all properties to the parcel
        dest.writeInt(id);
        dest.writeString(link);
        dest.writeString(status);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(country);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(ratingTitle);
        dest.writeInt(ratingNumber);
    }

    //constructor used for parcel
    public ListReviewModel(Parcel parcel){
        //read and set saved values from parcel
        id = parcel.readInt();
        link = parcel.readString();
        status = parcel.readString();
        city = parcel.readString();
        state = parcel.readString();
        country = parcel.readString();
        latitude = parcel.readString();
        longitude = parcel.readString();
        ratingTitle = parcel.readString();
        ratingNumber = parcel.readInt();
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

