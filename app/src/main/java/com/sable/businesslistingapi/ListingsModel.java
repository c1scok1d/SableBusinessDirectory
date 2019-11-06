package com.sable.businesslistingapi;

import android.os.Parcel;
import android.os.Parcelable;


public class ListingsModel implements Parcelable {
    public static final int IMAGE_TYPE = 1;
    public String title, street, city, state, zip, hours, isOpen, content, image, phone, timestamp, bldgNo;
    public int type, rating, ratingCount;
    public Double lat, lng;

    public ListingsModel(int mtype, String mtitle, Integer mrating, Integer mRatingCount, String mstreet, String mcity, String mstate, String mzip, String mhours, String mIsOpen, String mcontent, String mimage, String mphone, Double mlat, Double mlng, String mtimestamp) {
        this.type = mtype;
        this.title = mtitle;
        this.rating = mrating;
        this.ratingCount = mRatingCount;
        this.street = mstreet;
        this.city = mcity;
        this.state = mstate;
        this.zip = mzip;
        this.hours = mhours;
        this.isOpen = mIsOpen;
        this.content = mcontent;
        this.image = mimage;
        this.phone = mphone;
        this.lat = mlat;
        this.lng = mlng;
        this.timestamp = mtimestamp;
    }

    //write object values to parcel for storage
    public void writeToParcel(Parcel dest, int flags){
        //write all properties to the parcel
        //dest.writeInt(type);
        dest.writeString(title);
        //dest.writeString(bldgNo);
        dest.writeInt(rating);
        dest.writeInt(ratingCount);
        dest.writeString(street);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(zip);
        dest.writeString(hours);
        dest.writeString(isOpen);
        dest.writeString(content);
        dest.writeString(image);
        dest.writeString(phone);
        dest.writeDouble(lat);
        dest.writeDouble(lng);

    }

    //constructor used for parcel
    public ListingsModel(Parcel parcel){
        //read and set saved values from parcel
        title = parcel.readString();
        bldgNo = parcel.readString();
        rating = parcel.readInt();
        ratingCount = parcel.readInt();
        street = parcel.readString();
        city = parcel.readString();
        state = parcel.readString();
        zip = parcel.readString();
        hours = parcel.readString();
        isOpen = parcel.readString();
        content = parcel.readString();
        image = parcel.readString();
        phone = parcel.readString();
        lat = parcel.readDouble();
        lng = parcel.readDouble();
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

