package com.marceldev.seoulpublicwifi.Services;

import com.google.gson.annotations.SerializedName;
import com.marceldev.seoulpublicwifi.Models.WifiInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class WifiResponseBody {

    @SerializedName("TbPublicWifiInfo")
    private WifiResponseInfo info;
}

@Getter
class WifiResponseInfo {
    @SerializedName("list_total_count")
    private int totalCount;

    @SerializedName("RESULT")
    private WifiResponseResult result;

    @SerializedName("row")
    private List<WifiInfo> row;
}

class WifiResponseResult {
    @SerializedName("CODE")
    private String code;
    @SerializedName("MESSAGE")
    private String message;
}