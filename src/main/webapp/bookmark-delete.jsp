<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="com.marceldev.seoulpublicwifi.Models.Bookmark" %>
<%@ page import="com.marceldev.seoulpublicwifi.Services.BookmarkService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="comp/header.jsp" />

<body>
    <%
        BookmarkService service = new BookmarkService();
        String id = request.getParameter("id");
        Bookmark bookmark = service.detail(Integer.parseInt(id));
    %>
    <h1>북마크 삭제</h1>
    <jsp:include page="comp/menu.jsp" />
    <p>북마크를 삭제하시겠습니까?</p>

    <table class="table-list">
        <tbody>
            <%
                Map<String, String> map = new LinkedHashMap<>();
                map.put("북마크 이름", bookmark.getBookmarkName());
                map.put("와이파이명", bookmark.getWifiName());
                map.put("등록일자", bookmark.getRegisterDate());

                for (Map.Entry<String, String> entry : map.entrySet()) {
                    out.write("<tr>");
                    out.write("<th>" + entry.getKey() + "</th>");
                    out.write("<td>" + entry.getValue() + "</td>");
                    out.write("</tr>");
                }
            %>

            <tr>
                <td colspan="2" style='text-align: center;'>
                    <form action="bookmark-delete-submit.jsp" method="post">
                        <a href="bookmark-list.jsp">돌아가기</a>
                        <span> | </span>
                        <input type="hidden" id="bookmark-id" name="bookmark-id" value="<%= bookmark.getId()  %>" />
                        <input type="submit" value="삭제" />
                    </form>
                </td>
            </tr>
        </tbody>
    </table>
</body>

<jsp:include page="comp/footer.jsp" />
