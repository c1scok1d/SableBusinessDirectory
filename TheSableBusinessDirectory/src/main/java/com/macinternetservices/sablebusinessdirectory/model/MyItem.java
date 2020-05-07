/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.macinternetservices.sablebusinessdirectory.model;

import com.google.android.gms.maps.model.LatLng;
import com.macinternetservices.sablebusinessdirectory.ClusterItem;

public class MyItem implements ClusterItem {
    private final LatLng mPosition;
    private String mTitle;
    private String mSnippet;
    private Float mRating;
    private Integer mRatingCount;
    private String mFeaturedImage;


    public MyItem(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
        mTitle = null;
        mSnippet = null;
        mRatingCount = null;
        mRating = null;
        mFeaturedImage = null;
    }

    public MyItem(double lat, double lng, String title, String snippet, Float rating, Integer ratingCount, String featuredImage) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
        mSnippet = snippet;
        mRating = rating;
        mRatingCount = ratingCount;
        mFeaturedImage = featuredImage;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() { return mTitle; }

    @Override
    public String getSnippet() { return mSnippet; }

    @Override
    public Float getRating() { return mRating; }

    @Override
    public String getFeaturedImage() {
        return mFeaturedImage;
    }

    @Override
    public Integer getRatingCount() { return mRatingCount; }


    /**
     * Set the title of the marker
     * @param title string to be set as title
     */
    public void setTitle(String title) {
        mTitle = title;
    }

    /**
     * Set the description of the marker
     * @param snippet string to be set as snippet
     */
    public void setSnippet(String snippet) {
        mSnippet = snippet;
    }

    /**
     * Set the description of the marker
     * @param featureadImage string to be set as snippet
     */
    public void setFeatureadImage(String featureadImage) {
        mFeaturedImage = featureadImage;
    }

    /**
     * Set the description of the marker
     * @param rating string to be set as snippet
     */
    public void setRating(Float rating) {
        mRating = rating;
    }

    /**
     * Set the description of the marker
     * @param ratingCount string to be set as snippet
     */
    public void setRatingCount(Integer ratingCount) {
        mRatingCount = ratingCount;
    }
}
