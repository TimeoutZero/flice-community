
'use strict'

# =============================================
# Module
# =============================================
angular.module 'FliceCommunityWeb.controllers'

  # =============================================
  # CommunityCreateController
  # =============================================
  .controller 'CommunityCreateController', ['$scope', '$window',  '$state',
    ($scope, $window, $state) ->

	    $scope.create = =>
	    	console.log $scope.community

  ]
