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

    @SerializedName("ticket_url")
    private String ticketUrl;

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public List<Artist> getArtists() {
        return artists;
    }
    public Artist getPrimaryArtist() {
        if (artists!=null && artists.size()>0) {
            return artists.get(0);

        } else {
            return null;
        }
    }

    public String getFormattedLocation() {
        return formattedLocation;
    }

    public Venue getVenue() {
        return venue;
    }

    public String getTicketUrl() {
        return ticketUrl;
    }
}
