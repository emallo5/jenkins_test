package com.aparcsystems.mock;

import com.aparcsystems.model.Penalty;
import com.google.gson.Gson;

/**
 * Created by emi91_000 on 20/02/2015.
 */
public class PenaltyMocks {

    public static Penalty getStandartPenalty(){
        String json="{\n" +
                "    \"CitationId\": 145679,\n" +
                "    \"ClientCitationNo\": \"XX315750\",\n" +
                "    \"IssueDateTime\":\"2013-01-01T00:00:00\",\n" +
                "    \"OfficerId\": \"005\",\n" +
                "    \"Plate\": \"DEX512\",\n" +
                "    \"Province\": \"BC\",\n" +
                "    \"VehicleMake\": \"Acura\",\n" +
                "    \"MeterId\": \"1234455\",\n" +
                "    \"Location\": \"Cedar lot\",\n" +
                "    \"OffenceCode\": \"5.02-3\",\n" +
                "    \"ViolationDescription\": \"Reserved parking - permiting required\",\n" +
                "    \"PublicComment1\": \"To private property\",\n" +
                "    \"PublicComment2\": \"\",\n" +
                "    \"PublicComment3\": \"\",\n" +
                "    \"Amount\": 15.0,\n" +
                "    \"IsDisputed\": false,\n" +
                "    \"IsPaid\": false,\n" +
                "    \"PhotoCount\": 4\n" +
                "}";
        Gson gson=new Gson();
        Penalty penalty= gson.fromJson(json,Penalty.class);
        return penalty;
    }

    public static Penalty getPaidPenalty(){
        String json="{\n" +
                "    \"CitationId\": 145679,\n" +
                "    \"ClientCitationNo\": \"XX315750\",\n" +
                "    \"IssueDateTime\":\"2013-01-01T00:00:00\",\n" +
                "    \"OfficerId\": \"005\",\n" +
                "    \"Plate\": \"DEX512\",\n" +
                "    \"Province\": \"BC\",\n" +
                "    \"VehicleMake\": \"Acura\",\n" +
                "    \"MeterId\": \"1234455\",\n" +
                "    \"Location\": \"Cedar lot\",\n" +
                "    \"OffenceCode\": \"5.02-3\",\n" +
                "    \"ViolationDescription\": \"Reserved parking - permiting required\",\n" +
                "    \"PublicComment1\": \"To private property\",\n" +
                "    \"PublicComment2\": \"\",\n" +
                "    \"PublicComment3\": \"\",\n" +
                "    \"Amount\": 15.0,\n" +
                "    \"IsDisputed\": false,\n" +
                "    \"IsPaid\": true,\n" +
                "    \"PhotoCount\": 4\n" +
                "}";
        Gson gson=new Gson();
        Penalty penalty= gson.fromJson(json,Penalty.class);
        return penalty;
    }

    public static Penalty getDisputePenalty(){
        String json="{\n" +
                "    \"CitationId\": 145679,\n" +
                "    \"ClientCitationNo\": \"XX315750\",\n" +
                "    \"IssueDateTime\":\"2013-01-01T00:00:00\",\n" +
                "    \"OfficerId\": \"005\",\n" +
                "    \"Plate\": \"DEX512\",\n" +
                "    \"Province\": \"BC\",\n" +
                "    \"VehicleMake\": \"Acura\",\n" +
                "    \"MeterId\": \"1234455\",\n" +
                "    \"Location\": \"Cedar lot\",\n" +
                "    \"OffenceCode\": \"5.02-3\",\n" +
                "    \"ViolationDescription\": \"Reserved parking - permiting required\",\n" +
                "    \"PublicComment1\": \"To private property\",\n" +
                "    \"PublicComment2\": \"\",\n" +
                "    \"PublicComment3\": \"\",\n" +
                "    \"Amount\": 15.0,\n" +
                "    \"IsDisputed\": true,\n" +
                "    \"IsPaid\": false,\n" +
                "    \"PhotoCount\": 4\n" +
                "}";
        Gson gson=new Gson();
        Penalty penalty= gson.fromJson(json,Penalty.class);
        return penalty;
    }
}
