package com.sable.businesslistingapi;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class ListingsModel implements Parcelable {
    public static final int IMAGE_TYPE = 1;
    public String post_title, post_status, default_category, featured_image,
            bldgno, street, city, state, country, zip, phone, email, website, twitter, facebook, video,
            hours, isOpen, content, image, logo, timestamp;
    public boolean featured;
    public int rating, id;
    public Double latitude, longitude;
    public Object post_tags;
    List post_category;


    public ListingsModel(int imageType, Integer id, String title, String status, String defaultCategory,
                         List<BusinessListings.PostCategory> postCategory, Boolean featured, String mfeaturedImage,
                         String bldgNo, String street, String city, String region, String country, String zip,
                         Double latitude, Double longitude, Integer rating, String phone, String email,
                         String website, String twitter, String facebook, String video,
                         BusinessListings.BusinessHours businessHours, String commentStatus,
                         String logo, String content, BusinessListings.FeaturedImage featuredImage,
                         String src1, String src2) {

        //this.mimageType= imageType;
        this.id = id;
        this.post_title = title;
        this.post_status = status;
      //  this.post_tags = tags;
        this.default_category = defaultCategory;
        this.post_category = postCategory;
        this.featured = featured;
        this.featured_image = mfeaturedImage;
        this.bldgno = bldgNo;
        this.street = street;
        this.city = city;
        this.state = region;
        this.country = country;
        this.zip = zip;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.twitter = twitter;
        this.facebook = facebook;
        this.video = video;
       // this.hours = businessHours;
        this.isOpen = commentStatus;
        this.logo = logo;
        this.content = content;
      //  this.image = mImage;
   }




    //write object values to parcel for storage
    public void writeToParcel(Parcel dest, int flags){
        //write all properties to the parcel
        dest.writeInt(id);
        dest.writeString(post_title);
        dest.writeString(post_status);
        //dest.writeParcelable(post_tags);
        dest.writeString(default_category);
        //dest.writeString(post_category);
        //dest.writeBoolean(featured);
        dest.writeString(featured_image);
        dest.writeString(bldgno);
        dest.writeString(street);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(country);
        dest.writeString(zip);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeInt(rating);
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
        post_title = parcel.readString();
        post_status = parcel.readString();
        post_tags = parcel.readString();
        default_category = parcel.readString();
        //post_category = parcel.readString();
        //featured = parcel.readBoolean();
        featured_image = parcel.readString();
        bldgno = parcel.readString();
        street = parcel.readString();
        city = parcel.readString();
        state = parcel.readString();
        country = parcel.readString();
        zip = parcel.readString();
        latitude = parcel.readDouble();
        longitude = parcel.readDouble();
        rating = parcel.readInt();
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

