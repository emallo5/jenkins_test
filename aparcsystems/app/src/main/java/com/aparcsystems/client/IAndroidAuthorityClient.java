package com.aparcsystems.client;

import com.aparcsystems.model.Feed;
import com.aparcsystems.model.HttpPenalty;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;

public interface IAndroidAuthorityClient {

    @GET("/Citations/FindCitationDetails")
    public HttpPenalty getPenalty(@Query("ClientCitationNo")String ClientCitationNo,@Query("ClientNum")int clientNum );
}
