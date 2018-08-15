package app.controller;

import app.auth.jwt.JwtAuthenticationResponse;
import app.auth.jwt.JwtTokenProvider;
import app.controller.request.LoginRequest;
import app.controller.request.RegistrationRequest;
import app.controller.response.ApiResponse;
import app.entity.User;
import app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        LOG.info("In register method.....................");
        if (userService.findUserByEmail(registrationRequest.getEmail()) != null) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        User user = new User(registrationRequest.getEmail(), registrationRequest.getPassword(),
                registrationRequest.getName());
        User registered = userService.register(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(registered.getEmail()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully"));
    }

}
