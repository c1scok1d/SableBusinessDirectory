package com.macinternetservices.sablebusinessdirectory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusinessListings {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("featured")
    @Expose
    private Boolean featured;
    @SerializedName("rating_count")
    @Expose
    private Integer ratingCount;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("title")
    @Expose
    private Title title;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("author")
    @Expose
    private Integer author;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("date_gmt")
    @Expose
    private String dateGmt;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("modified_gmt")
    @Expose
    private String modifiedGmt;
    @SerializedName("content")
    @Expose
    private Content content;
    @SerializedName("default_category")
    @Expose
    private String defaultCategory;
    @SerializedName("post_category")
    @Expose
    private List<PostCategory> postCategory = null;
    @SerializedName("post_tags")
    @Expose
    private List<Object> postTags = null;
    @SerializedName("package_id")
    @Expose
    private String packageId;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("bldgno")
    @Expose
    private String bldgno;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("zip")
    @Expose
    private String zip;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("mapview")
    @Expose
    private Object mapview;
    @SerializedName("mapzoom")
    @Expose
    private String mapzoom;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("special_offers")
    @Expose
    private String specialOffers;
    @SerializedName("business_hours")
    @Expose
    private BusinessHours businessHours;
    @SerializedName("claimed")
    @Expose
    private Claimed claimed;
    @SerializedName("rating")
    @Expose
    private Float rating;
    @SerializedName("featured_media")
    @Expose
    private Integer featuredMedia;
    @SerializedName("featured_image")
    @Expose
    private FeaturedImage featuredImage;
    @SerializedName("comment_status")
    @Expose
    private String commentStatus;
    @SerializedName("ping_status")
    @Expose
    private String pingStatus;
    @SerializedName("meta")
    @Expose
    private List<Object> meta = null;
    @SerializedName("guid")
    @Expose
    private Guid guid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
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

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getModifiedGmt() {
        return modifiedGmt;
    }

    public void setModifiedGmt(String modifiedGmt) {
        this.modifiedGmt = modifiedGmt;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getDefaultCategory() {
        return defaultCategory;
    }

    public void setDefaultCategory(String defaultCategory) {
        this.defaultCategory = defaultCategory;
    }

    public List<PostCategory> getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(List<PostCategory> postCategory) {
        this.postCategory = postCategory;
    }

    public List<Object> getPostTags() {
        return postTags;
    }

    public void setPostTags(List<Object> postTags) {
        this.postTags = postTags;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getBldgNo() {
        return bldgno;
    }

    public void setBldgNo(String bldgno) {
        this.bldgno = bldgno;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Object getMapview() {
        return mapview;
    }

    public void setMapview(Object mapview) {
        this.mapview = mapview;
    }

    public String getMapzoom() {
        return mapzoom;
    }

    public void setMapzoom(String mapzoom) {
        this.mapzoom = mapzoom;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getSpecialOffers() {
        return specialOffers;
    }

    public void setSpecialOffers(String specialOffers) {
        this.specialOffers = specialOffers;
    }

    public BusinessHours getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(BusinessHours businessHours) {
        this.businessHours = businessHours;
    }

    public Claimed getClaimed() {
        return claimed;
    }

    public void setClaimed(Claimed claimed) {
        this.claimed = claimed;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getFeaturedMedia() {
        return featuredMedia;
    }

    public void setFeaturedMedia(Integer featuredMedia) {
        this.featuredMedia = featuredMedia;
    }

    public FeaturedImage getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(FeaturedImage featuredImage) {
        this.featuredImage = featuredImage;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getPingStatus() {
        return pingStatus;
    }

    public void setPingStatus(String pingStatus) {
        this.pingStatus = pingStatus;
    }

    public List<Object> getMeta() {
        return meta;
    }

    public void setMeta(List<Object> meta) {
        this.meta = meta;
    }

    public Guid getGuid() {
        return guid;
    }

    public void setGuid(Guid guid) {
        this.guid = guid;
    }

    public class About {

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

    public class BusinessHours {

        @SerializedName("raw")
        @Expose
        private String raw;
        @SerializedName("rendered")
        @Expose
        private Rendered rendered;

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public Rendered getRendered() {
            return rendered;
        }

        public void setRendered(Rendered rendered) {
            this.rendered = rendered;
        }

    }

    public class Claimed {

        @SerializedName("raw")
        @Expose
        private String raw;
        @SerializedName("rendered")
        @Expose
        private String rendered;

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
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

        @SerializedName("raw")
        @Expose
        private String raw;
        @SerializedName("rendered")
        @Expose
        private String rendered;
        @SerializedName("protected")
        @Expose
        private Boolean _protected;

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }

        public Boolean getProtected() {
            return _protected;
        }

        public void setProtected(Boolean _protected) {
            this._protected = _protected;
        }

    }

    public class Cury {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("href")
        @Expose
        private String href;
        @SerializedName("templated")
        @Expose
        private Boolean templated;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public Boolean getTemplated() {
            return templated;
        }

        public void setTemplated(Boolean templated) {
            this.templated = templated;
        }

    }

    public class Days {

        @SerializedName("Mo")
        @Expose
        private Mo mo;
        @SerializedName("Tu")
        @Expose
        private Tu tu;
        @SerializedName("We")
        @Expose
        private We we;
        @SerializedName("Th")
        @Expose
        private Th th;
        @SerializedName("Fr")
        @Expose
        private Fr fr;
        @SerializedName("Sa")
        @Expose
        private Sa sa;
        @SerializedName("Su")
        @Expose
        private Su su;

        public Mo getMo() {
            return mo;
        }

        public void setMo(Mo mo) {
            this.mo = mo;
        }

        public Tu getTu() {
            return tu;
        }

        public void setTu(Tu tu) {
            this.tu = tu;
        }

        public We getWe() {
            return we;
        }

        public void setWe(We we) {
            this.we = we;
        }

        public Th getTh() {
            return th;
        }

        public void setTh(Th th) {
            this.th = th;
        }

        public Fr getFr() {
            return fr;
        }

        public void setFr(Fr fr) {
            this.fr = fr;
        }

        public Sa getSa() {
            return sa;
        }

        public void setSa(Sa sa) {
            this.sa = sa;
        }

        public Su getSu() {
            return su;
        }

        public void setSu(Su su) {
            this.su = su;
        }

    }

    public class Extra {

        @SerializedName("has_open")
        @Expose
        private Integer hasOpen;
        @SerializedName("has_closed")
        @Expose
        private Integer hasClosed;
        @SerializedName("today_range")
        @Expose
        private String todayRange;
        @SerializedName("current_label")
        @Expose
        private String currentLabel;
        @SerializedName("open_now_label")
        @Expose
        private String openNowLabel;
        @SerializedName("closed_now_label")
        @Expose
        private String closedNowLabel;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("time")
        @Expose
        private String time;
        @SerializedName("full_date")
        @Expose
        private String fullDate;
        @SerializedName("date_format")
        @Expose
        private String dateFormat;
        @SerializedName("time_format")
        @Expose
        private String timeFormat;
        @SerializedName("full_date_format")
        @Expose
        private String fullDateFormat;
        @SerializedName("offset")
        @Expose
        private String offset;

        public Integer getHasOpen() {
            return hasOpen;
        }

        public void setHasOpen(Integer hasOpen) {
            this.hasOpen = hasOpen;
        }

        public Integer getHasClosed() {
            return hasClosed;
        }

        public void setHasClosed(Integer hasClosed) {
            this.hasClosed = hasClosed;
        }

        public String getTodayRange() {
            return todayRange;
        }

        public void setTodayRange(String todayRange) {
            this.todayRange = todayRange;
        }

        public String getCurrentLabel() {
            return currentLabel;
        }

        public void setCurrentLabel(String currentLabel) {
            this.currentLabel = currentLabel;
        }

        public String getOpenNowLabel() {
            return openNowLabel;
        }

        public void setOpenNowLabel(String openNowLabel) {
            this.openNowLabel = openNowLabel;
        }

        public String getClosedNowLabel() {
            return closedNowLabel;
        }

        public void setClosedNowLabel(String closedNowLabel) {
            this.closedNowLabel = closedNowLabel;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getFullDate() {
            return fullDate;
        }

        public void setFullDate(String fullDate) {
            this.fullDate = fullDate;
        }

        public String getDateFormat() {
            return dateFormat;
        }

        public void setDateFormat(String dateFormat) {
            this.dateFormat = dateFormat;
        }

        public String getTimeFormat() {
            return timeFormat;
        }

        public void setTimeFormat(String timeFormat) {
            this.timeFormat = timeFormat;
        }

        public String getFullDateFormat() {
            return fullDateFormat;
        }

        public void setFullDateFormat(String fullDateFormat) {
            this.fullDateFormat = fullDateFormat;
        }

        public String getOffset() {
            return offset;
        }

        public void setOffset(String offset) {
            this.offset = offset;
        }

    }

    public class FeaturedImage {

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
        @SerializedName("width")
        @Expose
        private Integer width;
        @SerializedName("height")
        @Expose
        private Integer height;

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

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

    }

    public class Fr {

        @SerializedName("today")
        @Expose
        private Integer today;
        @SerializedName("closed")
        @Expose
        private Integer closed;
        @SerializedName("open")
        @Expose
        private Integer open;
        @SerializedName("day")
        @Expose
        private String day;
        @SerializedName("day_short")
        @Expose
        private String dayShort;
        @SerializedName("day_no")
        @Expose
        private Integer dayNo;
        @SerializedName("slots")
        @Expose
        private List<Slot____> slots = null;

        public Integer getToday() {
            return today;
        }

        public void setToday(Integer today) {
            this.today = today;
        }

        public Integer getClosed() {
            return closed;
        }

        public void setClosed(Integer closed) {
            this.closed = closed;
        }

        public Integer getOpen() {
            return open;
        }

        public void setOpen(Integer open) {
            this.open = open;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getDayShort() {
            return dayShort;
        }

        public void setDayShort(String dayShort) {
            this.dayShort = dayShort;
        }

        public Integer getDayNo() {
            return dayNo;
        }

        public void setDayNo(Integer dayNo) {
            this.dayNo = dayNo;
        }

        public List<Slot____> getSlots() {
            return slots;
        }

        public void setSlots(List<Slot____> slots) {
            this.slots = slots;
        }

    }

    public class Guid {

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

    public class Image {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("src")
        @Expose
        private String src;
        @SerializedName("title")
        @Expose
        private String title;
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

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

    public class Links {

        @SerializedName("self")
        @Expose
        private List<Self> self = null;
        @SerializedName("collection")
        @Expose
        private List<Collection> collection = null;
        @SerializedName("about")
        @Expose
        private List<About> about = null;
        @SerializedName("author")
        @Expose
        private List<Author> author = null;
        @SerializedName("replies")
        @Expose
        private List<Reply> replies = null;
        @SerializedName("version-history")
        @Expose
        private List<VersionHistory> versionHistory = null;
        @SerializedName("wp:featuredmedia")
        @Expose
        private List<WpFeaturedmedium> wpFeaturedmedia = null;
        @SerializedName("wp:attachment")
        @Expose
        private List<WpAttachment> wpAttachment = null;
        @SerializedName("wp:term")
        @Expose
        private List<WpTerm> wpTerm = null;
        @SerializedName("curies")
        @Expose
        private List<Cury> curies = null;

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

        public List<About> getAbout() {
            return about;
        }

        public void setAbout(List<About> about) {
            this.about = about;
        }

        public List<Author> getAuthor() {
            return author;
        }

        public void setAuthor(List<Author> author) {
            this.author = author;
        }

        public List<Reply> getReplies() {
            return replies;
        }

        public void setReplies(List<Reply> replies) {
            this.replies = replies;
        }

        public List<VersionHistory> getVersionHistory() {
            return versionHistory;
        }

        public void setVersionHistory(List<VersionHistory> versionHistory) {
            this.versionHistory = versionHistory;
        }

        public List<WpFeaturedmedium> getWpFeaturedmedia() {
            return wpFeaturedmedia;
        }

        public void setWpFeaturedmedia(List<WpFeaturedmedium> wpFeaturedmedia) {
            this.wpFeaturedmedia = wpFeaturedmedia;
        }

        public List<WpAttachment> getWpAttachment() {
            return wpAttachment;
        }

        public void setWpAttachment(List<WpAttachment> wpAttachment) {
            this.wpAttachment = wpAttachment;
        }

        public List<WpTerm> getWpTerm() {
            return wpTerm;
        }

        public void setWpTerm(List<WpTerm> wpTerm) {
            this.wpTerm = wpTerm;
        }

        public List<Cury> getCuries() {
            return curies;
        }

        public void setCuries(List<Cury> curies) {
            this.curies = curies;
        }

    }

    public class Mo {

        @SerializedName("today")
        @Expose
        private Integer today;
        @SerializedName("closed")
        @Expose
        private Integer closed;
        @SerializedName("open")
        @Expose
        private Integer open;
        @SerializedName("day")
        @Expose
        private String day;
        @SerializedName("day_short")
        @Expose
        private String dayShort;
        @SerializedName("day_no")
        @Expose
        private Integer dayNo;
        @SerializedName("slots")
        @Expose
        private List<Slot> slots = null;

        public Integer getToday() {
            return today;
        }

        public void setToday(Integer today) {
            this.today = today;
        }

        public Integer getClosed() {
            return closed;
        }

        public void setClosed(Integer closed) {
            this.closed = closed;
        }

        public Integer getOpen() {
            return open;
        }

        public void setOpen(Integer open) {
            this.open = open;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getDayShort() {
            return dayShort;
        }

        public void setDayShort(String dayShort) {
            this.dayShort = dayShort;
        }

        public Integer getDayNo() {
            return dayNo;
        }

        public void setDayNo(Integer dayNo) {
            this.dayNo = dayNo;
        }

        public List<Slot> getSlots() {
            return slots;
        }

        public void setSlots(List<Slot> slots) {
            this.slots = slots;
        }

    }

    public class PostCategory {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("slug")
        @Expose
        private String slug;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

    }

    public class Rendered {

        @SerializedName("days")
        @Expose
        private Days days;
        @SerializedName("extra")
        @Expose
        private Extra extra;

        public Days getDays() {
            return days;
        }

        public void setDays(Days days) {
            this.days = days;
        }

        public Extra getExtra() {
            return extra;
        }

        public void setExtra(Extra extra) {
            this.extra = extra;
        }

    }

    public class Reply {

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

    public class Sa {

        @SerializedName("today")
        @Expose
        private Integer today;
        @SerializedName("closed")
        @Expose
        private Integer closed;
        @SerializedName("open")
        @Expose
        private Integer open;
        @SerializedName("day")
        @Expose
        private String day;
        @SerializedName("day_short")
        @Expose
        private String dayShort;
        @SerializedName("day_no")
        @Expose
        private Integer dayNo;
        @SerializedName("slots")
        @Expose
        private List<Slot_____> slots = null;

        public Integer getToday() {
            return today;
        }

        public void setToday(Integer today) {
            this.today = today;
        }

        public Integer getClosed() {
            return closed;
        }

        public void setClosed(Integer closed) {
            this.closed = closed;
        }

        public Integer getOpen() {
            return open;
        }

        public void setOpen(Integer open) {
            this.open = open;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getDayShort() {
            return dayShort;
        }

        public void setDayShort(String dayShort) {
            this.dayShort = dayShort;
        }

        public Integer getDayNo() {
            return dayNo;
        }

        public void setDayNo(Integer dayNo) {
            this.dayNo = dayNo;
        }

        public List<Slot_____> getSlots() {
            return slots;
        }

        public void setSlots(List<Slot_____> slots) {
            this.slots = slots;
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

    public class Slot {

        @SerializedName("slot")
        @Expose
        private String slot;
        @SerializedName("range")
        @Expose
        private String range;
        @SerializedName("open")
        @Expose
        private Integer open;
        @SerializedName("time")
        @Expose
        private List<String> time = null;
        @SerializedName("minutes")
        @Expose
        private List<Integer> minutes = null;

        public String getSlot() {
            return slot;
        }

        public void setSlot(String slot) {
            this.slot = slot;
        }

        public String getRange() {
            return range;
        }

        public void setRange(String range) {
            this.range = range;
        }

        public Integer getOpen() {
            return open;
        }

        public void setOpen(Integer open) {
            this.open = open;
        }

        public List<String> getTime() {
            return time;
        }

        public void setTime(List<String> time) {
            this.time = time;
        }

        public List<Integer> getMinutes() {
            return minutes;
        }

        public void setMinutes(List<Integer> minutes) {
            this.minutes = minutes;
        }

    }

    public class Slot_ {

        @SerializedName("slot")
        @Expose
        private String slot;
        @SerializedName("range")
        @Expose
        private String range;
        @SerializedName("open")
        @Expose
        private Integer open;
        @SerializedName("time")
        @Expose
        private List<String> time = null;
        @SerializedName("minutes")
        @Expose
        private List<Integer> minutes = null;

        public String getSlot() {
            return slot;
        }

        public void setSlot(String slot) {
            this.slot = slot;
        }

        public String getRange() {
            return range;
        }

        public void setRange(String range) {
            this.range = range;
        }

        public Integer getOpen() {
            return open;
        }

        public void setOpen(Integer open) {
            this.open = open;
        }

        public List<String> getTime() {
            return time;
        }

        public void setTime(List<String> time) {
            this.time = time;
        }

        public List<Integer> getMinutes() {
            return minutes;
        }

        public void setMinutes(List<Integer> minutes) {
            this.minutes = minutes;
        }

    }

    public class Slot__ {

        @SerializedName("slot")
        @Expose
        private String slot;
        @SerializedName("range")
        @Expose
        private String range;
        @SerializedName("open")
        @Expose
        private Integer open;
        @SerializedName("time")
        @Expose
        private List<String> time = null;
        @SerializedName("minutes")
        @Expose
        private List<Integer> minutes = null;

        public String getSlot() {
            return slot;
        }

        public void setSlot(String slot) {
            this.slot = slot;
        }

        public String getRange() {
            return range;
        }

        public void setRange(String range) {
            this.range = range;
        }

        public Integer getOpen() {
            return open;
        }

        public void setOpen(Integer open) {
            this.open = open;
        }

        public List<String> getTime() {
            return time;
        }

        public void setTime(List<String> time) {
            this.time = time;
        }

        public List<Integer> getMinutes() {
            return minutes;
        }

        public void setMinutes(List<Integer> minutes) {
            this.minutes = minutes;
        }

    }

    public class Slot___ {

        @SerializedName("slot")
        @Expose
        private String slot;
        @SerializedName("range")
        @Expose
        private String range;
        @SerializedName("open")
        @Expose
        private Integer open;
        @SerializedName("time")
        @Expose
        private List<String> time = null;
        @SerializedName("minutes")
        @Expose
        private List<Integer> minutes = null;

        public String getSlot() {
            return slot;
        }

        public void setSlot(String slot) {
            this.slot = slot;
        }

        public String getRange() {
            return range;
        }

        public void setRange(String range) {
            this.range = range;
        }

        public Integer getOpen() {
            return open;
        }

        public void setOpen(Integer open) {
            this.open = open;
        }

        public List<String> getTime() {
            return time;
        }

        public void setTime(List<String> time) {
            this.time = time;
        }

        public List<Integer> getMinutes() {
            return minutes;
        }

        public void setMinutes(List<Integer> minutes) {
            this.minutes = minutes;
        }

    }

    public class Slot____ {

        @SerializedName("slot")
        @Expose
        private String slot;
        @SerializedName("range")
        @Expose
        private String range;
        @SerializedName("open")
        @Expose
        private Integer open;
        @SerializedName("time")
        @Expose
        private List<String> time = null;
        @SerializedName("minutes")
        @Expose
        private List<Integer> minutes = null;

        public String getSlot() {
            return slot;
        }

        public void setSlot(String slot) {
            this.slot = slot;
        }

        public String getRange() {
            return range;
        }

        public void setRange(String range) {
            this.range = range;
        }

        public Integer getOpen() {
            return open;
        }

        public void setOpen(Integer open) {
            this.open = open;
        }

        public List<String> getTime() {
            return time;
        }

        public void setTime(List<String> time) {
            this.time = time;
        }

        public List<Integer> getMinutes() {
            return minutes;
        }

        public void setMinutes(List<Integer> minutes) {
            this.minutes = minutes;
        }

    }

    public class Slot_____ {

        @SerializedName("slot")
        @Expose
        private Object slot;
        @SerializedName("range")
        @Expose
        private String range;
        @SerializedName("open")
        @Expose
        private Integer open;
        @SerializedName("time")
        @Expose
        private List<Object> time = null;
        @SerializedName("minutes")
        @Expose
        private List<Object> minutes = null;

        public Object getSlot() {
            return slot;
        }

        public void setSlot(Object slot) {
            this.slot = slot;
        }

        public String getRange() {
            return range;
        }

        public void setRange(String range) {
            this.range = range;
        }

        public Integer getOpen() {
            return open;
        }

        public void setOpen(Integer open) {
            this.open = open;
        }

        public List<Object> getTime() {
            return time;
        }

        public void setTime(List<Object> time) {
            this.time = time;
        }

        public List<Object> getMinutes() {
            return minutes;
        }

        public void setMinutes(List<Object> minutes) {
            this.minutes = minutes;
        }

    }

    public class Slot______ {

        @SerializedName("slot")
        @Expose
        private Object slot;
        @SerializedName("range")
        @Expose
        private String range;
        @SerializedName("open")
        @Expose
        private Integer open;
        @SerializedName("time")
        @Expose
        private List<Object> time = null;
        @SerializedName("minutes")
        @Expose
        private List<Object> minutes = null;

        public Object getSlot() {
            return slot;
        }

        public void setSlot(Object slot) {
            this.slot = slot;
        }

        public String getRange() {
            return range;
        }

        public void setRange(String range) {
            this.range = range;
        }

        public Integer getOpen() {
            return open;
        }

        public void setOpen(Integer open) {
            this.open = open;
        }

        public List<Object> getTime() {
            return time;
        }

        public void setTime(List<Object> time) {
            this.time = time;
        }

        public List<Object> getMinutes() {
            return minutes;
        }

        public void setMinutes(List<Object> minutes) {
            this.minutes = minutes;
        }

    }

    public class Su {

        @SerializedName("today")
        @Expose
        private Integer today;
        @SerializedName("closed")
        @Expose
        private Integer closed;
        @SerializedName("open")
        @Expose
        private Integer open;
        @SerializedName("day")
        @Expose
        private String day;
        @SerializedName("day_short")
        @Expose
        private String dayShort;
        @SerializedName("day_no")
        @Expose
        private Integer dayNo;
        @SerializedName("slots")
        @Expose
        private List<Slot______> slots = null;

        public Integer getToday() {
            return today;
        }

        public void setToday(Integer today) {
            this.today = today;
        }

        public Integer getClosed() {
            return closed;
        }

        public void setClosed(Integer closed) {
            this.closed = closed;
        }

        public Integer getOpen() {
            return open;
        }

        public void setOpen(Integer open) {
            this.open = open;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getDayShort() {
            return dayShort;
        }

        public void setDayShort(String dayShort) {
            this.dayShort = dayShort;
        }

        public Integer getDayNo() {
            return dayNo;
        }

        public void setDayNo(Integer dayNo) {
            this.dayNo = dayNo;
        }

        public List<Slot______> getSlots() {
            return slots;
        }

        public void setSlots(List<Slot______> slots) {
            this.slots = slots;
        }

    }

    public class Th {

        @SerializedName("today")
        @Expose
        private Integer today;
        @SerializedName("closed")
        @Expose
        private Integer closed;
        @SerializedName("open")
        @Expose
        private Integer open;
        @SerializedName("day")
        @Expose
        private String day;
        @SerializedName("day_short")
        @Expose
        private String dayShort;
        @SerializedName("day_no")
        @Expose
        private Integer dayNo;
        @SerializedName("slots")
        @Expose
        private List<Slot___> slots = null;

        public Integer getToday() {
            return today;
        }

        public void setToday(Integer today) {
            this.today = today;
        }

        public Integer getClosed() {
            return closed;
        }

        public void setClosed(Integer closed) {
            this.closed = closed;
        }

        public Integer getOpen() {
            return open;
        }

        public void setOpen(Integer open) {
            this.open = open;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getDayShort() {
            return dayShort;
        }

        public void setDayShort(String dayShort) {
            this.dayShort = dayShort;
        }

        public Integer getDayNo() {
            return dayNo;
        }

        public void setDayNo(Integer dayNo) {
            this.dayNo = dayNo;
        }

        public List<Slot___> getSlots() {
            return slots;
        }

        public void setSlots(List<Slot___> slots) {
            this.slots = slots;
        }

    }

    public class Title {

        @SerializedName("raw")
        @Expose
        private String raw;
        @SerializedName("rendered")
        @Expose
        private String rendered;

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }

    }

    public class Tu {

        @SerializedName("today")
        @Expose
        private Integer today;
        @SerializedName("closed")
        @Expose
        private Integer closed;
        @SerializedName("open")
        @Expose
        private Integer open;
        @SerializedName("day")
        @Expose
        private String day;
        @SerializedName("day_short")
        @Expose
        private String dayShort;
        @SerializedName("day_no")
        @Expose
        private Integer dayNo;
        @SerializedName("slots")
        @Expose
        private List<Slot_> slots = null;

        public Integer getToday() {
            return today;
        }

        public void setToday(Integer today) {
            this.today = today;
        }

        public Integer getClosed() {
            return closed;
        }

        public void setClosed(Integer closed) {
            this.closed = closed;
        }

        public Integer getOpen() {
            return open;
        }

        public void setOpen(Integer open) {
            this.open = open;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getDayShort() {
            return dayShort;
        }

        public void setDayShort(String dayShort) {
            this.dayShort = dayShort;
        }

        public Integer getDayNo() {
            return dayNo;
        }

        public void setDayNo(Integer dayNo) {
            this.dayNo = dayNo;
        }

        public List<Slot_> getSlots() {
            return slots;
        }

        public void setSlots(List<Slot_> slots) {
            this.slots = slots;
        }

    }

    public class VersionHistory {

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

    public class We {

        @SerializedName("today")
        @Expose
        private Integer today;
        @SerializedName("closed")
        @Expose
        private Integer closed;
        @SerializedName("open")
        @Expose
        private Integer open;
        @SerializedName("day")
        @Expose
        private String day;
        @SerializedName("day_short")
        @Expose
        private String dayShort;
        @SerializedName("day_no")
        @Expose
        private Integer dayNo;
        @SerializedName("slots")
        @Expose
        private List<Slot__> slots = null;

        public Integer getToday() {
            return today;
        }

        public void setToday(Integer today) {
            this.today = today;
        }

        public Integer getClosed() {
            return closed;
        }

        public void setClosed(Integer closed) {
            this.closed = closed;
        }

        public Integer getOpen() {
            return open;
        }

        public void setOpen(Integer open) {
            this.open = open;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getDayShort() {
            return dayShort;
        }

        public void setDayShort(String dayShort) {
            this.dayShort = dayShort;
        }

        public Integer getDayNo() {
            return dayNo;
        }

        public void setDayNo(Integer dayNo) {
            this.dayNo = dayNo;
        }

        public List<Slot__> getSlots() {
            return slots;
        }

        public void setSlots(List<Slot__> slots) {
            this.slots = slots;
        }

    }

    public class WpAttachment {

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

    public class WpFeaturedmedium {

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

    public class WpTerm {

        @SerializedName("taxonomy")
        @Expose
        private String taxonomy;
        @SerializedName("embeddable")
        @Expose
        private Boolean embeddable;
        @SerializedName("href")
        @Expose
        private String href;

        public String getTaxonomy() {
            return taxonomy;
        }

        public void setTaxonomy(String taxonomy) {
            this.taxonomy = taxonomy;
        }

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
}