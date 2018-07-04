/**=========================================================
 * Module: userCtrl,js
 * Angular userCtrl controller
 =========================================================*/

App.controller('userCtrl',  function($scope, $filter , $http, ngDialog) {
    $scope.pagination = {pagestart: 0, pagesize: 20, maxsize: 8,count:200, pageindex: 1, limitOptions:[10,20,50,100]};
    $scope.data = {};
    $scope.info={timestamp:new Date().getTime()};
    $scope.searchForm = {extLimit: $scope.pagination,data: $scope.data,info:$scope.info};
    $scope.search = function() {
        $http.post("/api/v1/user/findLimit",$scope.searchForm).
        success(function(data) {
            //data赋值到invdata，列表数据名称不能变更
            data.invdata=data.data;
            //赋值给$scope.data
            $scope.data = data;
            //页码参数名称不能变更
            $scope.pagination['limit']=$scope.data.extlimit.pagesize;
        }).
        error(function(err) {
            //错误代码
        });
    };
    $scope.pageChanged = function() {
        $scope.pagination.pagesize=$scope.pagination.limit;
        $scope.pagination.pagestart = ($scope.pagination.pageindex - 1) * $scope.pagination.pagesize;
        $scope.search();
    };
    $scope.clearSearch = function() {
        $scope.pagination.pagestart = 0;
        $scope.pagination.pageindex = 1;
        $scope.searchForm = {extlimit: $scope.pagination};
        $scope.search();
    };
    $scope.edit = function(item) {
        console.log(ngDialog);
        var rv = undefined;
        if(item) {
            rv = item.id;
        }
        ngDialog.open({
            templateUrl: '../templates/borrow/borrow_edit.html',
            className: 'ngdialog-theme-default',
            scope: $scope,
            data:item,
            controller: 'borrowEditCtrl',
            preCloseCallback: function(value) {
                if (confirm('Close it?  (Value = ' + value + ')')) {
                    return true;
                }
                return false;
            }
        });
    };

    $scope.del = function(item) {
        var result = confirm('是否确认删除 ?');
        if (result) {
            $scope.searchForm.data.id = id;
            $http({
                method: 'POST',
                url: '/api/conl/user/del',
                data: $scope.searchForm,
                headers: {
                    'Content-Type': "application/json"
                }
            }).then(function (data) {
                var list = data.data;
                if (list.info.status == 200) {
                    alert(list.info.message);
                    $scope.search();
                } else {
                    alert(list.info.message);
                }
                $scope.closeThisDialog();
            }).catch(function (data) {
                console.log(data);
            });
        }
    };

    $scope.show = function (item) {
        /*ngDialog.open({
            templateUrl: '../templates/borrow/borrow_show.html',
            className: 'ngdialog-theme-default',
            scope: $scope,
            data:item,
            controller: 'borrowShowCtrl'
        });*/
    };

    $scope.showHistory = function (item) {
        ngDialog.open({
            templateUrl: '../templates/borrow/borrow_show.html',
            className: 'ngDialog-theme-plain',
            scope: $scope,
            data:item,
            controller: 'showHistoryCtrl'
        });
    };
    $scope.returnBack = function (item) {
        ngDialog.open({
            templateUrl: '../templates/borrow/return_back.html',
            className: 'ngdialog-theme-default',
            scope: $scope,
            data:item,
            controller: 'returnBackCtrl',
            preCloseCallback: function(value) {
                $scope.search();
                return true;
            }
        });
    };
    $scope.search();
});