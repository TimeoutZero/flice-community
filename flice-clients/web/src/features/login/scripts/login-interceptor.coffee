
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
    $provide.factory 'UserLoginInterceptor', [ '$q', '$injector', '$rootScope' , '$cookies', ($q, $injector, $rootScope, $cookies) ->
      'request': (config) ->
        accessToken = $cookies['accessToken']
        if accessToken then config.headers['Authorization'] = "Bearer #{accessToken}"
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

