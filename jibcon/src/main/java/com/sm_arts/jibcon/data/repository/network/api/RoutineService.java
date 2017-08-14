package com.sm_arts.jibcon.data.repository.network.api;

import com.sm_arts.jibcon.data.models.api.dto.Routine;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by jaeyoung on 8/14/17.
 */

public interface RoutineService {
    @GET("/api/routines/")
    Call<List<Routine>> getRoutines(@Header("Authorization")String token);

    @POST("/api/routines/")
    Call<Routine> postRoutine(@Header("Authorization") String token,
                              @Body Routine routine);

    @GET("/api/routines/{id}/")
    Call<Routine> getRoutine(@Header("Authorization") String token,
                                   @Path("id") String id);

    @PUT("/api/routines/{id}/")
    Call<Routine> putRoutine(@Header("Authorization") String token,
                             @Path("id") String id,
                             @Body Routine routine);

    @DELETE("/api/routines/{id}/")
    Call<Object> deleteRoutine(@Header("Authorization") String token,
                               @Path("id") String id);
}
