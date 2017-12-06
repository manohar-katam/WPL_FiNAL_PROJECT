<%--
  Created by IntelliJ IDEA.
  User: kartheek
  Date: 12/3/2017
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.wpl.gift.model.User" %>

<%@include file="_user-header.jsp" %>

<section id="forgot_password">

    <div class="container">

<div class="container">
    <div class="row">
        <div class="panel panel-primary">
            <div class="panel-body">
                <form:form method="POST" action="/passwordchange" modelAttribute="user">
                    <div class="form-group">
                        <h2>Forgot Password page</h2>
                    </div>
                    <div class="form-group">
                        <strong>Enter your email id</strong>
                        <form:input id="email" name="email" type="text" maxlength="50" class="form-control" path="userName" />
                    </div>
                    <div class="form-group" style="padding-top: 12px;">
                        <button id="submit" type="submit" class="btn btn-success btn-block">Submit</button>
                        <a href="/login" id="cancel" name="cancel" class="btn btn-default">Cancel</a>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>
    </div>
</section>
<%@include file="_footer.jsp" %>