package com.webpage.predictpoliticalpartyprice.security;
import static org.junit.Assert.*;
import com.webpage.predictpoliticalpartyprice.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class CustomUserDetailsTests {

    @Test
    public void givenUserWithPasswordName_whenCustomUserDetailsCreatedFromUser_thenGivenAuthoritiesShouldReturnRoleUserPasswordAndName(){
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@gmail.com");
        user.setPassword("somepassword");
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        SimpleGrantedAuthority exspectedAuthority = new SimpleGrantedAuthority("ROLE_USER");

        assert(exspectedAuthority.getAuthority().equals("ROLE_USER"));
        assert(customUserDetails.getUsername().equals("testuser"));
        assert(customUserDetails.getPassword().equals("somepassword"));
    }
}
