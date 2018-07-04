package dbenux.test.a2bands.model;

import com.google.gson.annotations.SerializedName;

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

}
