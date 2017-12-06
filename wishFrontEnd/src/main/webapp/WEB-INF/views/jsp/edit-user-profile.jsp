products<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: manohar
  Date: 01-12-2017
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@include file="_user-header.jsp"%>

<div class="container">
    <div class="intro-text">
        <div class="container">
            <div class="row">
                <div class="panel panel-primary">
                    <div class="panel-body">
                        <form:form method="POST" action="" modelAttribute="">
                            <div class="form-group">
                                <h2>Create account</h2>
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="signupName">Your Full name</label>
                                <form:input id="signupName" name="fullname" path="fullname" type="text" maxlength="50" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="signupPassword">Password</label>
                                <form:input id="signupPassword" name="password" path="password" type="password" maxlength="25" class="form-control" placeholder="at least 6 characters" length="40"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="securityquestion">Security Question</label>
                                <form:input id="securityquestion" name="securityquestion" path="securityquestion" type="text" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="securityanswer">Security Answer</label>
                                <form:input id="securityanswer" name="securityanswer" path="securityanswer" type="password" maxlength="25" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <button id="signupSubmit" type="submit" class="btn btn-info btn-block">UPDATE PROFILE</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="_footer.jsp"%>
