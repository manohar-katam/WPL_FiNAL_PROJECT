
<%--
  Created by IntelliJ IDEA.
  User: manohar
  Date: 01-12-2017
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@include file="_user-header.jsp"%>
<section id="registry">
    <div class="container">
<h2>Registry Cart</h2>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">Product ID</th>
        <th scope="col">Name</th>
        <th scope="col">Price</th>
        <th scope="col">DELETE</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${regCart}" var="reg">
        <tr>
            <td><c:out value="${reg.getProductId()}" /></td>
            <td><c:out value="${reg.getName()}" /></td>
            <td><c:out value="${reg.getPrice()}" /></td>
            <td><span><a href="/registry/createRegistry/removeProduct?id=${reg.getId()}">
                <button type="button" class="btn btn-danger glyphicon glyphicon-trash">DELETE</button>
                </a></span>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <a href="/registry/createRegistry/products"><button type="button" class="btn btn-danger glyphicon glyphicon-trash">ADD PRODUCTS</button></a>
        <span style="float: right;">
        <form method="POST" action="saveRegistry"><button type="submit" class="btn btn-primary glyphicon glyphicon-trash">SAVE REGISTRY</button></form>

      </span>

    </div>
</section>
<%@include file="_footer.jsp"%>