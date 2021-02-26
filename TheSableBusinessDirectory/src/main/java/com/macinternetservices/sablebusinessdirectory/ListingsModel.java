package com.macinternetservices.sablebusinessdirectory;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.location.Geofence;

import java.util.ArrayList;


public class ListingsModel extends ArrayList<Parcelable> implements Parcelable {
    public static final int IMAGE_TYPE = 1;
    public String title, status, category, featured_image, bldgno, street, city, state, country, zipcode, phone, email, website, twitter, facebook, video,
            hours, content, image, logo, timestamp, link, isOpen, reviews, foo, userName, userEmail, userImage, userId;
    public int id, ratingCount;
    public Double latitude, longitude;
    Float rating;
    Boolean featured;
    String geofence;


    public ListingsModel(int imageType, Integer id, String raw, String link, String status, String name, Boolean featured, String src, String bldgNo, String street, String city, String region, String country, String zip, Double latitude, Double longitude, Float rating, Integer ratingCount, String phone, String email, String website, String twitter, String facebook, String video, String todayRange, String isOpen, String logo, String raw1, String thumbnail, String raw2, SimpleGeofence geofence) {
        this.id = id;
        this.title = raw;
        this.link = link;
        this.status = status;
        this.category = name;
        this.featured = featured;
        this.featured_image = src;
        this.bldgno = bldgNo;
        this.street = street;
        this.city = city;
        this.state = region;
        this.country = country;
        this.zipcode = zip;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.twitter = twitter;
        this.facebook = facebook;
        this.video = video;
        this.hours = todayRange;
        this.isOpen = isOpen;
        this.logo = logo;
        this.content = raw1;
        this.image = thumbnail;
        this.foo = raw2;
        this.geofence = geofence.toString();
    }


    //write object values to parcel for storage
    public void writeToParcel(Parcel dest, int flags){
        //write all properties to the parcel
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(link);
        dest.writeString(status);
        dest.writeString(category);
//        dest.writeBoolean(featured);
        dest.writeString(featured_image);
        dest.writeString(bldgno);
        dest.writeString(street);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(country);
        dest.writeString(zipcode);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeFloat(rating);
        dest.writeInt(ratingCount);
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
        dest.writeString(geofence.toString());


    }

    //constructor used for parcel
    public ListingsModel(Parcel parcel){
        //read and set saved values from parcel
        id = parcel.readInt();
        title = parcel.readString();
        link = parcel.readString();
        status = parcel.readString();
        category = parcel.readString();
       // featured = parcel.readBoolean();
        featured_image = parcel.readString();
        bldgno = parcel.readString();
        street = parcel.readString();
        city = parcel.readString();
        state = parcel.readString();
        country = parcel.readString();
        zipcode = parcel.readString();
        latitude = parcel.readDouble();
        longitude = parcel.readDouble();
        rating = parcel.readFloat();
        ratingCount = parcel.readInt();
        phone = parcel.readString();
        email = parcel.readString();
        website = parcel.readString();
        twitter = parcel.readString();
        facebook = parcel.readString();
        video = parcel.readString();
        hours = parcel.readString();
        isOpen = parcel.readString();
        reviews = parcel.readString();
        logo = parcel.readString();
        content = parcel.readString();
        image = parcel.readString();
        timestamp = parcel.readString();
        geofence = parcel.readString();
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

