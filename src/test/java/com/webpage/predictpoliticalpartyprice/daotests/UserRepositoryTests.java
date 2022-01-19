package com.webpage.predictpoliticalpartyprice.daotests;


import com.webpage.predictpoliticalpartyprice.dao.UserRepository;
import com.webpage.predictpoliticalpartyprice.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setEmail("testuser@gmail.com");
        user.setPassword("testuser202201");
        user.setUsername("testuser");


        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
        assertThat(user.getPassword()).isEqualTo(existUser.getPassword());
        assertThat(user.getUsername()).isEqualTo(existUser.getUsername());
    }

}