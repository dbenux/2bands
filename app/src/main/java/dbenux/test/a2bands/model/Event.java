package dbenux.test.a2bands.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Event {

    /* some fields in JSON response from BandsInTown API have been purposely ignored
       to focus on relevant ones */

    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("datetime")
    private Date date;

    @SerializedName("artists")
    private List<Artist> artists;

    @SerializedName("formatted_location")
    private String formattedLocation;

    @SerializedName("venue")
    private Venue venue;

}
