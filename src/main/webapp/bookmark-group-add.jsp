<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="comp/header.jsp" />

<body>
    <h1>북마크 그룹 추가</h1>
    <jsp:include page="comp/menu.jsp" />

    <form action="bookmark-group.jsp" method="post">
        <table class="table-list">
            <tbody>
                <tr>
                    <th>
                        <label for="bookmark-name">북마크 이름</label>
                    </th>
                    <td>
                        <input type="text" id="bookmark-name" name="bookmark-name" />
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="bookmark-priority">순서</label>
                    </th>
                    <td>
                        <input type="text" id="bookmark-priority" name="bookmark-priority" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style='text-align: center;'>
                        <input type="submit" value="추가" />
                        <input type="hidden" id="bookmark-action" name="bookmark-action" value="add" />
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</body>

<jsp:include page="comp/footer.jsp" />
