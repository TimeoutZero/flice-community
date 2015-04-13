'use strict'

# =============================================
# Module
# =============================================
angular.module 'FliceCommunityWeb'

  # =============================================
  # App Config
  # =============================================
  .config( ['$stateProvider', '$urlRouterProvider', ($stateProvider, $urlRouterProvider) ->

    # States
    # =============================================
    $stateProvider


      # community
      # ==============================
      .state('community'
        url         : '/community'
        templateUrl : 'views/features/community/views/community.html'
        controller  : 'CommunityController'
        abstract    : yes
      )

      .state('community.self'
        url         : '/:id'

        views :
          '' :
            controller  : 'CommunitySelfController'
            templateUrl : 'views/features/community/views/self.html'
          'community-menu@community.self':
            controller  : 'CommunitySelfMenuController'
            templateUrl: 'views/features/community/views/self/menu.html'
          'community-description@community.self':
            controller  : 'CommunitySelfDescriptionController'
            templateUrl: 'views/features/community/views/self/description.html'
          'community-members@community.self':
            controller  : 'CommunitySelfMembersController'
            templateUrl: 'views/features/community/views/self/members.html'
      )

      .state('community.create'
        url         : '/create'
        templateUrl : 'views/features/community/views/create.html'
        controller  : 'CommunityCreateController'
      )

      .state('community.edit'
        url         : '/edit'
        templateUrl : 'views/features/community/views/edit.html'
        controller  : 'CommunityEditController'
      )

      .state('community.list'
        url         : '/list'
        templateUrl : 'views/features/community/views/list.html'
        controller  : 'CommunityListController'
      )
  ])
