angular.module('ac.util.AcTable', [])
.filter('isEmpty', [function() {
	return function(object) {
		return angular.equals({}, object);
	}
}])
.filter('keyCountInObject', [function() {
	return function(object) {
		return Object.keys(object).length;
	}
}])
.directive("acTable", function() {
	return {
	    restrict: 'E',
	    require: 'acTable',
	    scope: {
	    	//"islineclick":"=islineclick"
	    },
	    controller: function($scope, $element) {
	    	var cols = [];
	    	var opCols = [];
            var osCols = [];
	        this.addColumn = function(col) {
	          cols.push(col);
	        };
	        this.getColumns = function() {
	          return cols;
	        };
	        this.addOpColumn = function(col) {
	        	opCols.push(col);
	        };
	        this.getOpColumns = function() {
	          return opCols;
	        };

            this.addOsColumn = function(col) {
                osCols.push(col);
            };
            this.getOsColumns = function() {
                return osCols;
            };

            // this.getIsLineClick = function() {
            //     return $scope.islineclick;
            // };
	    },
	    link: function(scope, element, attributes, acTableCtrl) {
	    }
	}
})
.directive("acTableColumn", function() {
	return {
	    restrict: 'E',
	    require: '^acTable',
	    link: function(scope, element, attributes, acTableCtrl) {
	    	acTableCtrl.addColumn({
	            header: attributes.header,
	            field: attributes.field,
	            style: attributes.style,
	            sortable: attributes.sortable
	    	});
	    }
	}
})
.directive("acTableColumnOp", function() {
	return {
		restrict: 'E',
	    require: '^acTable',
	    scope: {
	    	op: '='
	    },
	    link: function(scope, element, attributes, acTableCtrl) {
	    	var opl = {
	    		content: attributes.content,
	    		op: scope.op,
	    		tooltip: attributes.tooltip,
	    		tooltipPlacement: attributes.tooltipPlacement
	    	};
	    	acTableCtrl.addOpColumn(opl);
	    }
	}
})
.directive("acTableColumnOs", function() {
    return {
        restrict: 'E',
        require: '^acTable',
        scope: {
            os: '='
        },
        link: function(scope, element, attributes, acTableCtrl) {
            var osl = {
                header: attributes.header,
                content: attributes.content,
                os: scope.os,
                tooltip: attributes.tooltip,
                tooltipPlacement: attributes.tooltipPlacement
            };
            acTableCtrl.addOsColumn(osl);
        }
    }
})
.directive("acTableScroll", function() {
	return {
		restrict: 'A',
		replace: false,
		compile: function(element, attrs){
			element.on('scroll', function(e){
			});
		}
	}
})
.run(function($templateCache) {
	var templateN1 = '\
    	<div class="actable">\
			<div class="headerRow grey">\
				<div ng-show="selectable" class="cell checkbox"></div>\
				<div ng-repeat="col in cols" class="cell" ng-style="col.style" ng-class="{\'sort\': col.sortable}" ng-click="toggleSort(col.sortable,col.field)" style="{{col.style}}">\
					<span>{{col.header}}</span><i ng-if="col.sortable==\'true\'" class="pull-right material-icons sort-toggle-icon" ng-class="getSortDir(col.sortable,col.field)">arrow_downward</i>\
				</div>\
				<div ng-if="opCols && opCols.length > 0" class="cell operator"><span>操作</span></div>\
			</div>\
			<div class="ac-container" ng-style="containerStyle" ac-table-scroll>\
			<div class="row" ng-repeat="item in items.invdata" ng-click="toggleSelectItems(item,$event)">\
				<div ng-show="selectable" class="cell checkbox">\
					<md-checkbox ng-checked="isItemSelected(item)" ng-click="toggleSelectItems(item, $event)" aria-label="Checkbox"></md-checkbox>\
				</div>\
				<div ng-if="osCols && osCols.length > 0" class="cell">\
					<span class="operator" ng-repeat="osCol in osCols" ng-click="osClick(osCol, item);" uib-tooltip="{{osCol.tooltip}}" ng-bind-html="osCol.content"></span>\
				</div>\
				<div ng-if="opCols && opCols.length > 0" class="cell">\
					<span class="operator" ng-repeat="opCol in opCols" ng-click="opClick(opCol, item);" uib-tooltip="{{opCol.tooltip}}" ng-bind-html="opCol.content"></span>\
				</div>\
			</div>\
			</div>\
		</div>\
		';

	var template = '\
    	<div class="actable">\
			<div class="row header grey">\
				<div ng-show="selectable" class="cell checkbox"></div>\
				<div ng-repeat="col in cols" class="cell" ng-style="col.style" ng-class="{\'sort\': col.sortable}" ng-click="toggleSort(col.sortable,col.field)" style="{{col.style}}">\
					<span>{{col.header}}</span><i ng-if="col.sortable==\'true\'" class="pull-right material-icons sort-toggle-icon" ng-class="getSortDir(col.sortable,col.field)"><i class="fa fa-angle-down" aria-hidden="true"/></i>\
				</div>\
				<div ng-repeat="col in osCols" class="cell">\
					<span>{{col.header}}</span><i ng-if="col.sortable==\'true\'" class="pull-right material-icons sort-toggle-icon" ng-class="getSortDir(col.sortable,col.field)"></i>\
				</div>\
				<div ng-if="opCols && opCols.length > 0" class="cell operator"><span>操作</span></div>\
			</div>\
			<div class="row" ng-repeat="item in items.invdata" ng-click="toggleSelectItems(item,$event)">\
				<div ng-show="selectable" class="cell checkbox">\
					<md-checkbox ng-checked="isItemSelected(item)" ng-click="toggleSelectItems(item, $event)" aria-label="Checkbox"></md-checkbox>\
				</div>\
				<div ng-repeat="col in cols" class="cell" ng-style="col.style" ng-click="lineclick(item)">{{item[col.field]}}</div>\
				<div ng-repeat="col in osCols" class="cell" ng-style="osStyle">\
					<span class="operator" ng-click="osClick(col, item);" uib-tooltip="{{osCol.tooltip}}" ng-bind-html="col.content"></span>\
				</div>\
				<div ng-if="opCols && opCols.length > 0" class="cell" ng-style="opStyle">\
					<span class="operator" ng-repeat="opCol in opCols" ng-click="opClick(opCol, item);" uib-tooltip="{{opCol.tooltip}}" ng-bind-html="opCol.content"></span>\
				</div>\
			</div>\
		</div>\
		';
	$templateCache.put('ac-table-directives', template)
})
.directive("acTableGrid",['$compile','$templateCache', function($compile,$templateCache) {
	return {
	    restrict: 'E',
	    transclude: true,
	    require: '^acTable',
	    scope: {
	    	items: '=', 
	    	selectable: '=',
	    	multiple: '=',
	    	selectedItems: '=',//选中的项目idField数组
	    	idField: '@',//用于
	    	paging: '=',
	    	onSortChange: '&',
	    	width: '@',
	    	autowidth: '@',
            lineclick : '=lineclick',
	    },
	    template: $templateCache.get('ac-table-directives'),
	    link: function(scope, element, attributes, acTableCtrl) {
	    	scope.cols = acTableCtrl.getColumns();
	    	scope.opCols = acTableCtrl.getOpColumns();
            scope.osCols = acTableCtrl.getOsColumns();
	    	//scope.isLineClick=acTableCtrl.getIsLineClick();
            // if (scope.isLineClick != undefined && scope.isLineClick>0){
	    	// 	scope.showClick="show(item,"+scope.isLineClick+")"
            // }
            // else
            // {
				// scope.showClick="";
            // }
	    	
	    	if(!scope.width || scope.autowidth) {
	    		if(scope.opCols.length > 0) {
	    			opStyle =  {'min-width': '100px'}
	    		}
	    		
	    		for(var i = 0; i < scope.cols.length; i++) {
	    			scope.cols[i].style =  {
	    				'width': (100/scope.cols.length) + '%'
	    			}
	    		}
	    	}
	    	
	    	scope.containerStyle = {
    			'height': '250px'
	    	};
	    	scope.opClick = function(opCol, item) {
                opCol.op(item);
	    	}

            scope.osClick = function(osCol, item) {
                osCol.os(item);
            }
            // scope.show=function (item) {
            //     scope.lineclick(item);
            // }
	    	scope.$watch('items', function () {
	    		var data = $templateCache.get('ac-table-directives');
	    		element.html(data);
                $compile(element.contents())(scope);
	    	});
	    	scope.colCanSort = function(item) {
	    		return item;
	    	};
	    	scope.getSortDir = function(sortable, sort) {
	    		if(sortable != 'true') {
	    			return 'none';
	    		}
	    		else if(scope.paging && scope.paging.sort == sort) {
					return scope.paging.dir;
				}
				else {
					return 'none';
				}
	    	};
	    	scope.toggleSort = function(sortable,sort) {
	    		if(sortable != 'true') {
	    			return;
	    		}
	    		
	    		scope.paging.sort = sort;
				if(scope.paging.dir == 'asc') {
					scope.paging.dir = 'desc';
				}
				else {
					scope.paging.dir = 'asc';
				}
	    		scope.onSortChange();
	    	};
	    	
	    	scope.isItemSelected = function(item) {
	    		if(!scope.selectable) {
	    			return false;
	    		}
	    		if(scope.selectedItems[item[scope.idField]]) {
	    			return true;
	    		}
	    		else {
	    			return false;
	    		}
	    	};
	    	scope.toggleSelectItems = function(item, event) {
	    		if(!scope.selectable) {
	    			return;
	    		}
	    		
				if (scope.selectedItems[item[scope.idField]]) {
					delete scope.selectedItems[item[scope.idField]];
			    }
			    else {
			    	if(scope.multiple) {//多选
			    		scope.selectedItems[item[scope.idField]] = item;
			    	}
			    	else {//单选
			    		scope.selectedItems = {};
			    		scope.selectedItems[item[scope.idField]] = item;
			    	}
			    }
				event.stopPropagation();
	    	}
	    }
	}
}]);
