<%--
  Created by IntelliJ IDEA.
  User: snehachandra, manohar
  Date: 12/5/17
  Time: 12:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="_user-header.jsp"%>

<section id="myregistries">

    <div class="container">
        <h2>Registry Name</h2>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Category</th>
                <th scope="col">Price</th>
                <th scope="col">Rating</th>
                <th scope="col">Gift It</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${registry}" var="registryItem">
                <tr>
                    <td><c:out value="${registryItem.getName()}" /></td>
                    <td><c:out value="${registryItem.getCategory()}" /></td>
                    <td><c:out value="${registryItem.getPrice()}" /></td>
                    <td><c:out value="${registryItem.getRating()}" /></td>
                    <td>
                        <c:choose>
                            <c:when test="${registryItem.getAssigned().equals('admin')}">
                              <span>
                                  <a href="selfAssign?id=${registryItem.getId()}">
                                    <button type="button" class="btn btn-danger">YES,I WILL</button></a>
                              </span>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${registryItem.getAssigned()}" />
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<%@include file="_footer.jsp"%>