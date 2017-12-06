
<%--
  Created by IntelliJ IDEA.
  User: snehachandra
  Date: 12/3/17
  Time: 10:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="_user-header.jsp"%>
<section id="userList">
    <div class="container">
        <h2>USER LIST</h2>
        <form:form method="POST" action="saveUsers" modelAttribute="registry">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Add</th>
                <th scope="col">User</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userList}" var="user">

                <tr>
                    <td><form:checkbox path="userIds" name="userIds" value="${user.getId()}"/></td>
                    <td><c:out value="${user.getFirstName()}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
            <button type="submit" class="btn btn-primary glyphicon glyphicon-trash">SAVE</button>
        </form:form>
    </div>
</section>
<%@include file="_footer.jsp"%>