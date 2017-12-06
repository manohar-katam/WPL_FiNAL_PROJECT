<%--
  Created by IntelliJ IDEA.
  User: manohar
  Date: 03-12-2017
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>


<%@include file="_user-header.jsp"%>

<section id="myregistriesList">

    <div class="container">
        <h2>My Registries</h2>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Registry Name</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${registryList}" var="registry">
                <tr>
                    <td><c:out value="${registry.getId()}" /></td>
                    <td><c:out value="${registry.getName()}" /></td>
                    <td>
               <span>
                   <a href="/viewRegistry?regId=${registry.getId()}">
                       <button type="button" class="btn btn-danger">VIEW</button>
                   </a>
               </span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<%@include file="_footer.jsp"%>
