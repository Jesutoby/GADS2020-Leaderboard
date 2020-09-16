package com.adetoyan.gadsleaderboard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VerbService {

    @GET("/api/hours")
    Call<List<RetroLearn>> getHours();

    @GET("/api/skilliq")
    Call<List<RetroSkill>> getScore();

}
