package com.sable.businesslistingapi;

import android.os.Parcel;
import android.os.Parcelable;


public class ListingsAddModel implements Parcelable {
    public static final int IMAGE_TYPE = 1;
    public String name, category, description, address, state, country, zipcode,  city, bldgNo, street, email, website, twitter, facebook, img1, img2, img3, phone;
    public int type, rating, ratingCount;
    public Double lat, lng;

    public ListingsAddModel(int mtype, String mName, String mCategory, String  mDescription, Double longitude, Double latitude, String maddress,
                            String mstate, String mCountry, String mZipcode,
                            String mCity, String mbldgNo, String mstreet, String mimg1,
                            String mimg2, String mimg3, String mPhone, String mEmail, String mWebsite,
                            String mTwitter, String mFacebook) {
        this.name = mName;
        this.category = mCategory;
        this.description = mDescription;
        this.lng = longitude;
        this.lat = latitude;
        this.address = maddress;
        this.state = mstate;
        this.country = mCountry;
        this.zipcode = mZipcode;
        this.city = mCity;
        this.bldgNo = mbldgNo;
        this.street = mstreet;
        this.img1 = mimg1;
        this.img2 = mimg2;
        this.img3 = mimg3;
        this.phone = mPhone;
        this.email = mEmail;
        this.website = mWebsite;
        this.twitter = mTwitter;
        this.facebook = mFacebook;
    }

    //write object values to parcel for storage
    public void writeToParcel(Parcel dest, int flags){
        //write all properties to the parcel
        //dest.writeInt(type);
        dest.writeString(name);
        dest.writeString(category);
        dest.writeString(description);
        dest.writeDouble(lng);
        dest.writeDouble(lat);
        dest.writeString(address);
        dest.writeString(state);
        dest.writeString(country);
        dest.writeString(zipcode);
        dest.writeString(city);
        dest.writeString(bldgNo);
        dest.writeString(street);
        dest.writeString(img1);
        dest.writeString(img2);
        dest.writeString(img3);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(website);
        dest.writeString(twitter);
        dest.writeString(facebook);
    }

    //constructor used for parcel
    public ListingsAddModel(Parcel parcel){
        //read and set saved values from parcel
        name = parcel.readString();
        category = parcel.readString();
        description = parcel.readString();
        lng = parcel.readDouble();
        lat = parcel.readDouble();
        address = parcel.readString();
        state = parcel.readString();
        country = parcel.readString();
        zipcode = parcel.readString();
        city = parcel.readString();
        bldgNo = parcel.readString();
        street = parcel.readString();
        img1 = parcel.readString();
        img2 = parcel.readString();
        img3 = parcel.readString();
        phone = parcel.readString();
        email = parcel.readString();
        website = parcel.readString();
        twitter = parcel.readString();
        facebook = parcel.readString();
    }

    //creator - used when un-parceling our parcle (creating the object)
    public static final Creator<ListingsAddModel> CREATOR = new Creator<ListingsAddModel>(){

        @Override
        public ListingsAddModel createFromParcel(Parcel parcel) {
            return new ListingsAddModel(parcel);
        }

        @Override
        public ListingsAddModel[] newArray(int size) {
            return new ListingsAddModel[0];
        }
    };

    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }
}

