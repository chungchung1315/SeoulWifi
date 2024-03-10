<%@ page import="com.marceldev.seoulpublicwifi.Services.BookmarkService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="comp/header.jsp" />

<body>
<%
    BookmarkService service = new BookmarkService();
    String bookmarkId = request.getParameter("bookmark-id");

    if (bookmarkId == null || bookmarkId.isEmpty()) {
    %>
        <script>
            alert('북마크 삭제에 실패했습니다.');
            window.location.href = "bookmark-list.jsp";
        </script>
    <%
        return;
    }

    int row = service.delete(Integer.parseInt(bookmarkId));
    if (row == 1) {
    %>
        <script>
            alert('북마크를 삭제하였습니다.');
            window.location.href = "bookmark-list.jsp";
        </script>
    <%
    } else {
    %>
        <script>
            alert('북마크 삭제에 실패했습니다.');
            window.location.href = "bookmark-list.jsp";
        </script>
    <%
    }
%>
</body>

<jsp:include page="comp/footer.jsp" />
