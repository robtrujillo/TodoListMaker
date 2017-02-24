<!--
Created by IntelliJ IDEA.
User: rvtru
Date: 2/14/2017
Time: 1:58 PM
To change this template use File | Settings | File Templates.
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>TODO LIST MAKER</title>



    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
    <script src="https://apis.google.com/js/api:client.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    <!-- Favicon -->
    <link rel="icon" type="image/png" href="<c:url value="/favicon.ico" />">


    <!-- Normalize  for improved cross-browser rendering -->
    <link rel="stylesheet" href="<c:url value="/resources/library-vendor/normalize/normalize.css" />" >

    <!-- Google Code Prettify -->
    <link rel="stylesheet" href="<c:url value="/resources/library-vendor/google-code-prettify/prettify.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/library-vendor/google-code-prettify/desert.css" />">

    <!-- jqTree -->
    <link rel="stylesheet" href="<c:url value="/resources/library-vendor/jqTree-master/jqtree.css" />">

    <!-- Pnotify - pinesframework  -->
    <link rel="stylesheet" href="<c:url value="/resources/library-vendor/pnotify/pnotify.custom.min.css" />">
    <script>
        var googleUser = {};
        var startApp = function() {
            gapi.load('auth2', function(){
                // Retrieve the singleton for the GoogleAuth library and set up the client.
                auth2 = gapi.auth2.init({
                    client_id: '322601702877-dbp2h5samt8vp79cas05479a2t7r9tqk.apps.googleusercontent.com',
                    cookiepolicy: 'single_host_origin',
                    // Request scopes in addition to 'profile' and 'email'
                    //scope: 'additional_scope'
                });
                attachSignin(document.getElementById('customBtn'));
            });
        };

        function attachSignin(element) {

            console.log(element.id);
            auth2.attachClickHandler(element, {},
                function(googleUser) {
                    var userName = googleUser.getBasicProfile().getName();
                    var userEmail = googleUser.getBasicProfile().getEmail();
                    sessionStorage.setItem("userName", userName);
                    sessionStorage.setItem("email", userEmail);
                    document.forms["loadPage"].submit();
                }, function(error) {
                    alert(JSON.stringify(error, undefined, 2));
                });
        }
    </script>

</head>
<body class="page-wrapper">

<div>
    <h1>Welcome to the Todo List Maker!</h1>
    <p>The most immersive todo list maker of this century!</p>
    <form:form id="loadPage" name="loadPage" method="GET" action="/loadPage">
        <button hidden="hidden" class="btn btn-signout btn-warning" type="submit"></button>
    </form:form>

    <form:form method="GET" action="/login">

        <div id="login_ID">
            <div id="gSignInWrapper">
                <div id="customBtn" class="customGPlusSignIn">
                    <button type="button" class = "btn btn-google">Sign in with Google</button>
                </div>
            </div>
        </div>
        <div id="name"></div>

        <script>startApp();</script>
    </form:form>
</div>

<link rel="stylesheet" href="<c:url value="/resources/app/css/login.css" />">

<!-- JavaScripts -->
<section>

    <!-- jQuery Core -->
    <script src="<c:url value="/resources/library-vendor/jquery/jquery-2.0.3.min.js" />"></script>

    <!-- jQuery Validation -->
    <script src="<c:url value="/resources/library-vendor/jquery/validation/jquery.validate.min.js" />"  type="text/javascript" ></script>

    <!-- jQuery Validation Additional Methods -->
    <script src="<c:url value="/resources/library-vendor/jquery/validation/additional-methods.min.js" />"  type="text/javascript" ></script>

    <!-- Bootstrap -->
    <script src="<c:url value="/resources/library-vendor/bootstrap/js/bootstrap.min.js" />" type="text/javascript"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries, (https://github.com/scottjehl/Respond), (https://code.google.com/p/html5shiv/) -->
    <script src="<c:url value="/resources/library-vendor/respond/respond.min.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/library-vendor/html5shiv/html5shiv.js" />" type="text/javascript"></script>

    <!-- Google Code Prettify -->
    <script src="<c:url value="/resources/library-vendor/google-code-prettify/prettify.js" />" type="text/javascript"></script>

    <!-- jqTree  -->
    <script src="<c:url value="/resources/library-vendor/jqTree-master/tree.jquery.js" />"  type="text/javascript" ></script>

    <!-- Cookie Jquery  -->
    <script src="<c:url value="/resources/library-vendor/jquery-cookie-master/jquery.cookie.js" />"  type="text/javascript" ></script>

    <!-- Pnotify - pinesframework  -->
    <script src="<c:url value="/resources/library-vendor/pnotify/pnotify.custom.min.js" />"  type="text/javascript" ></script>

    <%--HERE--%>




</section>




</body>