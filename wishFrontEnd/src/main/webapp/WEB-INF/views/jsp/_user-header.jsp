<%--
  Created by IntelliJ IDEA.
  User: manohar
  Date: 01-12-2017
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    if(session.getAttribute("username") == "admin@giftregistry.com"){
        response.sendRedirect("unauthorized-access");
    }
%>


<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Gift Registry</title>

    <!-- Bootstrap core CSS -->
    <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>


    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>

    <!-- Custom styles for this template -->
    <link href="/resources/css/agency.min.css" rel="stylesheet">
    <link href="/resources/css/products.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css" rel="stylesheet">

    <script href="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js" type="application/javascript"></script>
   <script href=" https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js" type="application/javascript"></script>


</head>

<body id="page-top">


<!-- Navigation -->
<nav class="navbar navbar-expand-md navbar-inverse navbar-fixed-top" style="background-color: black"  id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="/home">Gift Registry</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fa fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav text-uppercase ml-auto">
                <li class="nav-registryItem">
                    <a class="nav-link js-scroll-trigger" href="/viewRegistryList">My Registries</a>
                </li>
                <li class="nav-registryItem">
                    <a class="nav-link js-scroll-trigger" href="/viewSharedRegistryList">View Registries</a>
                </li>
                <li class="nav-registryItem">
                    <a class="nav-link js-scroll-trigger" href="/registry/createRegistry/products">Products</a>
                </li>
                <li class="nav-registryItem">
                    <a class="nav-link js-scroll-trigger" href="/userprofile">Profile</a>
                </li>
                <li class="nav-registryItem">
                    <a class="nav-link js-scroll-trigger" href="/logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
