<%@ page import="com.marceldev.seoulpublicwifi.Models.LocationHistory" %>
<%@ page import="java.util.List" %>
<%@ page import="com.marceldev.seoulpublicwifi.Services.HistoryService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="comp/header.jsp" />

<body>
    <%
        HistoryService service = new HistoryService();

        String id = request.getParameter("deleteItemId");
        if (id != null) {
            service.delete(Integer.parseInt(id));
        }

        List<LocationHistory> histories = service.list();
    %>
    <h1>위치 히스토리 목록</h1>
    <jsp:include page="comp/menu.jsp" />

    <table class="table-list">
        <thead>
            <tr>
                <th>ID</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>조회일자</th>
                <th>비고</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (histories == null || histories.isEmpty()) {
                    out.write("<tr>");
                    out.write("<td colspan='5' style='text-align: center;'>" + "값이 없습니다." + "</td>");
                    out.write("</tr>");
                } else {
                    for (LocationHistory history : histories) {
                        out.write("<tr>");
                        out.write("<td>" + history.getId() + "</td>");
                        out.write("<td>" + history.getLatitude() + "</td>");
                        out.write("<td>" + history.getLongitude() + "</td>");
                        out.write("<td>" + history.getQueryDate() + "</td>");
                        out.write("<td style='text-align: center;'>");
                        out.write("<form action='history.jsp' method='post'>");
                        out.write("<input type='hidden' name='deleteItemId' value='" + history.getId() + "' />");
                        out.write("<button type='submit'>삭제</button>");
                        out.write("</form>");
                        out.write("</td>");
                        out.write("</tr>");
                    }
                }
            %>
        </tbody>
    </table>
</body>

<jsp:include page="comp/footer.jsp" />
