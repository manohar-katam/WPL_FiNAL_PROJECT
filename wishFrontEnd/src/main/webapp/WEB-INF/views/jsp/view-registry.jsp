<%--
  Created by IntelliJ IDEA.
  User: snehachandra
  Date: 12/5/17
  Time: 12:47 AM
  To change this template use File | Settings | File Templates.
--%>


<%@include file="_user-header.jsp"%>

<section id="myregistries">

    <div class="container">
        <h2>Registry Name</h2>
        <table id="registry" class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Category</th>
                <th scope="col">Price</th>
                <th scope="col">Rating</th>
                <th scope="col">Gifted By</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${registry}" var="registryItem">
                <tr>
                    <td><c:out value="${registryItem.getName()}" /></td>
                    <td><c:out value="${registryItem.getCategory()}" /></td>
                    <td><c:out value="${registryItem.getPrice()}" /></td>
                    <td><c:out value="${registryItem.getRating()}" /></td>
                    <td><c:choose>
                        <c:when test="${registryItem.getAssigned().equals('admin')}">
                              <span>
                                  NONE YET!
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
