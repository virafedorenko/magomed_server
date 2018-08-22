package app.controller;

import app.auth.jwt.JwtTokenProvider;
import app.controller.request.LoginRequest;
import app.controller.request.RegistrationRequest;
import app.entity.User;
import app.exception.BadRequestException;
import app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class AuthController {
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        LOG.info("In register method.....................");
        if (userService.findUserByEmail(registrationRequest.getEmail()) != null) {
            throw new BadRequestException("Username is already taken!");
        }
        User user = new User(registrationRequest.getEmail(), registrationRequest.getPassword(),
                registrationRequest.getName());
        User registered = userService.register(user);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(registered.getEmail());
        loginRequest.setPassword(registrationRequest.getPassword());
        return login(loginRequest);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        LOG.info("In logout method.....................");
    }

}
