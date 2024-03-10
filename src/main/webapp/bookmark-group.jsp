<%@ page import="com.marceldev.seoulpublicwifi.Models.BookmarkGroup" %>
<%@ page import="java.util.List" %>
<%@ page import="com.marceldev.seoulpublicwifi.Services.BookmarkGroupService" %>
<%@ page import="com.marceldev.seoulpublicwifi.Models.BookmarkGroupModify" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="comp/header.jsp" />

<body>
    <%
        BookmarkGroupService service = new BookmarkGroupService();
        request.setCharacterEncoding("UTF-8");
        String bookmarkAction = request.getParameter("bookmark-action");
        String bookmarkName = request.getParameter("bookmark-name");
        String bookmarkPriority = request.getParameter("bookmark-priority");
        String bookmarkGroupIdString = request.getParameter("bookmark-group-id");
        Integer bookmarkGroupId = bookmarkGroupIdString != null ? Integer.parseInt(bookmarkGroupIdString) : null;

        if (bookmarkAction != null) {
            switch (bookmarkAction) {
                case "add":
                    service.add(bookmarkName, Integer.parseInt(bookmarkPriority));
                    break;
                case "edit":
                    BookmarkGroupModify group = new BookmarkGroupModify();
                    group.setBookmarkName(bookmarkName);
                    group.setPriority(Integer.parseInt(bookmarkPriority));
                    service.edit(bookmarkGroupId, group);
                    break;
                case "delete":
                    service.delete(bookmarkGroupId);
                    break;
                default:
            }
        }

        List<BookmarkGroup> list = service.list();
    %>
    <h1>북마크 그룹</h1>
    <jsp:include page="comp/menu.jsp" />
    <form class="sub-menu" action="bookmark-group-add.jsp" method="get">
        <input type="submit" value="북마크 그룹 이름 추가">
    </form>

    <table class="table-list">
        <thead>
        <tr>
            <th>ID</th>
            <th>북마크 이름</th>
            <th>순서</th>
            <th>등록일자</th>
            <th>수정일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (list.isEmpty()) {
                out.write("<tr>");
                out.write("<td colspan='6' style='text-align: center;'>" + "값이 없습니다." + "</td>");
                out.write("</tr>");
            } else {
                for (BookmarkGroup bookmarkGroup: list) {
                    out.write("<tr>");
                    out.write("<td>" + bookmarkGroup.getId() + "</td>");
                    out.write("<td>" + bookmarkGroup.getBookmarkName() + "</td>");
                    out.write("<td>" + bookmarkGroup.getPriority() + "</td>");
                    out.write("<td>" + bookmarkGroup.getRegisterDate() + "</td>");
                    out.write("<td>" + (bookmarkGroup.getModifyDate() == null ? "" : bookmarkGroup.getModifyDate()) + "</td>");
                    out.write("<td style='text-align: center;'>");
                    out.write("<a href='bookmark-group-edit.jsp?id=" + bookmarkGroup.getId()  + "'>수정</a>");
                    out.write(" ");
                    out.write("<a href='bookmark-group-delete.jsp?id=" + bookmarkGroup.getId()  + "'>삭제</a>");
                    out.write("</td>");
                    out.write("</tr>");
                }
            }
        %>
        </tbody>
    </table>
</body>

<jsp:include page="comp/footer.jsp" />
