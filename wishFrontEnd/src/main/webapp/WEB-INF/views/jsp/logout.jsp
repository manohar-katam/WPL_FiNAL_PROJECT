<%--
  Created by IntelliJ IDEA.
  User: manohar
  Date: 01-12-2017
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@include file="_header.jsp" %>

    <!-- Header -->
    <header class="masthead">
      <div class="container">
        <div class="intro-text">
        <%
		session.invalidate();
		%>
          <div class="intro-lead-in">Successfully logged out! </div>
          <a class="btn btn-primary btn-xl text-uppercase js-scroll-trigger" href="login">LOGIN AGAIN</a>
        </div>
      </div>
    </header>

<%@include file="_footer.jsp" %>