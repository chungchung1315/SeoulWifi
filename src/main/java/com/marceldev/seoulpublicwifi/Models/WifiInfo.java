package com.marceldev.seoulpublicwifi.Models;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class WifiInfo {
    @SerializedName("X_SWIFI_MGR_NO")
    private String manageNumber;

    @SerializedName("X_SWIFI_WRDOFC")
    private String manageCity;

    @SerializedName("X_SWIFI_MAIN_NM")
    private String wifiName;

    @SerializedName("X_SWIFI_ADRES1")
    private String addressRoad;

    @SerializedName("X_SWIFI_ADRES2")
    private String addressDetail;

    @SerializedName("X_SWIFI_INSTL_FLOOR")
    private String installFloor;

    @SerializedName("X_SWIFI_INSTL_TY")
    private String installType;

    @SerializedName("X_SWIFI_INSTL_MBY")
    private String installInstitute;

    @SerializedName("X_SWIFI_SVC_SE")
    private String serviceKind;

    @SerializedName("X_SWIFI_CMCWR")
    private String wifiKind;

    @SerializedName("X_SWIFI_CNSTC_YEAR")
    private String installYear;

    @SerializedName("X_SWIFI_INOUT_DOOR")
    private String inOutDoor;

    @SerializedName("X_SWIFI_REMARS3")
    private String connectionEnv;

    @SerializedName("LAT")
    private String latitude;

    @SerializedName("LNT")
    private String longitude;

    @SerializedName("WORK_DTTM")
    private String workDate;

    private Double distance;

    @Override
    public String toString() {
        return String.format("name: %s, lat: %s, lnt: %s", wifiName, latitude, longitude);
    }
}