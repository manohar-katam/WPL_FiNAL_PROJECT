
<%--
  Created by IntelliJ IDEA.
  User: snehachandra
  Date: 12/2/17
  Time: 12:24 PM
  To change this template use File | Settings | File Templates.
--%>

<%@include file="_user-header.jsp"%>

<!-- Header -->
<section id="create_registry">


    <div class="container">
        <div class="intro-text">
            <div class="container">
                <div class="row">
                    <div class="panel panel-primary">
                        <div class="panel-body">
                            <form:form method="POST" action="createRegistry" modelAttribute="registry">
                                <div class="form-group">
                                    <h2>Create Registry</h2>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="name">Name</label>
                                    <form:input id="name" name="name" path="name" type="text" maxlength="50" class="form-control"/>
                                </div>
                               <div class="form-group">
                                    <label class="control-label" for="shared">Shared: </label>
                                <form:radiobutton name="shared" onclick="hideUsers()" path="shared" id="Public" value="Public" required="required" checked="checked"/> <label> Public  </label>
                                    <form:radiobutton name="shared" onclick="showUsers()" path="shared" id="Private" value="Private" required="required" /> <label> Private </label>
                                </div>

                                <div class="form-group">
                                    <button id="products" type="submit" class="btn btn-info btn-block">Next</button>
                                </div>

                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</section>
<script type="text/javascript">

    jQuery( document ).ready(function($) {

        $('#users').hide();


    });
    function showUsers() {
        $('#users').show();
    }

    function showUserList() {

    }
    function hideUsers()
    {
        $('#users').hide();
    }

</script>

<%@include file="_footer.jsp"%>