package com.careworkstech.carworks

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.web.servlet.ModelAndView

@Secured(['ROLE_USER'])
class SearchController {
    def index() {}

    def search() {
        String make = params.make
        String model = params.model
        String trim = params.trim
        Integer year = params.int('year')

        def carCriteria = Car.createCriteria()
        List<Car> cars = carCriteria.list {
            and {
                if (make) {
                    eq('make', make)
                }
                if (model) {
                    eq('model', model)
                }
                if (trim) {
                    eq('trim', trim)
                }
                if (year) {
                    eq('year', year)
                }
            }
        }

        return new ModelAndView('/search/searchResults', [ carInstanceList: cars ])
    }
}
