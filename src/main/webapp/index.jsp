<%@ page import="com.marceldev.seoulpublicwifi.Services.WifiService" %>
<%@ page import="com.marceldev.seoulpublicwifi.Models.WifiInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.marceldev.seoulpublicwifi.DBConfig" %>
<%@ page import="com.marceldev.seoulpublicwifi.Services.HistoryService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="comp/header.jsp" />

<body>
<%--        <% DBConfig.deleteDB(); %>--%>
    <% DBConfig.createDBIfNotPresent(); %>

    <h1><%= "와이파이 정보 구하기" %></h1>
    <jsp:include page="comp/menu.jsp" />

    <form class="sub-menu" action="index.jsp" method="get">
        <label for="latitude">LAT:</label>
        <input type="text" id="latitude" name="latitude" value="<%= request.getParameter("latitude") != null ? request.getParameter("latitude") : "0.0" %>" />
        <label for="longitude">, LNT:</label>
        <input type="text" id="longitude" name="longitude" value="<%= request.getParameter("longitude") != null ? request.getParameter("longitude") : "0.0" %>" />

        <div class="button-container">
            <button type="button" onclick="getLocation()" id="get-location-button">내 위치 가져오기</button>
            <div class="loader" id="loader"></div>
        </div>

        <input type="submit" value="근처 WIFI 정보 보기">
    </form>

    <%
        String lat = request.getParameter("latitude");
        String lnt = request.getParameter("longitude");
        double latDouble = Double.parseDouble(lat != null ? lat : "0");
        double lntDouble = Double.parseDouble(lnt != null ? lnt : "0");

        List<WifiInfo> wifis = new ArrayList<>();

        if (latDouble != 0 && lntDouble != 0) {
            WifiService wifiService = new WifiService();
            wifis = wifiService.list(latDouble, lntDouble);

            HistoryService historyService = new HistoryService();
            historyService.add(latDouble, lntDouble);
        }
    %>

    <table class="table-list">
        <thead>
            <tr>
                <th>거리(Km)</th>
                <th>관리번호</th>
                <th>자치구</th>
                <th>와이파이명</th>
                <th>도로명주소</th>
                <th>상세주소</th>
                <th>설치위치(층)</th>
                <th>설치유형</th>
                <th>설치기관</th>
                <th>서비스구분</th>
                <th>망종류</th>
                <th>설치년도</th>
                <th>설내외구분</th>
                <th>WIFI접속환경</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>작업일자</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (wifis.isEmpty()) {
                    out.write("<tr>");
                    out.write("<td colspan='17' style='text-align: center;'>" + "위치 정보를 입력한 후에 조회해 주세요." + "</td>");
                    out.write("</tr>");
                } else {
                    for (WifiInfo wifi : wifis) {
                        out.write("<tr>");
                        out.write("<td>" + String.format("%.4f", wifi.getDistance()) + "</td>");
                        out.write("<td>" + wifi.getManageNumber() + "</td>");
                        out.write("<td>" + wifi.getManageCity() + "</td>");
                        out.write("<td>" +
                                "<a href='detail.jsp?manageNumber=" + wifi.getManageNumber() + "'>" +
                                wifi.getWifiName() +
                                "</a>" +
                                "</td>"
                        );
                        out.write("<td>" + wifi.getAddressRoad() + "</td>");
                        out.write("<td>" + wifi.getAddressDetail() + "</td>");
                        out.write("<td>" + wifi.getInstallFloor() + "</td>");
                        out.write("<td>" + wifi.getInstallType() + "</td>");
                        out.write("<td>" + wifi.getInstallInstitute() + "</td>");
                        out.write("<td>" + wifi.getServiceKind() + "</td>");
                        out.write("<td>" + wifi.getWifiKind() + "</td>");
                        out.write("<td>" + wifi.getInstallYear() + "</td>");
                        out.write("<td>" + wifi.getInOutDoor() + "</td>");
                        out.write("<td>" + wifi.getConnectionEnv() + "</td>");
                        out.write("<td>" + wifi.getLatitude() + "</td>");
                        out.write("<td>" + wifi.getLongitude() + "</td>");
                        out.write("<td>" + wifi.getWorkDate() + "</td>");
                        out.write("</tr>");
                    }
                }
            %>
        </tbody>
    </table>
</body>

<jsp:include page="comp/footer.jsp" />