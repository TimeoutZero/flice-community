'use strict'

# =============================================
# Module
# =============================================
angular.module 'FliceCommunityWeb'

  # =============================================
  # App Config
  # =============================================
  .config( ['$stateProvider', '$urlRouterProvider', ($stateProvider, $urlRouterProvider) ->

    # Default State
    # =============================================
    $urlRouterProvider.otherwise('/community/list')


  ])
