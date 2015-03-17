'use strict'

# =============================================
# Module
# =============================================
angular.module 'FliceCommunityWeb.controllers'

  # =============================================
  # LoginController
  # =============================================
  .controller 'LoginController', ['$scope', '$window', 'LoginService', '$state', '$filter', '$modal', '$q',
    ($scope, $window, LoginService, $state, $filter, $modal, $q) ->

      # =============================================
      # Attributes
      # =============================================
      $scope.user       =
        firstName  : null
        email      : null

      $scope.ableToConnectWithFacebook  = yes


      # =============================================
      # Initialize
      # =============================================

      # =============================================
      # Handlers
      # =============================================

      # =============================================
      # Methods
      # =============================================

      $scope.doLogin  = ->
        promise = LoginService.login($scope.user)
        promise.success (data, status, headers, config) -> $scope.openNotificationModal('Connected')
        promise.error (data, status, headers, config, statusText) ->
          $scope.openNotificationModal('Could not connect')


      # Aux Methods
      # ===============================
      $scope.openNotificationModal = (text) ->
        $modal.open
          windowClass : 'modal modal-theme-plain myo-modal'
          backdrop    : yes
          template    : """
            <h3>#{text}</h3>
          """




      return @

  ]