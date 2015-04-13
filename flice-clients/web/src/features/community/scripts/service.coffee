'use strict'

# =============================================
# Module
# =============================================
angular.module 'FliceCommunityWeb.services'

  # =============================================
  # CommunityService
  # =============================================
  .service 'CommunityService', ['$http', 'APP_BASE_URL', ($http, APP_BASE_URL) ->

    get: (data) ->
      $http
        url           : APP_BASE_URL.CORE + 'community/list'
        method        : 'GET'

    getById: (id) ->
      $http
        url           : APP_BASE_URL.CORE + 'community/#{id}'
        method        : 'GET'

    create: (data) ->
    	$http
    		url 					: APP_BASE_URL.CORE + "community"
    		method 				: 'POST'
    		data					: data
  ]
