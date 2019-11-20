package com.sable.businesslistingapi;

import android.os.Parcel;
import android.os.Parcelable;


public class ListingsAddModel implements Parcelable {
    public static final int IMAGE_TYPE = 1;
    public String name, category, description, address, state, country, zipcode,  city, bldgNo, street, email, website, twitter, facebook, phone, link;
    public int type, rating, ratingCount, addCategory;
    public Double lat, lng;

    public ListingsAddModel(int mtype, String mName, String mCategory, Integer addCategory, String  mDescription, Double longitude, Double latitude, String maddress,
                            String mstate, String mCountry, String mZipcode,
                            String mCity, String mbldgNo, String mstreet, String mPhone, String mEmail, String mWebsite,
                            String mTwitter, String mFacebook) {
        this.name = mName;
        //this.link = mlink;
        this.category = mCategory;
        this.addCategory = addCategory;
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
        dest.writeString(link);
        dest.writeString(category);
        dest.writeInt(addCategory);
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
        link = parcel.readString();
        category = parcel.readString();
        addCategory = parcel.readInt();
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

