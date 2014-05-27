package com.careworkstech.carworks

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.*
import spock.lang.*

@TestFor(CarController)
@Mock([Car,User])
class CarControllerSpec extends Specification {
    User testUser
    SpringSecurityService mockSpringSecurityService

    def setup () {
        User.metaClass.encodePassword = { -> }
        testUser = new User(username: 'testUser',
                            password: 'testPass',
                            accountExpired: false,
                            accountLocked: false,
                            passwordExpired: false,
                            id: 1000)
        testUser.save(flush: true)
        mockSpringSecurityService = Mock(SpringSecurityService)
        controller.springSecurityService = mockSpringSecurityService
        mockSpringSecurityService.getCurrentUser() >> { testUser } //need here because multiple specs for save
    }

    def populateValidParams(params) {
        assert params != null
        params['make'] = 'Subaru'
        params['model'] = 'Forester'
        params['trim'] = 'XT'
        params['year'] = 2014
        params['user'] = testUser
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.carInstanceList
            model.carInstanceCount == 0
    }

    void "showForUser should return a list of cars belong to the user given the user id" () {
        given: 'a car exists for a test user'
            Car car = new Car(make: 'BMW', model: '550i', year: 2013, user: testUser)
            car.save(failOnError: true, flush: true)
            Car car2 = new Car(make: 'Mercedes', model: 'CLA45AMG', year: 2014, user: testUser)
            car2.save(failOnError: true, flush: true)

        when: 'showForUser is invoked for the test user'
            controller.showForUser(testUser)

        then: 'the model contains a list of cars belonging to the test user'
            model.carInstanceList.all.size == 2
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.carInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            def car = new Car()
            car.validate()
            controller.save(car)

        then:"The create view is rendered again with the correct model"
            model.carInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            car = new Car(params)

            controller.save(car)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/car/show/1'
            controller.flash.message != null
            Car.count() == 1
    }

    def 'save should set user to be the currently logged in user' () {
        given: 'a car with no user and a logged in user'
            Car car = new Car()
            car.make = 'Subaru'
            car.model = 'Forester'
            car.year = 2014

        when: 'save is invoked, passing in the car'
            controller.save(car)

        then: 'the car has user set to the currently logged in user'
            car.user == testUser
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def car = new Car(params)
            controller.show(car)

        then:"A model is populated containing the domain instance"
            model.carInstance == car
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def car = new Car(params)
            controller.edit(car)

        then:"A model is populated containing the domain instance"
            model.carInstance == car
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/car/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def car = new Car()
            car.validate()
            controller.update(car)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.carInstance == car

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            car = new Car(params).save(flush: true)
            controller.update(car)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/car/show/$car.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/car/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def car = new Car(params).save(flush: true)

        then:"It exists"
            Car.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(car)

        then:"The instance is deleted"
            Car.count() == 0
            response.redirectedUrl == '/car/index'
            flash.message != null
    }
}
