<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.wpl.gift.model.User" %><%--
  Created by IntelliJ IDEA.
  User: manohar
  Date: 23-11-2017
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@include file="_user-header.jsp" %>

<section id="userProfile">

    <div class="container">

<div class="container">
    <div class="row">
        <div class="panel panel-primary">
            <div class="panel-body">
                <form:form method="POST" action="userprofile" modelAttribute="user">
                    <div class="form-group">
                        <h2>Profile Info</h2>
                    </div>
                    <div class="form-group">
                        <strong>First Name</strong>
                        <form:input id="firstName" name="firstName" type="text" maxlength="50" class="form-control" path="firstName"    value="${user.getFirstName()}"/>
                    </div>
                    <div class="form-group">
                        <strong>Last Name</strong>
                        <form:input id="lastName" name="LastName" type="text" maxlength="50" class="form-control" path="lastName" value="${user.getLastName()}"/>
                    </div>
                    <div class="form-group" style="padding-top: 12px;">
                        <button id="signinSubmit" type="submit" class="btn btn-success btn-block">Save</button>
                        <a href="/hithomepage" id="cancel" name="cancel" class="btn btn-default">Cancel</a>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>
    </div>
</section>
<%@include file="_footer.jsp" %>