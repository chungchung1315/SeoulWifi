<%@ page import="com.marceldev.seoulpublicwifi.Services.BookmarkGroupService" %>
<%@ page import="com.marceldev.seoulpublicwifi.Models.BookmarkGroup" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="comp/header.jsp" />

<body>
    <%
        BookmarkGroupService service = new BookmarkGroupService();
        String id = request.getParameter("id");
        BookmarkGroup group = service.getBookmarkGroup(Integer.parseInt(id));
    %>

    <h1>북마크 그룹 수정</h1>
    <jsp:include page="comp/menu.jsp" />

    <form action="bookmark-group.jsp" method="post">
        <table class="table-list">
            <tbody>
                <tr>
                    <th>
                        <label for="bookmark-name">북마크 이름</label>
                    </th>
                    <td>
                        <input type="text" id="bookmark-name" name="bookmark-name" value="<%= group.getBookmarkName() %>" disabled />
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="bookmark-priority">순서</label>
                    </th>
                    <td>
                        <input type="text" id="bookmark-priority" name="bookmark-priority" value="<%= group.getPriority() %>" disabled />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style='text-align: center;'>
                        <a href="bookmark-group.jsp">돌아가기</a>
                        <span> | </span>
                        <input type="submit" value="삭제" />
                        <input type="hidden" id="bookmark-action" name="bookmark-action" value="delete" />
                        <input type="hidden" id="bookmark-group-id" name="bookmark-group-id" value="<%= group.getId()  %>" />
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</body>

<jsp:include page="comp/footer.jsp" />
