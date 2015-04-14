'use strict'


# =============================================
# Modules
# =============================================
angular.module 'FliceCommunityWeb.controllers' , []
angular.module 'FliceCommunityWeb.filters'     , []
angular.module 'FliceCommunityWeb.factories'   , []
angular.module 'FliceCommunityWeb.services'    , []
angular.module 'FliceCommunityWeb.constants'   , []
angular.module 'FliceCommunityWeb.directives'  , []


# =============================================
# Scripts Module
# =============================================
angular.module 'FliceCommunityWeb.scripts'     , [
  'FliceCommunityWeb.controllers'
  'FliceCommunityWeb.constants'
  'FliceCommunityWeb.filters'
  'FliceCommunityWeb.factories'
  'FliceCommunityWeb.services'
  'FliceCommunityWeb.directives'
]


# =============================================
# Main Module
# =============================================
angular.module('FliceCommunityWeb', [
  'ngCookies'
  'ngSanitize'
  'QuickList'
  'ui.router'
  'ui.bootstrap'
  'FliceCommunityWeb.scripts'
])


  # =============================================
  # Initialize
  # =============================================
  .run([ () ->

    # Import underscore-string to underscore
    # =================================
    _.mixin(_.string.exports())

    # Change Moment relative time
    moment.lang 'pt-br',
      relativeTime :
        future: "em %s"
        past:   "%s atrás"
        s:  "segundos"
        m:  "um minuto"
        mm: "%d minutos"
        h:  "uma hora"
        hh: "%d horas"
        d:  "um dia"
        dd: "%d dias"
        M:  "um mês"
        MM: "%d meses"
        y:  "um ano"
        yy: "%d anos"

    moment.lang('pt-br')

  ])

  # =============================================
  # httpProvider Config
  # =============================================
  .config( ['$httpProvider', ($httpProvider) ->

    # Customize $httpProvider
    # =============================================
    # $httpProvider.defaults.transformRequest  = (data) -> if data then $.param(data) else data
    # $httpProvider.defaults.headers.post      = "Content-Type": 'application/json'
  ])





