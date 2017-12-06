<%--
  Created by IntelliJ IDEA.
  User: kartheek
  Date: 12/3/2017
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.wpl.gift.model.User" %>

<%@include file="_user-header.jsp" %>
<section id="security_questions">

    <div class="container">
<div class="container">
    <div class="row">
        <div class="panel panel-primary">
            <div class="panel-body">
                <form:form method="POST" action="/resetpassword" modelAttribute="user">
                    <div class="form-group">
                        <h2>Security questions page</h2>
                    </div>
                    <div class="form-group">
                        <strong>Security Question</strong>
                        <form:input id="sq" name="text" type="text" maxlength="50" class="form-control" path="securityQuestion" disabled="true" />
                    </div>
                    <div class="form-group">
                        <strong>Security Answer</strong>
                        <form:input id="sa" name="text" type="text" maxlength="50" class="form-control" path="securityAnswer" />
                    </div>
                    <div class="form-group">
                        <strong>New Password</strong>
                        <form:input id="pass" name="text" type="text" maxlength="50" class="form-control" path="password" />
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