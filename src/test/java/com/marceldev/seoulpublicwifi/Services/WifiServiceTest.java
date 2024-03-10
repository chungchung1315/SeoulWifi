package com.marceldev.seoulpublicwifi.Services;

import com.google.gson.Gson;
import com.marceldev.seoulpublicwifi.Constants;
import com.marceldev.seoulpublicwifi.Models.WifiInfo;

import java.util.List;

public class WifiServiceTest {
    public static void main(String[] args) {
        // Stateful
        //new WifiServiceTest().testFetchFromRemote(); // empty and save to DB

        // Stateless
        new WifiServiceTest().testConvertJson();
        new WifiServiceTest().testMakeFetchUrl();
    }

    private void testFetchFromRemote() {
        WifiService service = new WifiService();
        List<WifiInfo> wifiInfos = service.fetchWifiAll();

        service.emptyWifiTable();
        int affectedRows = service.saveToDB(wifiInfos);
        System.out.println("inserted rows = " + affectedRows);
    }

    private void testConvertJson() {
        WifiService service = new WifiService();
        String json = "{\n" +
                "  \"TbPublicWifiInfo\": {\n" +
                "    \"list_total_count\": 23795,\n" +
                "    \"RESULT\": { \"CODE\": \"INFO-000\", \"MESSAGE\": \"정상 처리되었습니다\" },\n" +
                "    \"row\": [\n" +
                "      {\n" +
                "        \"X_SWIFI_MGR_NO\": \"-WF171016\",\n" +
                "        \"X_SWIFI_WRDOFC\": \"중구\",\n" +
                "        \"X_SWIFI_MAIN_NM\": \"다동어린이공원\",\n" +
                "        \"X_SWIFI_ADRES1\": \"다동 51-2\",\n" +
                "        \"X_SWIFI_ADRES2\": \"을지로 3길 49(다동 51-2, 공-16)\",\n" +
                "        \"X_SWIFI_INSTL_FLOOR\": \"\",\n" +
                "        \"X_SWIFI_INSTL_TY\": \"3. 공원(하천)\",\n" +
                "        \"X_SWIFI_INSTL_MBY\": \"협력형_서울(SKT)\",\n" +
                "        \"X_SWIFI_SVC_SE\": \"공공WiFi\",\n" +
                "        \"X_SWIFI_CMCWR\": \"자가망U-무선망\",\n" +
                "        \"X_SWIFI_CNSTC_YEAR\": \"2017\",\n" +
                "        \"X_SWIFI_INOUT_DOOR\": \"실외\",\n" +
                "        \"X_SWIFI_REMARS3\": \"\",\n" +
                "        \"LAT\": \"37.56817\",\n" +
                "        \"LNT\": \"126.98076\",\n" +
                "        \"WORK_DTTM\": \"2024-03-02 11:12:42.0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"X_SWIFI_MGR_NO\": \"-WF171017\",\n" +
                "        \"X_SWIFI_WRDOFC\": \"중구\",\n" +
                "        \"X_SWIFI_MAIN_NM\": \"다동어린이공원\",\n" +
                "        \"X_SWIFI_ADRES1\": \"다동 51-2\",\n" +
                "        \"X_SWIFI_ADRES2\": \"을지로 3길 49(다동 51-2, 공-16)\",\n" +
                "        \"X_SWIFI_INSTL_FLOOR\": \"\",\n" +
                "        \"X_SWIFI_INSTL_TY\": \"3. 공원(하천)\",\n" +
                "        \"X_SWIFI_INSTL_MBY\": \"협력형_서울(SKT)\",\n" +
                "        \"X_SWIFI_SVC_SE\": \"공공WiFi\",\n" +
                "        \"X_SWIFI_CMCWR\": \"자가망U-무선망\",\n" +
                "        \"X_SWIFI_CNSTC_YEAR\": \"2017\",\n" +
                "        \"X_SWIFI_INOUT_DOOR\": \"실외\",\n" +
                "        \"X_SWIFI_REMARS3\": \"\",\n" +
                "        \"LAT\": \"37.56817\",\n" +
                "        \"LNT\": \"126.98076\",\n" +
                "        \"WORK_DTTM\": \"2024-03-02 11:12:42.0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"X_SWIFI_MGR_NO\": \"-WF171018\",\n" +
                "        \"X_SWIFI_WRDOFC\": \"중구\",\n" +
                "        \"X_SWIFI_MAIN_NM\": \"금호터널위쉼터\",\n" +
                "        \"X_SWIFI_ADRES1\": \"신당동 844-5\",\n" +
                "        \"X_SWIFI_ADRES2\": \"동호로5길 19(신당동 844-5, 공-43)\",\n" +
                "        \"X_SWIFI_INSTL_FLOOR\": \"SKT고장시 자동해지\",\n" +
                "        \"X_SWIFI_INSTL_TY\": \"3. 공원(하천)\",\n" +
                "        \"X_SWIFI_INSTL_MBY\": \"협력형_서울(SKT)\",\n" +
                "        \"X_SWIFI_SVC_SE\": \"공공WiFi\",\n" +
                "        \"X_SWIFI_CMCWR\": \"자가망U-무선망\",\n" +
                "        \"X_SWIFI_CNSTC_YEAR\": \"2017\",\n" +
                "        \"X_SWIFI_INOUT_DOOR\": \"실외\",\n" +
                "        \"X_SWIFI_REMARS3\": \"\",\n" +
                "        \"LAT\": \"37.55187\",\n" +
                "        \"LNT\": \"127.01254\",\n" +
                "        \"WORK_DTTM\": \"2024-03-02 11:12:42.0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"X_SWIFI_MGR_NO\": \"-WF171019\",\n" +
                "        \"X_SWIFI_WRDOFC\": \"중구\",\n" +
                "        \"X_SWIFI_MAIN_NM\": \"금호터널위쉼터\",\n" +
                "        \"X_SWIFI_ADRES1\": \"신당동 844-5\",\n" +
                "        \"X_SWIFI_ADRES2\": \"동호로5길 19(신당동 844-5, 공-43)\",\n" +
                "        \"X_SWIFI_INSTL_FLOOR\": \"SKT고장시 자동해지\",\n" +
                "        \"X_SWIFI_INSTL_TY\": \"3. 공원(하천)\",\n" +
                "        \"X_SWIFI_INSTL_MBY\": \"협력형_서울(SKT)\",\n" +
                "        \"X_SWIFI_SVC_SE\": \"공공WiFi\",\n" +
                "        \"X_SWIFI_CMCWR\": \"자가망U-무선망\",\n" +
                "        \"X_SWIFI_CNSTC_YEAR\": \"2017\",\n" +
                "        \"X_SWIFI_INOUT_DOOR\": \"실외\",\n" +
                "        \"X_SWIFI_REMARS3\": \"\",\n" +
                "        \"LAT\": \"37.55187\",\n" +
                "        \"LNT\": \"127.01254\",\n" +
                "        \"WORK_DTTM\": \"2024-03-02 11:12:42.0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"X_SWIFI_MGR_NO\": \"-WF171020\",\n" +
                "        \"X_SWIFI_WRDOFC\": \"중구\",\n" +
                "        \"X_SWIFI_MAIN_NM\": \"순화공원광장뒤\",\n" +
                "        \"X_SWIFI_ADRES1\": \"순화동 151\",\n" +
                "        \"X_SWIFI_ADRES2\": \"칠패로 27(순화동 151, 공-6)염천교앞공원\",\n" +
                "        \"X_SWIFI_INSTL_FLOOR\": \"SKT고장시 자동해지\",\n" +
                "        \"X_SWIFI_INSTL_TY\": \"3. 공원(하천)\",\n" +
                "        \"X_SWIFI_INSTL_MBY\": \"협력형_서울(SKT)\",\n" +
                "        \"X_SWIFI_SVC_SE\": \"공공WiFi\",\n" +
                "        \"X_SWIFI_CMCWR\": \"자가망U-무선망\",\n" +
                "        \"X_SWIFI_CNSTC_YEAR\": \"2017\",\n" +
                "        \"X_SWIFI_INOUT_DOOR\": \"실외\",\n" +
                "        \"X_SWIFI_REMARS3\": \"\",\n" +
                "        \"LAT\": \"37.5603\",\n" +
                "        \"LNT\": \"126.97199\",\n" +
                "        \"WORK_DTTM\": \"2024-03-02 11:12:42.0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"X_SWIFI_MGR_NO\": \"-WF171021\",\n" +
                "        \"X_SWIFI_WRDOFC\": \"중구\",\n" +
                "        \"X_SWIFI_MAIN_NM\": \"순화공원광장뒤\",\n" +
                "        \"X_SWIFI_ADRES1\": \"순화동 151\",\n" +
                "        \"X_SWIFI_ADRES2\": \"칠패로 27(순화동 151, 공-6)염천교앞공원\",\n" +
                "        \"X_SWIFI_INSTL_FLOOR\": \"SKT고장시 자동해지\",\n" +
                "        \"X_SWIFI_INSTL_TY\": \"3. 공원(하천)\",\n" +
                "        \"X_SWIFI_INSTL_MBY\": \"협력형_서울(SKT)\",\n" +
                "        \"X_SWIFI_SVC_SE\": \"공공WiFi\",\n" +
                "        \"X_SWIFI_CMCWR\": \"자가망U-무선망\",\n" +
                "        \"X_SWIFI_CNSTC_YEAR\": \"2017\",\n" +
                "        \"X_SWIFI_INOUT_DOOR\": \"실외\",\n" +
                "        \"X_SWIFI_REMARS3\": \"\",\n" +
                "        \"LAT\": \"37.5603\",\n" +
                "        \"LNT\": \"126.97199\",\n" +
                "        \"WORK_DTTM\": \"2024-03-02 11:12:42.0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"X_SWIFI_MGR_NO\": \"-WF171022\",\n" +
                "        \"X_SWIFI_WRDOFC\": \"중구\",\n" +
                "        \"X_SWIFI_MAIN_NM\": \"명동거리 1\",\n" +
                "        \"X_SWIFI_ADRES1\": \"명동2가 1-1\",\n" +
                "        \"X_SWIFI_ADRES2\": \"명동2가 1-1(명동성당앞, 명동-308)\",\n" +
                "        \"X_SWIFI_INSTL_FLOOR\": \"\",\n" +
                "        \"X_SWIFI_INSTL_TY\": \"1. 주요거리\",\n" +
                "        \"X_SWIFI_INSTL_MBY\": \"협력형_서울(SKT)\",\n" +
                "        \"X_SWIFI_SVC_SE\": \"공공WiFi\",\n" +
                "        \"X_SWIFI_CMCWR\": \"자가망U-무선망\",\n" +
                "        \"X_SWIFI_CNSTC_YEAR\": \"2017\",\n" +
                "        \"X_SWIFI_INOUT_DOOR\": \"실외\",\n" +
                "        \"X_SWIFI_REMARS3\": \"\",\n" +
                "        \"LAT\": \"37.564083\",\n" +
                "        \"LNT\": \"126.98628\",\n" +
                "        \"WORK_DTTM\": \"2024-03-02 11:12:42.0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"X_SWIFI_MGR_NO\": \"-WF171023\",\n" +
                "        \"X_SWIFI_WRDOFC\": \"중구\",\n" +
                "        \"X_SWIFI_MAIN_NM\": \"명동거리 1\",\n" +
                "        \"X_SWIFI_ADRES1\": \"명동2가 1-1\",\n" +
                "        \"X_SWIFI_ADRES2\": \"명동2가 1-1(명동성당앞, 명동-308)\",\n" +
                "        \"X_SWIFI_INSTL_FLOOR\": \"\",\n" +
                "        \"X_SWIFI_INSTL_TY\": \"1. 주요거리\",\n" +
                "        \"X_SWIFI_INSTL_MBY\": \"협력형_서울(SKT)\",\n" +
                "        \"X_SWIFI_SVC_SE\": \"공공WiFi\",\n" +
                "        \"X_SWIFI_CMCWR\": \"자가망U-무선망\",\n" +
                "        \"X_SWIFI_CNSTC_YEAR\": \"2017\",\n" +
                "        \"X_SWIFI_INOUT_DOOR\": \"실외\",\n" +
                "        \"X_SWIFI_REMARS3\": \"\",\n" +
                "        \"LAT\": \"37.564083\",\n" +
                "        \"LNT\": \"126.98628\",\n" +
                "        \"WORK_DTTM\": \"2024-03-02 11:12:42.0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"X_SWIFI_MGR_NO\": \"-WF171024\",\n" +
                "        \"X_SWIFI_WRDOFC\": \"중구\",\n" +
                "        \"X_SWIFI_MAIN_NM\": \"명동거리 2\",\n" +
                "        \"X_SWIFI_ADRES1\": \"저동1가 43-3\",\n" +
                "        \"X_SWIFI_ADRES2\": \"저동1가 43-3(명동-309)\",\n" +
                "        \"X_SWIFI_INSTL_FLOOR\": \"\",\n" +
                "        \"X_SWIFI_INSTL_TY\": \"1. 주요거리\",\n" +
                "        \"X_SWIFI_INSTL_MBY\": \"협력형_서울(SKT)\",\n" +
                "        \"X_SWIFI_SVC_SE\": \"공공WiFi\",\n" +
                "        \"X_SWIFI_CMCWR\": \"자가망U-무선망\",\n" +
                "        \"X_SWIFI_CNSTC_YEAR\": \"2017\",\n" +
                "        \"X_SWIFI_INOUT_DOOR\": \"실외\",\n" +
                "        \"X_SWIFI_REMARS3\": \"\",\n" +
                "        \"LAT\": \"37.56471\",\n" +
                "        \"LNT\": \"126.98707\",\n" +
                "        \"WORK_DTTM\": \"2024-03-02 11:12:42.0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"X_SWIFI_MGR_NO\": \"-WF171025\",\n" +
                "        \"X_SWIFI_WRDOFC\": \"중구\",\n" +
                "        \"X_SWIFI_MAIN_NM\": \"명동거리 2\",\n" +
                "        \"X_SWIFI_ADRES1\": \"저동1가 43-3\",\n" +
                "        \"X_SWIFI_ADRES2\": \"저동1가 43-3(명동-309)\",\n" +
                "        \"X_SWIFI_INSTL_FLOOR\": \"\",\n" +
                "        \"X_SWIFI_INSTL_TY\": \"1. 주요거리\",\n" +
                "        \"X_SWIFI_INSTL_MBY\": \"협력형_서울(SKT)\",\n" +
                "        \"X_SWIFI_SVC_SE\": \"공공WiFi\",\n" +
                "        \"X_SWIFI_CMCWR\": \"자가망U-무선망\",\n" +
                "        \"X_SWIFI_CNSTC_YEAR\": \"2017\",\n" +
                "        \"X_SWIFI_INOUT_DOOR\": \"실외\",\n" +
                "        \"X_SWIFI_REMARS3\": \"\",\n" +
                "        \"LAT\": \"37.56465\",\n" +
                "        \"LNT\": \"126.98736\",\n" +
                "        \"WORK_DTTM\": \"2024-03-02 11:12:42.0\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}\n";

        Gson gson = new Gson();
        List<WifiInfo> wifis = gson.fromJson(json, WifiResponseBody.class).getInfo().getRow();
        for (WifiInfo wifi : wifis) {
            System.out.println(wifi);
        }
    }

    private void testMakeFetchUrl() {
        String url = new WifiService().makeFetchUrl(1, 1000);
        if (!url.equals("http://openapi.seoul.go.kr:8088/" + Constants.WIFI_API_KEY + "/json/TbPublicWifiInfo/1/1000/")) {
            throw new RuntimeException();
        }

        url = new WifiService().makeFetchUrl(101, 100);
        if (!url.equals("http://openapi.seoul.go.kr:8088/" + Constants.WIFI_API_KEY + "/json/TbPublicWifiInfo/101/200/")) {
            throw new RuntimeException();
        }
    }
}
