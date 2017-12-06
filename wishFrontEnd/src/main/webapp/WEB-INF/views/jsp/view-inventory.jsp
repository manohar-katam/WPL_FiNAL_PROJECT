
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: manohar
  Date: 01-12-2017
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@include file="_admin-header.jsp"%>

<section id="view_products">

    <div class="container">
<span style="float: right;">
        <a href="inventory">
        <button type="button" class="btn btn-primary glyphicon glyphicon-trash"> ADD A PRODUCT
        </button></a>
      </span>
        <h2>Products in inventory</h2>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Product ID</th>
                <th scope="col">Name</th>
                <th scope="col">Price</th>
                <th scope="col">Color</th>
                <th scope="col">Rating</th>
                <th scope="col">DELETE</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${inventoryList}" var="inventory">
                <tr>
                    <td><c:out value="${inventory.getId()}" /></td>
                    <td><c:out value="${inventory.getName()}" /></td>
                    <td><c:out value="${inventory.getPrice()}" /></td>
                    <td><c:out value="${inventory.getColor()}" /></td>
                    <td><c:out value="${inventory.getRating()}" /></td>
                    <td><span style="float: left;">
        <a href="deleteProduct?id=<c:out value="${inventory.getId()}" />">
        <button type="button" class="btn btn-danger glyphicon glyphicon-trash"> DELETE
        </button></a>
      </span></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<%@include file="_footer.jsp"%>