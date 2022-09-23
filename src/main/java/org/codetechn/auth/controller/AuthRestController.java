package org.codetechn.auth.controller;


import org.codetechn.auth.entity.User;
import org.codetechn.auth.jwt.JwtUtil;
import org.codetechn.auth.payload.request.AuthRequest;
import org.codetechn.auth.payload.request.SignUpRequest;
import org.codetechn.auth.payload.response.AuthResponse;
import org.codetechn.auth.payload.response.MessageResponse;
import org.codetechn.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthRestController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login (@Valid @RequestBody AuthRequest request){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );
            User user =(User) authentication.getPrincipal();
            String accesToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(),user.getNumber(),user.getRoles().toString(),accesToken);
            return ResponseEntity.ok().body(response);


        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.toString());
        }
    }

    @PostMapping("sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
        if(userRepository.existsByEmail(signUpRequest.getEmail())){

            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));


        }

        User user = new User(signUpRequest.getEmail(),signUpRequest.getNumber(), passwordEncoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);
        String token= jwtUtil.generateAccessToken(user);
        AuthResponse response = new AuthResponse(user.getEmail(),user.getNumber(),user.getRoles().toString(),token);
        return ResponseEntity.ok().body(response);

        // return ResponseEntity.ok().body("user is new");

    }
}
