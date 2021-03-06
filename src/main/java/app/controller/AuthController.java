package app.controller;

import app.auth.jwt.JwtAuthenticationResponse;
import app.auth.jwt.JwtTokenProvider;
import app.controller.request.LoginRequest;
import app.controller.request.RegistrationRequest;
import app.entity.User;
import app.exception.BadRequestException;
import app.exception.ResourceNotFoundException;
import app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    public JwtAuthenticationResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return new JwtAuthenticationResponse(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtAuthenticationResponse register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        LOG.info("In register method.....................");
        if (userService.findUserByEmail(registrationRequest.getEmail()) != null) {
            throw new BadRequestException("Such username is already taken!");
        }
        User user = new User(registrationRequest.getEmail(), registrationRequest.getPassword(),
                registrationRequest.getName(),registrationRequest.getSurname());
        User registered = userService.register(user);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(registered.getEmail());
        loginRequest.setPassword(registrationRequest.getPassword());
        return login(loginRequest);
    }

    @GetMapping("/currentUser")
    @ResponseStatus(HttpStatus.OK)
    public User getCurrentUser(HttpServletRequest request) {
        String jwt = jwtTokenProvider.getJwtFromRequest(request);
        String userId = jwtTokenProvider.getUserIdFromJwt(jwt);
        User user = userService.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User", "id", userId);
        }
        return user;
    }

    @GetMapping("/userById")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@RequestParam("id") String id) {
        User user = userService.findUserById(id);
        if (user == null) {
            throw new ResourceNotFoundException("User", "id", id);
        }
        return user;
    }

    @GetMapping("/userByEmail")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByEmail(@RequestParam("email") String email) {
        User user = userService.findUserByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User", "email", email);
        }
        return user;
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        LOG.info("In logout method.....................");
    }

}
