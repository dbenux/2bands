package dbenux.test.a2bands.model;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = { "latitude", "longitude" })
public class Venue {

    @SerializedName("name")
    private String name;

    @SerializedName("place")
    private String place;

    @SerializedName("city")
    private String city;

    @SerializedName("region")
    private String region;

    @SerializedName("country")
    private String country;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
