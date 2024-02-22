package com.todo.ToDo.model.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"status","apiName","data", "errorCode", "errorMsg"})
public class BaseResponse {



    @JsonProperty("apiName")
    private String apiName;

    @JsonProperty("data")
    private Object data;

    @JsonProperty("errorCode")
    private int errorCode;

    @JsonProperty("status")
    private boolean status;

    @JsonProperty("errorMsg")
    private String errorMsg = "";

}