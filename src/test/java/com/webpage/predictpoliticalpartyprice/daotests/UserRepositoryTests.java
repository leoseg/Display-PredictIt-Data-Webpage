package com.webpage.predictpoliticalpartyprice.daotests;


import com.webpage.predictpoliticalpartyprice.dao.UserRepository;
import com.webpage.predictpoliticalpartyprice.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {

    @Resource
    TestEntityManager entityManager;

    @Resource
    UserRepository repo;

    @Test
    public void givenUser_whenRepoSaveUser_thenEntityManagerFindUserShouldFindUser(){
        User exspectedUser = new User();
        exspectedUser.setEmail("testuser@gmail.com");
        exspectedUser.setPassword("testuser202201");
        exspectedUser.setUsername("testuser");


        User savedUser = repo.save(exspectedUser);

        User actualUser = entityManager.find(User.class, savedUser.getId());

        assertThat(exspectedUser.getEmail()).isEqualTo(actualUser.getEmail());
        assertThat(exspectedUser.getPassword()).isEqualTo(actualUser.getPassword());
        assertThat(exspectedUser.getUsername()).isEqualTo(actualUser.getUsername());
    }

}