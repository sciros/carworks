import com.careworkstech.carworks.Car
import com.careworkstech.carworks.Make
import com.careworkstech.carworks.Model
import com.careworkstech.carworks.Trim

class BootStrap {

    def init = { servletContext ->
                Make subaru = Make.findByName('Subaru') ?: new Make(name: 'Subaru').save(failOnError: true)
                Make chevrolet = Make.findByName('Chevrolet') ?: new Make(name: 'Chevrolet').save(failOnError: true)

                Model forester = Model.findByName('Forester') ?: new Model(make: subaru, name: 'Forester').save()
                Model corvette_stingray = Model.findByName('Corvette Stingray') ?: new Model(make: chevrolet, name: 'Corvette Stingray').save()

                Trim xt = Trim.findByName('XT') ?: new Trim(model: forester, name: 'XT').save()
                Trim z51_1lt = Trim.findByName('Z51 1LT') ?: new Trim(model: corvette_stingray, name: 'Z51 1LT').save()

                Car car1 = Car.findByTrim(xt) ?: new Car(model: forester, trim: xt, mileage: 18500, price: 31000).save()
                Car car2 = Car.findByTrim(z51_1lt) ?: new Car(model: corvette_stingray, trim: z51_1lt, mileage: 500, price: 65000).save()
    }
    def destroy = {
    }
}
