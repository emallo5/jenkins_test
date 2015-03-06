package com.aparcsystems.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by emi91_000 on 28/02/2015.
 */
public class PenaltyError {

    @SerializedName("Message")
    public String message;

    public PenaltyError() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
