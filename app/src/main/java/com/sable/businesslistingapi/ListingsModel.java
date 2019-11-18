package com.sable.businesslistingapi;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class ListingsModel implements Parcelable {
    public static final int IMAGE_TYPE = 1;
    public String title, status, category, featured_image,
            bldgno, street, city, state, country, zipcode, phone, email, website, twitter, facebook, video,
            hours, isOpen, content, image, logo, timestamp, link, featured, ratingCount;
    public int rating, id;
    public Double latitude, longitude;



    public ListingsModel(int imageType, Integer id, String title, String link, String status,
                         String category, Boolean featured, String featuredImage, String bldgNo, String street,
                         String city, String state, String country, String zipcode, Double latitude, Double longitude,
                         Integer rating, Integer ratingCount, String phone, String email, String website, String twitter,
                         String facebook, String video, String businessHours, String commentStatus, String logo,
                         String content, String image){

        this.id = id;
        this.title = title;
        this.link = link;
        this.status = status;
        this.category = category;
        this.featured = featured.toString();
        this.featured_image = featuredImage;
        this.bldgno = bldgNo;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
        this.ratingCount = ratingCount.toString();
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.twitter = twitter;
        this.facebook = facebook;
        this.video = video;
        this.hours = businessHours;
        this.isOpen = commentStatus;
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
        dest.writeString(status);
        dest.writeString(category);
        dest.writeString(featured);
        dest.writeString(featured_image);
        dest.writeString(bldgno);
        dest.writeString(street);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(country);
        dest.writeString(zipcode);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeInt(rating);
        dest.writeString(ratingCount);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(website);
        dest.writeString(twitter);
        dest.writeString(facebook);
        dest.writeString(video);
        dest.writeString(hours);
        dest.writeString(isOpen);
        dest.writeString(logo);
        dest.writeString(content);
        dest.writeString(image);
        dest.writeString(timestamp);
    }

    //constructor used for parcel
    public ListingsModel(Parcel parcel){
        //read and set saved values from parcel
        id = parcel.readInt();
        title = parcel.readString();
        link = parcel.readString();
        status = parcel.readString();
        category = parcel.readString();
        featured = parcel.readString();
        featured_image = parcel.readString();
        bldgno = parcel.readString();
        street = parcel.readString();
        city = parcel.readString();
        state = parcel.readString();
        country = parcel.readString();
        zipcode = parcel.readString();
        latitude = parcel.readDouble();
        longitude = parcel.readDouble();
        rating = parcel.readInt();
        ratingCount = parcel.readString();
        phone = parcel.readString();
        email = parcel.readString();
        website = parcel.readString();
        twitter = parcel.readString();
        facebook = parcel.readString();
        video = parcel.readString();
        hours = parcel.readString();
        isOpen = parcel.readString();
        logo = parcel.readString();
        content = parcel.readString();
        image = parcel.readString();
        timestamp = parcel.readString();
    }

    //creator - used when un-parceling our parcle (creating the object)
    public static final Parcelable.Creator<ListingsModel> CREATOR = new Parcelable.Creator<ListingsModel>(){

        @Override
        public ListingsModel createFromParcel(Parcel parcel) {
            return new ListingsModel(parcel);
        }

        @Override
        public ListingsModel[] newArray(int size) {
            return new ListingsModel[0];
        }
    };

    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }
}

