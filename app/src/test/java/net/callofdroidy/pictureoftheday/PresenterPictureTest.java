package net.callofdroidy.pictureoftheday;

/**
 * Created by yli on 10/08/17.
 */

import android.util.Log;

import net.callofdroidy.pictureoftheday.model.NasaPicture;
import net.callofdroidy.pictureoftheday.webservice.NasaService;

import org.junit.Test;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

public class PresenterPictureTest {
    public static final String TAG = "TestPresenterPic";

    @Test
    public void testGetNasaPicture(){
        MockWebServer mockWebServer = new MockWebServer();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\n" +
                        "  \"copyright\": \"Petr Hor\\u00e1lek\", \n" +
                        "  \"date\": \"2017-08-10\", \n" +
                        "  \"explanation\": \"This weekend, meteors will rain down near the peak of the annual Perseid Meteor Shower. Normally bright and colorful, the Perseid shower meteors are produced by dust swept up by planet Earth from the orbit of Comet Swift-Tuttle. They streak from a radiant in Perseus, above the horizon in clear predawn skies. Despite interfering light from August's waning gibbous moon, this year's Perseids will still be enjoyable, especially if you can find yourself in an open space, away from city lights, and in good company. Frames used in this composite view capture bright Perseid meteors from the 2016 meteor shower set against a starry background along the Milky Way, with even the faint Andromeda Galaxy just above center. In the foreground, astronomers of all ages have gathered on a hill above the Slovakian village of Vrchtepla.\", \n" +
                        "  \"hdurl\": \"https://apod.nasa.gov/apod/image/1708/2016_08_06_Perseids_1310pxHoralek.jpg\", \n" +
                        "  \"media_type\": \"image\", \n" +
                        "  \"service_version\": \"v1\", \n" +
                        "  \"title\": \"Night of the Perseids\", \n" +
                        "  \"url\": \"https://apod.nasa.gov/apod/image/1708/2016_08_06_Perseids_1310pxHoralek.jpg\"\n" +
                        "}")
        );

        NasaService service = retrofit.create(NasaService.class);
        Call<NasaPicture> call = service.getNasaPicture(Constant.apiKey);

        try{
            Response<NasaPicture> response = call.execute();
            assertTrue(response.code() == 200);
            assertTrue(response.body() != null);
            assertEquals(response.body().getDate(), "2017-08-10");
        }catch (IOException e){
            Log.e(TAG, "testGetNasaPicture: " + e.toString());
        }

        try{
            mockWebServer.shutdown();
        }catch (IOException e){
            Log.e(TAG, "testGetNasaPicture: " + e.toString());
        }

    }
}
