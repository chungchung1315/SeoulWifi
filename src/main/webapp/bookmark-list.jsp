<%@ page import="com.marceldev.seoulpublicwifi.Services.BookmarkService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.marceldev.seoulpublicwifi.Models.Bookmark" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="comp/header.jsp" />

<body>
    <%
        BookmarkService service = new BookmarkService();
        List<Bookmark> list = service.list();
    %>

    <h1>북마크 목록</h1>
    <jsp:include page="comp/menu.jsp" />

    <table class="table-list">
        <thead>
        <tr>
            <th>ID</th>
            <th>북마크 이름</th>
            <th>와이파이명</th>
            <th>등록일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (list.isEmpty()) {
                out.write("<tr>");
                out.write("<td colspan='5' style='text-align: center;'>" + "값이 없습니다." + "</td>");
                out.write("</tr>");
            } else {
                for (Bookmark bookmark : list) {
                    out.write("<tr>");
                    out.write("<td>" + bookmark.getId() + "</td>");
                    out.write("<td>" + bookmark.getBookmarkName() + "</td>");
                    out.write("<td>" +
                            "<a href='detail.jsp?manageNumber=" + bookmark.getWifiManageNumber() + "'>" +
                            bookmark.getWifiName() +
                            "</a>" +
                            "</td>"
                    );
                    out.write("<td>" + bookmark.getRegisterDate() + "</td>");
                    out.write("<td style='text-align: center;'>" +
                            "<a href='bookmark-delete.jsp?id=" + bookmark.getId()  + "'>" +
                            "삭제" +
                            "</a>" +
                            "</td>"
                    );
                    out.write("</tr>");
                }
            }
        %>
        </tbody>
    </table>
</body>

<jsp:include page="comp/footer.jsp" />
