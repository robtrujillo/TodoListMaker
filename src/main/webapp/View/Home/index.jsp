
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8"  %>

<!-- ADDED BY ROBERT FROM CLANS -->

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="<c:url value="/resources/app/css/todolist_maker.css" />">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>TODO LIST MAKER</title>

</head>
<body ng-app="myApp" ng-controller="myCtrl">

    <!--App Content START-->
    <div class="app-wrapper">
        <div id="toolbar">

            <button ng-click="newList()"  type="button" class="btn btn-success" data-toggle="tooltip" title="Create" data-placement="bottom">
                <span class="glyphicon glyphicon-plus-sign"></span>
            </button>

            <button ng-click="loadList()" type="button" class="btn btn-default" data-toggle="tooltip" title="Load" data-placement="bottom">
                <span class="glyphicon glyphicon-folder-open"></span>
            </button>

            <button ng-disabled="!listForm.$dirty" type="button" class="btn btn-primary" data-toggle="tooltip" title="Save" data-placement="bottom">
                <span class="glyphicon glyphicon-floppy-disk"></span>
            </button>

            <button ng-click="logout()" type="submit" class="btn btn-default" data-toggle="tooltip" title="Logout" data-placement="bottom">
                <span class="glyphicon glyphicon-log-out"></span>
            </button>

        </div>

        <div id="mid" ng-show="show">
            <h1>To Do List</h1>
        </div>

        <div id="details" ng-show="show">
            <h2>Details</h2>
            <br/>
            <div class="form-group">
                <form name="listForm">
                    <label for="nameOfList">Name of List:</label>
                    <input type="text" class="form-control" id="nameOfList" ng-model="nameOfList">
                </form>
            </div>
            <div class="form-group">
                <label for="author">Author:</label>
                <input type="text" class="form-control" id="author">
            </div>
        </div>

        <div id="items" ng-show="show">
            <h3>Items</h3>
            <span id="item-btns">

                <button type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-plus-sign">
                <a href="#" data-toggle="tooltip" title="Create">Add Item</a>
            </span>
            </button>


            <button type="button" class="btn btn-default">
                <a href="#" data-toggle="tooltip" title="Save">Delete Item</a>
                <span class="glyphicon glyphicon-minus-sign"></span>
            </button>

            <button type="button" class="btn btn-default">
                <span class="glyphicons glyphicons-circle-arrow-top">
                    <a href="#" data-toggle="tooltip" title="Save">Move Item Up</a>
                </span>
            </button>

            <button type="button" class="btn btn-default">
                <span class="glyphicons glyphicons-circle-arrow-down">
                    <a href="#" data-toggle="tooltip" title="Save">Move Item Down</a>
                </span>
            </button>
            </span>

            <kendo-grid options="mainGridOptions">

            </kendo-grid>

        </div>
    </div>
    <!--App Content END--

    <!-- Modal New Category -->
    <div class="modal fade" id="new-category-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form role="form" id="category-add-form" action="#" method="post" accept-charset="utf-8">

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">New Category</h4>
                    </div>

                    <div class="modal-body">

                        <!-- Mensajes post ajax request -->
                        <div class="alert alert-success alert-dismissible" style="display: none;" role="alert">
                            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            You have registered the category!
                        </div>
                        <div class="alert alert-danger alert-dismissible" style="display: none;" role="alert">
                            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            An error has occurred, try again or you can also try reloading the page if the error persists!
                        </div>

                        <div class="form-group">
                            <label for="category-name"><span class="glyphicon glyphicon-folder-close"></span> Name</label>
                            <input type="text" class="form-control" id="category-name" name="category-name" placeholder="Eje: Laptops">
                            <span class="help-block" style="display: none;">Requerido</span>
                        </div>

                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Send</button>
                    </div>

                </form>
            </div>
        </div>
    </div>

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

        <!-- App Base -->

        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
        <script src="<c:url value="/resources/app/js/ang_test.js" />"  type="text/javascript" ></script>

        <!-- App -->




        <!--KENDO UI -->
        <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.118/styles/kendo.common-material.min.css" />
        <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.118/styles/kendo.material.min.css" />
        <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.118/styles/kendo.material.mobile.min.css" />
        <!-- THE FOLLOWING SCRIPT (KENDO) REQUIRES JQUERY & ANGULARJS-->
        <!--<script src="https://kendo.cdn.telerik.com/2017.1.118/js/jquery.min.js"></script>-->
        <!--<script src="https://kendo.cdn.telerik.com/2017.1.118/js/angular.min.js"></script>-->
        <script src="https://kendo.cdn.telerik.com/2017.1.118/js/kendo.all.min.js"></script>
        <!--KENDO UI-->



    </section>

</body>
</html>