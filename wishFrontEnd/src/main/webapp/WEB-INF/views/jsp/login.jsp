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
                  <form:form method="POST" action="login" modelAttribute="login">
                    <div class="form-group">
                      <h2>Sign in</h2>
                    </div>
                    <div class="form-group">
                      <strong>Email</strong>
                      <form:input id="signinEmail" name="username" type="email" maxlength="50" class="form-control" path="username"/>
                    </div>
                    <div class="form-group">
                      <strong>Password</strong>
                      <form:input id="signinPassword" name="password" type="password" maxlength="25" class="form-control" path="password"/>
                      <span class="right"><a href="/passwordchange">Forgot your password?</a></span>
                    </div>
                    <div class="form-group" style="padding-top: 12px;">
                      <button id="signinSubmit" type="submit" class="btn btn-success btn-block">Sign in</button>
                    </div>
                    <div class="form-group divider">
                      <hr class="left"><small>New to site?</small><hr class="right">
                    </div>
                    <p class="form-group"><a href="registernewUser" class="btn btn-info btn-block">Create an account</a></p>
                    <p class="form-group"><small>or</small></p>
                    <p class="form-group"><a href="#" class="btn btn-info btn-block">Sign in with Google</a></p>
                    <p class="form-group">By signing in you are agreeing to our <a href="#">Terms of Use</a> and our <a href="#">Privacy Policy</a>.</p>
                  </form:form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </header>

<%@include file="_footer.jsp" %>
