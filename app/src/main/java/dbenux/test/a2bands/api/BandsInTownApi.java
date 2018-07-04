package dbenux.test.a2bands.api;

import java.util.List;

import dbenux.test.a2bands.model.Event;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BandsInTownApi {

    String BASE_URL = "https://api.bandsintown.com";

    String APP_ID = "2Bands";

    @GET("artists/{artist}/events.json?api_version=2.0&app_id=" + APP_ID)
    Call<List<Event>> getEventsByArtist(@Path("artist") String artistName);

}
