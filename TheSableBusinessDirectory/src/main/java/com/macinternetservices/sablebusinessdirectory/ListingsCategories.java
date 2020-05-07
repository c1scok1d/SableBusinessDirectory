package com.macinternetservices.sablebusinessdirectory;

        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class ListingsCategories {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("meta")
    @Expose
    private List<Object> meta = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("taxonomy")
    @Expose
    private String taxonomy;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("parent")
    @Expose
    private Integer parent;
    @SerializedName("image")
    @Expose
    private List<Object> image = null;
   /* @SerializedName("icon")
    @Expose
    private Icon icon; */
    @SerializedName("fa_icon")
    @Expose
    private String faIcon;
    @SerializedName("fa_icon_color")
    @Expose
    private String faIconColor;
    @SerializedName("schema")
    @Expose
    private String schema;

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

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Object> getMeta() {
        return meta;
    }

    public void setMeta(List<Object> meta) {
        this.meta = meta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public List<Object> getImage() {
        return image;
    }

    public void setImage(List<Object> image) {
        this.image = image;
    }

  /*  public Icon getIcon() {
        return icon;
    } */

   /* public void setIcon(Icon icon) {
        this.icon = icon;
    }*/

    public String getFaIcon() {
        return faIcon;
    }

    public void setFaIcon(String faIcon) {
        this.faIcon = faIcon;
    }

    public String getFaIconColor() {
        return faIconColor;
    }

    public void setFaIconColor(String faIconColor) {
        this.faIconColor = faIconColor;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
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

    public class WpPostType {

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

    public class Icon {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("date_created")
        @Expose
        private String dateCreated;
        @SerializedName("date_created_gmt")
        @Expose
        private String dateCreatedGmt;
        @SerializedName("date_modified")
        @Expose
        private String dateModified;
        @SerializedName("date_modified_gmt")
        @Expose
        private String dateModifiedGmt;
        @SerializedName("src")
        @Expose
        private String src;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("alt")
        @Expose
        private String alt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getDateCreated() {
            return dateCreated;
        }

        public void setDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
        }

        public String getDateCreatedGmt() {
            return dateCreatedGmt;
        }

        public void setDateCreatedGmt(String dateCreatedGmt) {
            this.dateCreatedGmt = dateCreatedGmt;
        }

        public String getDateModified() {
            return dateModified;
        }

        public void setDateModified(String dateModified) {
            this.dateModified = dateModified;
        }

        public String getDateModifiedGmt() {
            return dateModifiedGmt;
        }

        public void setDateModifiedGmt(String dateModifiedGmt) {
            this.dateModifiedGmt = dateModifiedGmt;
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

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
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
        @SerializedName("curies")
        @Expose
        private List<Cury> curies = null;
        @SerializedName("wp:post_type")
        @Expose
        private List<WpPostType> wpPostType = null;

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

        public List<Cury> getCuries() {
            return curies;
        }

        public void setCuries(List<Cury> curies) {
            this.curies = curies;
        }

        public List<WpPostType> getWpPostType() {
            return wpPostType;
        }

        public void setWpPostType(List<WpPostType> wpPostType) {
            this.wpPostType = wpPostType;
        }

    }
}
