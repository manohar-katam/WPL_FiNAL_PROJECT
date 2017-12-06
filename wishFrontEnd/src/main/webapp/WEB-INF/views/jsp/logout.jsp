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