package net.callofdroidy.pictureoftheday.webservice;

import net.callofdroidy.pictureoftheday.model.NasaPicture;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NasaService {
    @GET("/planetary/apod")
    Call<NasaPicture> getNasaPicture(
            @Query("api_key") String apiKey
    );
}
