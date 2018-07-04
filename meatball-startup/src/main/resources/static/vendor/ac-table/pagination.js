angular.module('ac.util.Pagination', [])
.controller('PaginationController', ['$scope', '$attrs', '$parse', function ($scope, $attrs, $parse) {
	var self = this,
    	ngModelCtrl = { $setViewValue: angular.noop }, // nullModelCtrl
    	setNumPages = $attrs.numPages ? $parse($attrs.numPages).assign : angular.noop;
    	
    this.init = function(ngModelCtrl_, config) {
    	ngModelCtrl = ngModelCtrl_;
        this.config = config;
        
        ngModelCtrl.$render = function() {
            self.render();
        };
        $scope.$watch('paging.limit', function() {
        	$scope.totalPages = self.calculateTotalPages();
        });
        
        $scope.$watch('totalItems', function() {
            $scope.totalPages = self.calculateTotalPages();
        });
        $scope.$watch('totalPages', function(value){
        	setNumPages($scope.$parent, value);
        	
        	if($scope.page > value) {
        		$scope.selectPage(value);
        	}
        	else {
        		ngModelCtrl.$render();
        	}
        })
    };
    
    this.calculateTotalPages = function() {
    	var totalPages = $scope.paging.limit < 1 ? 1 : Math.ceil($scope.totalItems / $scope.paging.limit);
    	return Math.max(totalPages || 0, 1);
    }
    
    this.render = function() {
    	$scope.page = parseInt(ngModelCtrl.$viewValue, 10) || 1;
    }
    
    $scope.selectPage = function(page, evt) {
    	if ( $scope.page !== page && page > 0 && page <= $scope.totalPages) {
    		if(evt && evt.target) {
    			evt.target.blur();
    		}
    		ngModelCtrl.$setViewValue(page);
    		ngModelCtrl.$render();
    		$scope.onPageChange();
    	}
    }
}])
.constant('paginationConfig', {
	rotate: true
})
.directive('paginationTow', ['$parse', 'paginationConfig', function($parse, paginationConfig) {
	return {
		restrict: 'EA',
	    scope: {
	      totalItems: '=',
	      onPageChange: '&',
	      paging: '='
	    },
	    require: ['paginationTow', '?ngModel'],
	    controller: 'PaginationController',
	    replace: true,
	    template: '\
	    	<div class="row">\
	    		<div class="pull-right">\
	    			<div class="ac_pagination">\
	    				<div style="float:left;margin: 5px 10px">共{{totalItems}}条记录,每页<select ng-options="o for o in paging.limitOptions" ng-model="paging.limit" ng-change="onPageChange()"></select>条,页码 {{page}} / {{totalPages}}</div>\
	    				<button ng-disabled="page==1" class="page" ng-click="selectPage(1, $event)">首页</button>\
	    				<button ng-disabled="page<=1" class="page" ng-click="selectPage(page - 1, $event)">上一页</button>\
	    				<button class="page" ng-repeat="page in pages track by $index" ng-class="{\'active\': page.active}" ng-click="selectPage(page.number, $event)">{{page.text}}</button>\
	    				<button ng-disabled="page>=totalPages" class="page" ng-click="selectPage(page + 1, $event)">下一页</button>\
	    				<button ng-disabled="page==totalPages" class="page" ng-click="selectPage(totalPages, $event)">尾页</button>\
	    			</div>\
	    		</div>\
	    	</div>\
	    ',
			//<button  class="page" >{{page.number}}</button>\
		//<button class="page" ng-repeat="page in pages track by $index" ng-class="{\'active\': page.active}" ng-click="selectPage(page.number, $event)">{{page.text}}</button>\
        link: function(scope, element, attrs, ctrls) {
			var paginationCtrl = ctrls[0], ngModelCtrl = ctrls[1];
			if (!ngModelCtrl) {
		         return;
			}
			
			var maxSize = scope.paging.maxSize;
			var rotate = angular.isDefined(attrs.rotate) ? scope.$parent.$eval(attrs.rotate) : paginationConfig.rotate;
			
			paginationCtrl.init(ngModelCtrl, paginationConfig);
			
			function makePage(number, text, isActive) {
				return {
					number: number,
					text: text,
					active: isActive
				};
			}
			
			function getPages(currentPage, totalPages) {
				  var pages = [];
				  var startPage = 1, endPage = totalPages;
				  var isMaxSized = ( angular.isDefined(maxSize) && maxSize < totalPages );

				  if ( isMaxSized ) {
				    if ( rotate ) {
				      startPage = Math.max(currentPage - Math.floor(maxSize/2), 1);
				      endPage   = startPage + maxSize - 1;
				  
				      if (endPage > totalPages) {
				        endPage   = totalPages;
				        startPage = endPage - maxSize + 1;
				      }
				    } else {
				      startPage = ((Math.ceil(currentPage / maxSize) - 1) * maxSize) + 1;
				      endPage = Math.min(startPage + maxSize - 1, totalPages);
				    }
				  }
				  var pagenSize=10;
				  //如果是前五页或者后5页
                if (currentPage<pagenSize  )
                {
                    startPage=1;
                    endPage=pagenSize>totalPages?totalPages:pagenSize;
                }
                //后五页
                else if (currentPage>totalPages-pagenSize){
                    startPage=totalPages-pagenSize;
                    endPage=totalPages;
                }
                //中间
                else {
                    startPage=currentPage-(pagenSize/2-1);
                    endPage=currentPage+(pagenSize/2-1);
                }
				  for (var number = startPage; number <= endPage; number++) {
				    var page = makePage(number, number, number === currentPage);
				    pages.push(page);
				  }
				  
				  if ( isMaxSized && ! rotate ) {
				    if ( startPage > 1 ) {
				      var previousPageSet = makePage(startPage - 1, '...', false);
				      pages.unshift(previousPageSet);
				    }
				  
				    if ( endPage < totalPages ) {
				      var nextPageSet = makePage(endPage + 1, '...', false);
				      pages.push(nextPageSet);
				    }
				  }
				  return pages;
			}
			
			var originalRender = paginationCtrl.render;
			paginationCtrl.render = function() {
			    originalRender();
			    if (scope.page > 0 && scope.page <= scope.totalPages) {
					scope.pages = getPages(scope.page, scope.totalPages);
			    }
			};
		}
	}
}]);
