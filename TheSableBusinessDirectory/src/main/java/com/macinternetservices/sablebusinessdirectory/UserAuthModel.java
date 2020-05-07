package com.macinternetservices.sablebusinessdirectory;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class UserAuthModel extends ArrayList<Parcelable> implements Parcelable {
    public static final int IMAGE_TYPE = 1;
    public String status, msg, wpUserId, cookie, user_login, image, username, useremail;
    public int id, ratingCount;


    public UserAuthModel(int IMAGE_TYPE,
                         String status,
                         String msg,
                         Integer wpUserId,
                         String cookie,
                         String user_login,
                         String username,
                         String useremail,
                         String image){

        this.status = status;
        this.msg = msg;
        this.wpUserId = String.valueOf(wpUserId);
        this.username = username;
        this.useremail = useremail;
        this.image = image;
        this.cookie = cookie;
        this.user_login = user_login;
   }




    //write object values to parcel for storage
    public void writeToParcel(Parcel dest, int flags){
        //write all properties to the parcel
        dest.writeString(status);
        dest.writeString(msg);
        dest.writeString(wpUserId);
        dest.writeString(username);
        dest.writeString(useremail);
        dest.writeString(image);
        dest.writeString(cookie);
        dest.writeString(user_login);
    }

    //constructor used for parcel
    public UserAuthModel(Parcel parcel){
        //read and set saved values from parcel
        status = parcel.readString();
        msg = parcel.readString();
        username = parcel.readString();
        useremail = parcel.readString();
        image = parcel.readString();
        cookie = parcel.readString();
        user_login = parcel.readString();
    }
    //creator - used when un-parceling our parcle (creating the object)
    public static final Creator<UserAuthModel> CREATOR = new Creator<UserAuthModel>(){

        @Override
        public UserAuthModel createFromParcel(Parcel parcel) {
            return new UserAuthModel(parcel);
        }

        @Override
        public UserAuthModel[] newArray(int size) {
            return new UserAuthModel[0];
        }
    };

    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }
}

