package dbenux.test.a2bands.api;

import retrofit2.Retrofit;

public class Api {

    private static BandsInTownApi instance;
    public synchronized static BandsInTownApi getInstance() {
        if (instance==null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BandsInTownApi.BASE_URL)
                    .build();

            instance = retrofit.create(BandsInTownApi.class);
        }
        return instance;
    }

}
