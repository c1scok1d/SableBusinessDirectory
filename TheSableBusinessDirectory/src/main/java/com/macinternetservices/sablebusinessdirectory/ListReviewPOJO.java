package com.macinternetservices.sablebusinessdirectory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListReviewPOJO {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("date_gmt")
    @Expose
    private String dateGmt;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("author")
    @Expose
    private Integer author;
    @SerializedName("meta")
    @Expose
    private List<Object> meta = null;
    @SerializedName("post")
    @Expose
    private Integer post;
    @SerializedName("_links")
    @Expose
    private Up.Links links;
    @SerializedName("parent")
    @Expose
    private Integer parent;
    @SerializedName("author_name")
    @Expose
    private String authorName;
    @SerializedName("author_url")
    @Expose
    private String authorUrl;
    @SerializedName("content")
    @Expose
    private BusinessListings.Content content;
    @SerializedName("post_type")
    @Expose
    private String postType;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("rating")
    @Expose
    private Rating rating;
    @SerializedName("ratings")
    @Expose
    private Ratings ratings;
    @SerializedName("images")
    @Expose
    private Up.Images images;
    @SerializedName("total_images")
    @Expose
    private Integer totalImages;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("author_avatar_urls")
    @Expose
    private Up.AuthorAvatarUrls authorAvatarUrls;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateGmt() {
        return dateGmt;
    }

    public void setDateGmt(String dateGmt) {
        this.dateGmt = dateGmt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public List<Object> getMeta() {
        return meta;
    }

    public void setMeta(List<Object> meta) {
        this.meta = meta;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public Up.Links getLinks() {
        return links;
    }

    public void setLinks(Up.Links links) {
        this.links = links;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public BusinessListings.Content getContent() {
        return content;
    }

    public void setContent(BusinessListings.Content content) {
        this.content = content;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }

    public Up.Images getImages() {
        return images;
    }

    public void setImages(Up.Images images) {
        this.images = images;
    }

    public Integer getTotalImages() {
        return totalImages;
    }

    public void setTotalImages(Integer totalImages) {
        this.totalImages = totalImages;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Up.AuthorAvatarUrls getAuthorAvatarUrls() {
        return authorAvatarUrls;
    }

    public void setAuthorAvatarUrls(Up.AuthorAvatarUrls authorAvatarUrls) {
        this.authorAvatarUrls = authorAvatarUrls;
    }


    public class Rating {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("label")
        @Expose
        private String label;
        @SerializedName("rating")
        @Expose
        private Integer rating;
        @SerializedName("html")
        @Expose
        private String html;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

    }

    public class Ratings {

        @SerializedName("rendered")
        @Expose
        private List<Object> rendered = null;

        public List<Object> getRendered() {
            return rendered;
        }

        public void setRendered(List<Object> rendered) {
            this.rendered = rendered;
        }

    }

    public class Rendered {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("src")
        @Expose
        private String src;
        @SerializedName("thumbnail")
        @Expose
        private String thumbnail;
        @SerializedName("featured")
        @Expose
        private Boolean featured;
        @SerializedName("position")
        @Expose
        private String position;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public Boolean getFeatured() {
            return featured;
        }

        public void setFeatured(Boolean featured) {
            this.featured = featured;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

    }

    public class Self {

        @SerializedName("href")
        @Expose
        private String href;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

    }

    public class Up {

        @SerializedName("embeddable")
        @Expose
        private Boolean embeddable;
        @SerializedName("post_type")
        @Expose
        private String postType;
        @SerializedName("href")
        @Expose
        private String href;

        public Boolean getEmbeddable() {
            return embeddable;
        }

        public void setEmbeddable(Boolean embeddable) {
            this.embeddable = embeddable;
        }

        public String getPostType() {
            return postType;
        }

        public void setPostType(String postType) {
            this.postType = postType;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public class Author {

            @SerializedName("embeddable")
            @Expose
            private Boolean embeddable;
            @SerializedName("href")
            @Expose
            private String href;

            public Boolean getEmbeddable() {
                return embeddable;
            }

            public void setEmbeddable(Boolean embeddable) {
                this.embeddable = embeddable;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

        }

        public class AuthorAvatarUrls {

            @SerializedName("24")
            @Expose
            private String _24;
            @SerializedName("48")
            @Expose
            private String _48;
            @SerializedName("96")
            @Expose
            private String _96;

            public String get24() {
                return _24;
            }

            public void set24(String _24) {
                this._24 = _24;
            }

            public String get48() {
                return _48;
            }

            public void set48(String _48) {
                this._48 = _48;
            }

            public String get96() {
                return _96;
            }

            public void set96(String _96) {
                this._96 = _96;
            }

        }

        public class Collection {

            @SerializedName("href")
            @Expose
            private String href;

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

        }

        public class Content {

            @SerializedName("rendered")
            @Expose
            private String rendered;

            public String getRendered() {
                return rendered;
            }

            public void setRendered(String rendered) {
                this.rendered = rendered;
            }

        }

        public class Images {

            @SerializedName("rendered")
            @Expose
            private List<Rendered> rendered = null;

            public List<Rendered> getRendered() {
                return rendered;
            }

            public void setRendered(List<Rendered> rendered) {
                this.rendered = rendered;
            }

        }

        public class Links {

            @SerializedName("self")
            @Expose
            private List<Self> self = null;
            @SerializedName("collection")
            @Expose
            private List<Collection> collection = null;
            @SerializedName("author")
            @Expose
            private List<Author> author = null;
            @SerializedName("up")
            @Expose
            private List<Up> up = null;

            public List<Self> getSelf() {
                return self;
            }

            public void setSelf(List<Self> self) {
                this.self = self;
            }

            public List<Collection> getCollection() {
                return collection;
            }

            public void setCollection(List<Collection> collection) {
                this.collection = collection;
            }

            public List<Author> getAuthor() {
                return author;
            }

            public void setAuthor(List<Author> author) {
                this.author = author;
            }

            public List<Up> getUp() {
                return up;
            }

            public void setUp(List<Up> up) {
                this.up = up;
            }
        }
    }
}