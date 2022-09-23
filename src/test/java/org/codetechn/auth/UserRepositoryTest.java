package org.codetechn.auth;
import static org.assertj.core.api.Assertions.assertThat;

import org.codetechn.auth.entity.Role;
import org.codetechn.auth.entity.User;
import org.codetechn.auth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUser (){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("12345");

        User newUser = new User("lightorochi32@gmail.com","698777078",password);
        User savedUser = userRepository.save(newUser);


        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testAsignARole(){
        int userId=2;
        int role=1;
        User user =userRepository.findById(userId).get();
        user.addRole(new Role(role));

        User updatedUser = userRepository.save(user);

        assertThat(updatedUser.getRoles()).hasSize(1);
    }

}
