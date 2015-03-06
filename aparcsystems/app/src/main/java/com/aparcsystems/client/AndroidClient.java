package com.aparcsystems.client;

import com.aparcsystems.BuildConfig;
import com.aparcsystems.model.Feed;
import com.aparcsystems.model.HttpPenalty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Singleton;

import org.apache.http.protocol.HTTP;

import java.lang.reflect.Type;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * This class is the responsable to connect to the API. Here lies all the
 * connections between the client and the server.<br>
 * As <b>Jake Wharton</b> said:
 * "RestAdapter and the created instances should be treated as singletons"
 */
@Singleton
public class AndroidClient {

    private static final String BASE_URL = "http://54.172.212.50/ParktoriaEnforcementAPI_Dev/api";
    private RestAdapter restAdapter;
    private IAndroidAuthorityClient androidAuthorityClient;


    public AndroidClient() {
        if (restAdapter == null || androidAuthorityClient == null) {
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(BASE_URL)
                    .build();

            if(BuildConfig.DEBUG) { // Good old trick
                restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
            }else {
                restAdapter.setLogLevel(RestAdapter.LogLevel.NONE);
            }

            androidAuthorityClient = restAdapter.create(IAndroidAuthorityClient.class);
        }
    }

    public HttpPenalty getPenalty(String penaltyCode) {
        return androidAuthorityClient.getPenalty(penaltyCode,1);
    }

}
