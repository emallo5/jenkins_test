package com.aparcsystems.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by emi91_000 on 26/02/2015.
 */
public class HttpPenalty {
    @SerializedName("CitationForPayment")
    private List<Penalty> penalty;

    public Penalty getPenalty() {
        return penalty.get(0);
    }

    public void setPenalty(List<Penalty> penalty) {
        this.penalty = penalty;
    }
}
