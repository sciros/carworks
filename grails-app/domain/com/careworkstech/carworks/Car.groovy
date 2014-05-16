package com.careworkstech.carworks

class Car {
    String make
    String model
    String trim
    Integer year
    //Condition condition

    static constraints = {
        trim nullable: true
    }
}