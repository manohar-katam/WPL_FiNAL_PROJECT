<%--
  Created by IntelliJ IDEA.
  User: manohar
  Date: 01-12-2017
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="_header.jsp" %>

    <!-- Header -->
    <header class="masthead">
      <div class="container">
        <div class="intro-text">
          <div class="container">
            <div class="row">
              <div class="panel panel-primary">
                <div class="panel-body">
                  <form:form method="POST" action="register" modelAttribute="register">
                    <div class="form-group">
                      <h2>Create account</h2>
                    </div>
                    <div class="form-group">
                      <label class="control-label" for="signupFName">Your First name</label>
                      <form:input id="signupFName" name="firstname" path="firstname" type="text" maxlength="50" class="form-control"/>
                    </div>
                    <div class="form-group">
                      <label class="control-label" for="signupLName">Your Last name</label>
                      <form:input id="signupLName" name="lastname" path="lastname" type="text" maxlength="50" class="form-control"/>
                    </div>
                    <div class="form-group">
                      <label class="control-label" for="signupEmail">Email</label>
                      <form:input id="signupEmail" name="username" path="username" type="email" maxlength="50" class="form-control"/>
                    </div>
                    <div class="form-group">
                      <label class="control-label" for="signupPassword">Password</label>
                      <form:input id="signupPassword" name="password" path="password" type="password" maxlength="25" class="form-control" placeholder="at least 6 characters" length="40"/>
                    </div>
                    <div class="form-group">
                      <label class="control-label" for="signupPasswordagain">Password again</label>
                      <form:input id="signupPasswordagain" name="passwordagain" path="passwordagain" type="password" maxlength="25" class="form-control"/>
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
                      <button id="signupSubmit" type="submit" class="btn btn-info btn-block">Create your account</button>
                    </div>
                    <p class="form-group">By creating an account, you agree to our <a href="#">Terms of Use</a> and our <a href="#">Privacy Policy</a>.</p>
                    <hr>
                    <p></p>Already have an account? <a href="login.jsp">Sign in</a></p>
                  </form:form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </header>

<%@include file="_footer.jsp" %>