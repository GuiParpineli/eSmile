package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.entity.AppUser;
import com.esmile.appEsmile.exception.ResourceNotFoundException;
import com.esmile.appEsmile.login.JwtUtil;
import com.esmile.appEsmile.service.impl.AppUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class AppUserController {

    private final AppUserService service;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private static final Logger log = Logger.getLogger(AppUserController.class);

    @Autowired
    public AppUserController(AppUserService service, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AppUser appUser) {
        try {
            log.info("Efetuando login para: " + appUser.getEmail());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    appUser.getEmail(),
                    appUser.getPassword()
            ));
        } catch(Exception e) {
            throw new RuntimeException();
        }

        final UserDetails userDetails = service.loadUserByUsername(appUser.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AppUser((jwt)).getJwt());
    }

    @GetMapping
    public  ResponseEntity<?> obterTodos() throws ResourceNotFoundException {
        List<AppUser> appUsers;
        try {
            appUsers = service.getAll();
        }catch (Exception e){
            throw new ResourceNotFoundException("Nenhum usuario cadastrado");
        }
        return ResponseEntity.ok(appUsers);
    }
}
