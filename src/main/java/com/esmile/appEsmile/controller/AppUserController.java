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
import java.util.Optional;


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
        log.info(appUser);
        try {
            log.info("Efetuando login para: " + appUser.getEmail());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    appUser.getEmail(),
                    appUser.getPassword()
            ));
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        final UserDetails userDetails = service.loadUserByUsername(appUser.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AppUser((jwt)).getJwt());
    }

    @PostMapping("/cadastro")
    public ResponseEntity cadastrarUser(@RequestBody AppUser appUser) {
        AppUser systemUserSave = service.save(appUser);
        if (systemUserSave == null) {
            return new ResponseEntity("Usuario ja cadastrado", HttpStatus.BAD_REQUEST);
        }
        log.info("Usuario" + appUser.getUsername() + " cadastrado com sucesso");
        return new ResponseEntity(systemUserSave, HttpStatus.OK);
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

    @GetMapping("/busca")
    public ResponseEntity obterUserPorId(@RequestParam Long id) throws ResourceNotFoundException {
        Optional<AppUser> user = service.get(id);
        try{
            user.isEmpty();
        }catch (Exception e){
            throw  new ResourceNotFoundException("Usuario nao encontrado");
        }

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/exclusao")
    public ResponseEntity removeUser(@RequestParam("id") Long id) throws ResourceNotFoundException{
        try {
            service.delete(id);
            return ResponseEntity.ok("Usuario Deletado");
        }catch(Exception e) {
            throw new ResourceNotFoundException("Nenhum usuario com o id informado");
        }
    }
}
