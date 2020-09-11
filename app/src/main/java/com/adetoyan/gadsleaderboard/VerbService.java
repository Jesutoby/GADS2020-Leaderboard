package com.adetoyan.gadsleaderboard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface VerbService {

    @GET("/api/hours")
    Call<List<RetroLearn>> getHours();

    @GET("/api/skilliq")
    Call<List<RetroSkill>> getScore();

}
