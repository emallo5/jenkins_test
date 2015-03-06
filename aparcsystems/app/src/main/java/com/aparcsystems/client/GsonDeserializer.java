package com.aparcsystems.client;

import android.util.Log;

import com.aparcsystems.model.Feed;
import com.aparcsystems.model.HttpPenalty;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonDeserializer implements JsonDeserializer<HttpPenalty> {



    @Override
    public HttpPenalty deserialize(JsonElement el, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        HttpPenalty penalty=gson.fromJson(el,typeOfT);
        return penalty;
    }

}
