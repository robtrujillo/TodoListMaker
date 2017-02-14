/**
 * Created by rvtru on 2/12/2017.
 */

$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();
});

var app = angular.module('myApp', ["kendo.directives"]);
app.controller('myCtrl', function ($scope, $http) {

    /* INIT SOME SCOPE VARIABLES */
    $scope.nameOfList = "";
    /* ARRAY OF ITEMS TO DELETE */
    $scope.toDelete = [];
    /* USER CREDENTIALS */
    $scope.email = "";
    $scope.name = "";
    /* ARRAY OF ITEMS */
    $scope.items = [];
    /* SELECTED LIST */
    $scope.selectedList = {};


    /* SHOW CONTENT AFTER 'CREATE' BUTTON IS CLICKED */
    $scope.show = false;
    $scope.newList = function(){
        $scope.show = true;
    }

    /* DATA FOR KENDO GRID. THESE SHOULD BE THE ITEM OBJECTS RECEIVED FROM DATASTORE */
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

    $scope.getTodoList = function(){
        var y = $http({
            method: 'GET',
            url: '/getMessages',
            params: {}
        }).then(function (response) {
            $scope.userToDoList = response.data;
            console.log("it worked:\n" + response.toString());
            //$scope.posts[index]["comments"].splice(commentIndex, 1);
        }, function errorCallBack(response) {
            alert("get messages error\n");
        });
    }

    /* TOUGH NUT TO CRACK. SAVE LIST AND THEN SAVE ITEMS */
    $scope.save = function(){
        // params: [{ "email": "Chuntak@gmail.com", "private": true, "listName":"Chuntak todo list", "iD": -1},
        //     { "email": "Rob@gmail.com", "private": true, "listName":"Rob todo list", "iD": -1}]

        /* SAVE (ADD/UPDATE) LIST */
        $scope.saveList();

        /* SAVE (ADD/UPDATE) ITEM(S) */
        $scope.saveItems();
    }

    $scope.saveList = function () {
        var y = $http({
            method: 'GET',
            url: '/saveList',
            params: {"id": 30}
        }).then(function (response) {
            console.log("it worked:\n" + response.toString());
            //$scope.posts[index]["comments"].splice(commentIndex, 1);
        }, function errorCallBack(response) {
            alert("get messages error\n");
        });
    }

    $scope.saveItems = function(){
        var y = $http({
            method: 'GET',
            url: '/addList',
            params: {"id": 30}
        }).then(function (response) {
            console.log("it worked:\n" + response.toString());
            //$scope.posts[index]["comments"].splice(commentIndex, 1);
        }, function errorCallBack(response) {
            alert("get messages error\n");
        });
    }


    $scope.addList = function(){
        var y = $http({
            method: 'GET',
            url: '/addList',
            params: {"id": 30}
        }).then(function (response) {
            console.log("it worked:\n" + response.toString());
            //$scope.posts[index]["comments"].splice(commentIndex, 1);
        }, function errorCallBack(response) {
            alert("get messages error\n");
        });
    }

    $scope.updateList = function() {
        debugger
        var y = $http({
            method: 'GET',
            url: '/updateList',
            params: {"iD":$scope.selectedList.entity.properties.real_ID, "email":"OMG@gmail.com", "listName":"Changed"}
        }).then(function (response) {
            $scope.responseData = response.data;
            console.log("it worked:\n" + response.toString());
            //$scope.posts[index]["comments"].splice(commentIndex, 1);
        }, function errorCallBack(response) {
            alert("get messages error\n");
        });
    }

    $scope.close = function(result) {
        close(result, 500); // close, but give 500ms for bootstrap to animate
    };

    /*TODO STARTING FROM DOWN*/



    $scope.getItems = function () {
        var y = $http({
            method: 'GET',
            url: '/getItems',
            params: {"listId": $scope.selectedList.entity.properties.real_ID}
        }).then(function (response) {
            $scope.itemList = response.data;
            console.log("it worked:\n" + response.toString());
            //$scope.posts[index]["comments"].splice(commentIndex, 1);
        }, function errorCallBack(response) {
            alert("get messages error\n");
        });
    }

    $scope.addItem = function () {
        var y = $http({
            method: 'GET',
            url: '/addItem',
            params: {"listId": $scope.selectedList.entity.properties.real_ID, "category": "Orange", "description": "Cake",
                "completed": true, "positionInList": 1}
        }).then(function (response) {
            $scope.responseData = response.data;
            console.log("it worked:\n" + response.toString());
            //$scope.posts[index]["comments"].splice(commentIndex, 1);
        }, function errorCallBack(response) {
            alert("get messages error\n");
        });
    }

    $scope.updateItem = function () {
        var y = $http({
            method: 'GET',
            url: '/updateItem',
            params: {"iD": "", "listId": 9876543567, "category": "", "description": "",
                "startDate": "", "endDate": "", "completed": true, "positionInList": 1}
        }).then(function (response) {
            $scope.responseData = response.data;
            console.log("it worked:\n" + response.toString());
            //$scope.posts[index]["comments"].splice(commentIndex, 1);
        }, function errorCallBack(response) {
            alert("get messages error\n");
        });
    }


    /* SAVE ITEMS THAT HAVE AN ID TO DELETE FROM DATASTORE WHEN SAVE */
    $scope.collectDelete = function (item) {
        /* CHECK IF ITEM HAS ID, IF IT DOES PUSH IT IN DELETE_ARRAY */
        if(item.hasID()){
            $scope.toDelete.push(item);
        }
    }
});