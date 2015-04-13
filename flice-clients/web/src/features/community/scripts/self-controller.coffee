'use strict'

# =============================================
# Module
# =============================================
angular.module 'FliceCommunityWeb.controllers'

  # =============================================
  # CommunityListController
  # =============================================
	.controller 'CommunitySelfController', ['$scope', '$window', '$state', '$stateParams', 'CommunityService',
		($scope, $window, $state, $stateParams, communityService) ->

			$scope.community = ''

			$scope.populate = =>

				communityId = $stateParams.id
				promise 		= communityService.getById communityId

				promise.success (data) ->

					community 						=	{}
					community.name 				=	data[0].name
					community.description	= data[0].description
					community.image				= data[0].image

					$scope.community = community
				promise.error (data, status) ->
					$scope.openNotificationModal('Fail')

			$scope.populate()

	]