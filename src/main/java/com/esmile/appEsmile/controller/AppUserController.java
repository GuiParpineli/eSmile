package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.dto.AppUserDTO;
import com.esmile.appEsmile.entity.AppUser;
import com.esmile.appEsmile.exception.ResourceNotFoundException;
import com.esmile.appEsmile.login.JwtUtil;
import com.esmile.appEsmile.service.impl.AppUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
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
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AppUser appUser) throws LoginException {
        try {
            log.info("Efetuando login para: " + appUser.getEmail());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    appUser.getEmail(),
                    appUser.getPassword()
            ));
        } catch (Exception e) {
            throw new LoginException("Usuario ja cadastrado");
        }

        final UserDetails userDetails = service.loadUserByUsername(appUser.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AppUser((jwt)).getJwt());
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarUser(@RequestBody AppUser appUser) {
        log.info(String.format("Salvando usuario %s", appUser.getUsername()));
        try {
            service.save(appUser);
            return new ResponseEntity(
                    String.format("Usuario %s salvo com sucesso", appUser.getUsername()),
                    HttpStatus.OK);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Usuario ja cadastrado");
        }
    }

    @GetMapping
    public ResponseEntity<?> obterTodos() throws ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<AppUser> appUsers = service.getAll();
            List<AppUserDTO> appUserDTOS = new ArrayList<>();

            for (AppUser p : appUsers) {
                appUserDTOS.add(mapper.convertValue(p, AppUserDTO.class));
            }
            return ResponseEntity.ok(appUserDTOS);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Nenhum usuario cadastrado");
        }
    }

    @GetMapping("/buscaId")
    public ResponseEntity obterUserPorId(@RequestParam Long id) throws ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Optional<AppUser> user = service.get(id);
            return ResponseEntity.ok(mapper.convertValue(user.get(), AppUserDTO.class));
        } catch (Exception e) {
            throw new ResourceNotFoundException("Usuario nao encontrado");
        }
    }

    @GetMapping("buscaUsername")
    public ResponseEntity obterUserPorNome(@RequestParam String username) throws ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            AppUser user = service.loadUserByUsername(username);
            return ResponseEntity.ok(mapper.convertValue(user, AppUserDTO.class));
        } catch (Exception e) {
            throw new ResourceNotFoundException("Usuario não encontrado");
        }
    }

    @DeleteMapping("/excluir")
    public ResponseEntity removeUser(@RequestParam("id") Long id) throws ResourceNotFoundException {
        try {
            service.delete(id);
            return ResponseEntity.ok("Usuario Deletado");
        } catch (Exception e) {
            throw new ResourceNotFoundException("Nenhum usuario com o id informado");
        }
    }
}
