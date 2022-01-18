package com.webpage.predictpoliticalpartyprice.dao;

import com.webpage.predictpoliticalpartyprice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Extends the jpa repository to access the user entity in database
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * Query the user entry corresponding to the username
     *
     * @param username name of user
     * @return User object
     */
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByuserName(String username);
}