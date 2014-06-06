package com.careworkstech.carworks

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.springframework.web.servlet.ModelAndView
import spock.lang.Specification

@TestFor(SearchController)
@Mock([User, Car])
class SearchControllerSpec extends Specification {

    def setup() {
        //create some cars to search on
        User.metaClass.encodePassword = {-> }
        User testUser = new User(username: 'testUser',
                password: 'testPass',
                accountExpired: false,
                accountLocked: false,
                passwordExpired: false,
                id: 1000)
        testUser.save()

        new Car(user: testUser, make: 'Mercedes', model: 'CLA45AMG', year: 2014, condition: Condition.NEW).save()
        new Car(user: testUser, make: 'Subaru', model: 'WRX', year: 2013, condition: Condition.GREAT).save()
        new Car(user: testUser, make: 'Ford', model: 'Focus', trim: 'SV', year: 2012, condition: Condition.GREAT).save()
        new Car(user: testUser, make: 'Subaru', model: 'Forester', trim: 'XT', year: 2008, condition: Condition.MEH).save()
    }

    void "search should return the searchResults view with a list of cars given search params"() {
        given: 'search params'
        params.make = make
        params.model = model
        params.trim = trim
        params.year = year

        when: 'search is invoked'
        ModelAndView modelAndView = controller.search()

        then:
        List<Car> cars = (List<Car>) modelAndView.model.carInstanceList
        cars.size() == expectedSize

        where:
        make       | model | trim | year | expectedSize
        'Mercedes' | null  | null | null | 1
        null       | 'WRX' | null | null | 1
        'Subaru'   | null  | null | null | 2
        null       | null  | null | 2012 | 1
        'Subaru'   | null  | null | 2008 | 1
        'Mercedes' | 'S550' | null | null | 0
    }
}
