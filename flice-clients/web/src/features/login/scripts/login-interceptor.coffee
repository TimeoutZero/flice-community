
# =============================================
# Module
# =============================================
angular.module 'FliceCommunityWeb'


  # =============================================
  # Config
  # =============================================
  .config( ['$httpProvider', '$provide', ($httpProvider, $provide) ->

    # =============================================
    # UserLoginInterceptor
    # =============================================
    $provide.factory 'UserLoginInterceptor', [ '$q', '$injector', '$rootScope', ($q, $injector, $rootScope) ->

      'request': (config) ->
        if $rootScope.accessToken then config.headers['Authorization'] = "Bearer #{$rootScope.accessToken}"
        return config

      'requestError': (rejection)->
        return $q.reject rejection

      'response': (response)->
        return response or $q.when response

      'responseError': (rejection)->
        if rejection.status is 401
          $injector.invoke ['$state', '$rootScope', ($state, $rootScope) ->
            $rootScope.accessToken = null

            if $state.current.data.restrict
              $rootScope.user = null
              $state.go 'login'
          ]

        return  $q.reject rejection

    ]

    # =============================================
    # Register Interceptor
    # =============================================
    $httpProvider.interceptors.push 'UserLoginInterceptor'

  ])

