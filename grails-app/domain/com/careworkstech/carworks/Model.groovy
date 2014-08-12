package com.careworkstech.carworks

class Model {
    String name
    Make make

    static constraints = {
    }

    static mapping = {
        table 'car_model'
        id column: 'car_model_id'
    }
}
