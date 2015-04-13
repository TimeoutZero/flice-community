
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
	    	promise = communityService.create $scope.community
	    	promise.success (data) ->
          $state.go("community.self", { id : data.id })

  ]
