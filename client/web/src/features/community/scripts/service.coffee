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
        url           : APP_BASE_URL + 'community/list'
        method        : 'GET'
  ]
