package dbenux.test.a2bands.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Artist {

    /* some fields in JSON response from BandsInTown API have been purposely ignored
   to focus on relevant ones */

    @PrimaryKey
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("image_url")
    @ColumnInfo(name = "image_url")
    private String imageUrl;

    @SerializedName("thumb_url")
    @ColumnInfo(name = "thumb_url")
    private String thumbnailUrl;

    @SerializedName("website")
    private String websiteUrl;

    @SerializedName("facebook_tour_dates_url")
    @ColumnInfo(name = "facebook_tour_dates_url")
    private String facebookTourDatesUrl;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getFacebookTourDatesUrl() {
        return facebookTourDatesUrl;
    }
    public void setFacebookTourDatesUrl(String facebookTourDatesUrl) {
        this.facebookTourDatesUrl = facebookTourDatesUrl;
    }

}
