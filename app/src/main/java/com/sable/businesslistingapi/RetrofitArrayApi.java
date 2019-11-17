package com.sable.businesslistingapi;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Jaink on 14-09-2017.
 */



public interface RetrofitArrayApi {

    @GET("wp-json/geodir/v2/business")
    Call<List<BusinessListings>> getPostInfo(
            @QueryMap Map<String, String> options
    );

    @GET("wp-json/geodir/v2/business/?post_type=gd_business")
    Call<List<BusinessListings>> getSearch(
            @Query("search") String query);

    @GET("wp-json/geodir/v2/business/?post_type=gd_business")
    Call<List<BusinessListings>> getRadius(
            @QueryMap Map<String, String> options
    );

    @GET("wp-json/wc/v3/products?consumer_key=ck_c3addab1f230fa55025a2f78969d18f518ebbc5e&consumer_secret=cs_aaf9c39669e92ebd745a0e91a9a5810e9222cc92")
    Call<List<WooProducts>> getPostWooInfo();

    @GET("wp-json/geodir/v2/business/categories")
    Call<List<ListingsCategories>> getCategory();

/// to make call to dynamic URL
 // @GET
  //Call<List<BusinessListings>> getPostInfo(@Url String url);
//
    @Multipart
    @POST("wp-json/geodir/v2/business/")
    Call<List<BusinessListings>> postData(
            //@Path("id") String id,
            @Part("post_title") String post_title,
            @Part("post_status") String post_status,
           // @Field("post_tags") String post_tags,
            @Part("default_category") String category,
            //@Field("featured") String featured,
            @Part("content")String content,
            @Part("bldgno") String bldgno,
            @Part("street") String street,
            @Part("city") String city,
            @Part("region") String state,
            @Part("country") String country,
            @Part("zip") String zip,
            @Part("latitude") Double latitude,
            @Part("longitude") Double longitude,
            @Part("ratings") Integer ratings,
            @Part("phone") String phone,
            @Part("email") String email,
            @Part("website") String website,
            @Part("twitter") String twitter,
            @Part("facebook") String facebook,
           // @Field("video") File video,
            //@Part("business_hours") String hours,
            @Part MultipartBody.Part image,
            @Part MultipartBody.Part logo,
            @Part MultipartBody.Part featured_image);
 }

