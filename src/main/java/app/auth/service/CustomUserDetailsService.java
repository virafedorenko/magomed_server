package app.auth.service;

import app.auth.UserPrincipal;
import app.entity.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(s);
        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(String id) {
        User user = userService.findUserById(id);
        return UserPrincipal.create(user);
    }
}
