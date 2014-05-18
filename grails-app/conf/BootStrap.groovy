import com.careworkstech.carworks.Role
import com.careworkstech.carworks.User
import com.careworkstech.carworks.UserRole

class BootStrap {

    def init = { servletContext ->
        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)

        def user1 = User.findByUsername('user1') ?: new User(
                username: 'user1',
                password: 'P4ssw0rd',
                enabled: true).save(failOnError: true)

        def adminUser = User.findByUsername('admin') ?: new User(
                username: 'admin',
                password: 'P4ssw0rd',
                enabled: true).save(failOnError: true)

        if (!user1.authorities.contains(userRole)) {
            UserRole.create user1, userRole
        }

        if (!adminUser.authorities.contains(adminRole)) {
            UserRole.create adminUser, adminRole
        }

    }
    def destroy = {
    }
}
