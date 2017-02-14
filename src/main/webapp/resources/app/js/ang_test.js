/**
 * Created by rvtru on 2/12/2017.
 */

$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();
});

var app = angular.module('myApp', ["kendo.directives"]);
app.controller('myCtrl', function ($scope, $http) {

    /* EXAMPLE OF HOW TO CREATE AN HTTP REQUEST USING ANGULAR */
    $scope.click = function(){
        var y = $http({
            method: 'GET',
            url: '/getMessages',
            params: {"id": 30}
        }).then(function (response) {
            alert("it worked!");
            //$scope.posts[index]["comments"].splice(commentIndex, 1);
        }, function errorCallBack(response) {
            alert("get messages error\n");
        });
    }

    /* INIT SOME SCOPE VARIABLES */
    $scope.nameOfList = "";

    /* SHOW CONTENT AFTER 'CREATE' BUTTON IS CLICKED */
    $scope.show = false;
    $scope.newList = function(){
        $scope.show = true;
    }



    $scope.addList = function(){
        var y = $http({
            method: 'GET',
            url: '/addList',
            params: {"id": 30}
        }).then(function (response) {
            alert("it worked!");
            //$scope.posts[index]["comments"].splice(commentIndex, 1);
        }, function errorCallBack(response) {
            alert("get messages error\n");
        });
    }

    $scope.items = [
        {
            "position":1,
            "category":"testing",
            "description":"testing the kendo grid",
            "start":"a start time",
            "end":"an end time",
            "completed":true
        }
        ]
    /* INIT ITEM TABLE WITH KENDO GRID */
    $scope.mainGridOptions = {
        dataSource: $scope.items,
        selectable: "row",
        columns: [
            { field: "position", title: "Position" },
            { field: "category", title: "Category" },
            { field: "description", title: "Description" },
            { field: "start", title: "Start Date" },
            { field: "end", title: "End Date" },
            { field: "completed", title: "Completed" }
        ],
        sortable: true,
        pageable: true
    };


});


