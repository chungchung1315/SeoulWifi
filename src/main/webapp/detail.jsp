<%@ page import="com.marceldev.seoulpublicwifi.Models.WifiInfo" %>
<%@ page import="com.marceldev.seoulpublicwifi.Services.WifiService" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="com.marceldev.seoulpublicwifi.Services.BookmarkGroupService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.marceldev.seoulpublicwifi.Models.BookmarkGroup" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="comp/header.jsp" />

<body>
    <%
        // bookmark group 정보 불러오기
        BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
        List<BookmarkGroup> groups = bookmarkGroupService.list();

        // wifi 정보 불러오기
        WifiService service = new WifiService();
        String manageNumber = request.getParameter("manageNumber");
        WifiInfo wifi = null;
        if (manageNumber != null) {
            wifi = service.detail(manageNumber);
        }
    %>

    <h1><%= "와이파이 정보 구하기" %></h1>
    <jsp:include page="comp/menu.jsp" />

    <form action="bookmark-add-submit.jsp" method="post">
        <select id="bookmark-group-id" name="bookmark-group-id">
            <option value="">북마크 그룹 이름 선택</option>
            <%
                for (BookmarkGroup group : groups) {
                    out.write("<option value=" + group.getId() + ">" +
                            group.getBookmarkName() +
                            "</option>");
                }
            %>
        </select>
        <input type="hidden" id="wifi-manage-number" name="wifi-manage-number" value="<%= wifi.getManageNumber() %>" />
        <input type="submit" value="북마크 추가하기" />
    </form>

    <table class="table-list">
        <tbody>
            <%
                Map<String, String> map = new LinkedHashMap<>();
                map.put("거리(Km)", "0.0000");
                map.put("관리번호", wifi.getManageNumber());
                map.put("자치구", wifi.getManageCity());
                map.put("와이파이명", wifi.getWifiName());
                map.put("도로명주소", wifi.getAddressRoad());
                map.put("상세주소", wifi.getAddressDetail());
                map.put("설치위치(층)", wifi.getInstallFloor());
                map.put("설치유형", wifi.getInstallType());
                map.put("설치기관", wifi.getInstallInstitute());
                map.put("서비스구분", wifi.getServiceKind());
                map.put("망종류", wifi.getWifiKind());
                map.put("설치년도", wifi.getInstallYear());
                map.put("설내외구분", wifi.getInOutDoor());
                map.put("WIFI접속환경", wifi.getConnectionEnv());
                map.put("X좌표", wifi.getLatitude());
                map.put("Y좌표", wifi.getLongitude());
                map.put("작업일자", wifi.getWorkDate());

                for (Map.Entry<String, String> entry : map.entrySet()) {
                    out.write("<tr>");
                    out.write("<th>" + entry.getKey() + "</th>");
                    out.write("<td>" + entry.getValue() + "</td>");
                    out.write("</tr>");
                }
            %>
        </tbody>
    </table>
</body>

<jsp:include page="comp/footer.jsp" />
