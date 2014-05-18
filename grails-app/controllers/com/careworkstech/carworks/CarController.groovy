package com.careworkstech.carworks

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class CarController {
    static scaffold = true

    def index() { }
}
