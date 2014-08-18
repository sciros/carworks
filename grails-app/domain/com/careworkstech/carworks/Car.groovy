package com.careworkstech.carworks

class Car {
    Model model
    Trim trim
    Integer mileage = 0
    BigDecimal price

    static constraints = {
        trim nullable: true
    }

    String toString () {
      model?.make?.toString() + ' ' + model.toString() + ' ' + trim.toString() + ' ' + id.toString()
    }
}
