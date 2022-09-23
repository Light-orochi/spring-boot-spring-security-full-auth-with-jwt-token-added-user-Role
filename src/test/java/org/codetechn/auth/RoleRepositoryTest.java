package org.codetechn.auth;

import org.codetechn.auth.entity.Role;
import org.codetechn.auth.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTest
{
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testCreateRoles(){
        Role admin = new Role("ROLE_ADMIN");
        Role moderator = new Role("ROlE_MODERATOR");
        Role user = new Role("ROLE_USER");
        roleRepository.save(admin);
        roleRepository.save(moderator);
        roleRepository.save(user);

    }


}
