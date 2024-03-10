<%@ page import="com.marceldev.seoulpublicwifi.Services.BookmarkService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="comp/header.jsp" />

<body>
<%
    BookmarkService service = new BookmarkService();
    request.setCharacterEncoding("UTF-8");
    String manageNumber = request.getParameter("wifi-manage-number");
    String bookmarkGroupId = request.getParameter("bookmark-group-id");

    if (bookmarkGroupId == null || bookmarkGroupId.isEmpty()) {
        %>
        <script>
            alert('북마크 정보 추가에 실패했습니다.');
            window.history.back();
        </script>
        <%
        return;
    }

    int row = service.add(Integer.parseInt(bookmarkGroupId), manageNumber);
    if (row == 1) {
        %>
        <script>
            alert('북마크 정보를 추가하였습니다.');
            window.location.href = "bookmark-list.jsp";
        </script>
        <%
    } else {
        %>
        <script>
            alert('북마크 정보 추가에 실패했습니다.');
            window.history.back();
        </script>
        <%
    }
%>
</body>

<jsp:include page="comp/footer.jsp" />
