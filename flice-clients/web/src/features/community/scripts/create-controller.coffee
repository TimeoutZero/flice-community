
'use strict'

# =============================================
# Module
# =============================================
angular.module 'FliceCommunityWeb.controllers'

  # =============================================
  # CommunityCreateController
  # =============================================
  .controller 'CommunityCreateController', ['$scope', '$window',  '$state', 'CommunityService'
    ($scope, $window, $state, communityService) ->

	    $scope.create = =>
	    	communityService.create $scope.community

  ]
