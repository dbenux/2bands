package dbenux.test.a2bands.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

@Entity(indices = { @Index("favorited") },
        foreignKeys = {
                @ForeignKey(entity = Artist.class,
                        parentColumns = "id",
                        childColumns = "artist_id"),
                @ForeignKey(entity = Venue.class,
                        parentColumns = {"latitude", "longitude"},
                        childColumns = {"venue_latitude", "venue_longitude"})
        }
)
public class Event {

    /* some fields in JSON response from BandsInTown API have been purposely ignored
       to focus on relevant ones */

    @PrimaryKey
    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("datetime")
    private Date date;

    // WS-only
    @Ignore
    @SerializedName("artists")
    private List<Artist> artists;

    @Ignore
    private Artist primaryArtist;

    // DAO-only
    @ColumnInfo(name = "artist_id")
    private Long artistId;

    @SerializedName("formatted_location")
    @ColumnInfo(name = "formatted_location")
    private String formattedLocation;

    // WS-only
    @Ignore
    @SerializedName("venue")
    private Venue venue;

    // DAO-only
    @ColumnInfo(name = "venue_latitude")
    private Double venueLatitude;

    // DAO-only
    @ColumnInfo(name = "venue_longitude")
    private Double venueLongitude;

    @SerializedName("ticket_url")
    @ColumnInfo(name = "ticket_url")
    private String ticketUrl;

    private boolean favorited;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Artist getPrimaryArtist() {
        if (primaryArtist==null && artists!=null && artists.size()>0) {
            return artists.get(0);

        } else {
            return primaryArtist;
        }
    }
    public void setPrimaryArtist(Artist artist) {
        this.primaryArtist = artist;
    }

    public Long getArtistId() {
        if (artistId!=null) {
            return artistId;

        } else {
            Artist primaryArtist = getPrimaryArtist();
            if (primaryArtist != null) {
                return primaryArtist.getId();
            } else {
                return null;
            }
        }
    }
    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getFormattedLocation() {
        return formattedLocation;
    }
    public void setFormattedLocation(String formattedLocation) {
        this.formattedLocation = formattedLocation;
    }

    public Venue getVenue() {
        return venue;
    }
    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getTicketUrl() {
        return ticketUrl;
    }
    public void setTicketUrl(String ticketUrl) {
        this.ticketUrl = ticketUrl;
    }

    public boolean isFavorited() {
        return favorited;
    }
    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public Double getVenueLatitude() {
        if (venueLatitude!=null) {
            return venueLatitude;

        } else if (venue!=null) {
            return venue.getLatitude();

        } else {
            return null;
        }
    }

    public void setVenueLatitude(Double venueLatitude) {
        this.venueLatitude = venueLatitude;
    }

    public Double getVenueLongitude() {
        if (venueLongitude!=null) {
            return venueLongitude;

        } else if (venue!=null) {
            return venue.getLongitude();

        } else {
            return null;
        }
    }

    public void setVenueLongitude(Double venueLongitude) {
        this.venueLongitude = venueLongitude;
    }
}
