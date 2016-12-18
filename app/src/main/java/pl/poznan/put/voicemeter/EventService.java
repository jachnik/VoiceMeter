package pl.poznan.put.voicemeter;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by jachnika on 15.12.2016.
 */

public interface EventService {

    @POST("/event")
    Call<Event> publishEvent(@Body Event event);
    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://172.20.10.5:8080/").addConverterFactory(GsonConverterFactory.create()).build();
}
