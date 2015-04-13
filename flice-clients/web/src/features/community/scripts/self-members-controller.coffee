'use strict'

# =============================================
# Module
# =============================================
angular.module 'FliceCommunityWeb.controllers'

  # =============================================
  # CommunityListController
  # =============================================
	.controller 'CommunitySelfMembersController', ['$scope', '$window', '$state', '$stateParams', 'CommunityService',
		($scope, $window, $state, $stateParams, communityService) ->

	]