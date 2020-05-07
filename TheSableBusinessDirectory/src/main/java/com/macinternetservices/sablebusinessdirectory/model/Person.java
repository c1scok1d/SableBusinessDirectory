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

public class Person implements ClusterItem, com.macinternetservices.sablebusinessdirectory.clustering.ClusterItem {
    public final String name, profilePhoto, content, city, state, featuredImage;
    public final Float rating;
    public final LatLng position;
    public final Integer ratingCount;

    public Person(LatLng position, String name, String pictureResource, String content, Float rating, Integer ratingCount, String city, String state, String featuredImage) {
        this.name = name;
        profilePhoto = pictureResource;
        this.position = position;
        this.content = content;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.city = city;
        this.state = state;
        this.featuredImage = featuredImage;

    }

    @Override
    public LatLng getPosition() {
        return position;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getSnippet() {
        return content;
    }

    @Override
    public Float getRating() { return rating; }

    @Override
    public String getFeaturedImage() {
        return featuredImage;
    }

    @Override
    public Integer getRatingCount() { return ratingCount; }

    @Override
    public  String getCity() { return city;}

    @Override
    public String getState() { return state;}
}
