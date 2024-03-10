<%@ page import="com.marceldev.seoulpublicwifi.Services.WifiService" %>
<%@ page import="com.marceldev.seoulpublicwifi.Models.WifiInfo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="comp/header.jsp" />

<body>
    <%
        WifiService service = new WifiService();
        List<WifiInfo> wifiList = service.fetchWifiAll();

        service.emptyWifiTable();
        int affectedRows = service.saveToDB(wifiList);
    %>
    <div class="load-success">
        <h2><%= affectedRows %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h2>
        <a href="index.jsp">홈으로 가기</a>
    </div>
</body>

<jsp:include page="comp/footer.jsp" />