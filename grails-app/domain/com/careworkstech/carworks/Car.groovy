package com.careworkstech.carworks

class Car {
    Model model
    Trim trim
    Integer mileage
    BigDecimal price

    static constraints = {
        trim nullable: true
    }
}
