package dbenux.test.a2bands.model;

import com.google.gson.annotations.SerializedName;

public class Artist {

    /* some fields in JSON response from BandsInTown API have been purposely ignored
   to focus on relevant ones */

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("thumb_url")
    private String thumbnailUrl;

    @SerializedName("website")
    private String websiteUrl;

    @SerializedName("facebook_tour_dates_url")
    private String facebookTourDatesUrl;

}
