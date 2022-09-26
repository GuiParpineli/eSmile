package com.esmile.appEsmile.service.impl;

import com.esmile.appEsmile.entity.AppUser;
import com.esmile.appEsmile.exception.ResourceNotFoundException;
import com.esmile.appEsmile.repository.IAppUserRepository;
import com.esmile.appEsmile.service.IService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService implements IService<AppUser>, UserDetailsService {
    private final IAppUserRepository repository;

    @Autowired
    public AppUserService(IAppUserRepository repository) {this.repository = repository;}

    final static Logger log = Logger.getLogger(AppUserService.class);

    public List<AppUser> getAll() {
        return repository.findAll();
    }

    public Optional<AppUser> get(Long id) {
        return repository.findById(id);
    }

    public AppUser save(AppUser appUser) {
        log.info("Usuario salvo com sucesso");
        return repository.save(appUser);
    }

    public void delete(Long id) throws ResourceNotFoundException {
        if (repository.findById(id).isEmpty())
            throw new ResourceNotFoundException("");
        log.info("Usuario deletado com sucesso");
    }

    public void update(AppUser appUser) {
        if (appUser != null & repository.findById(appUser.getId()).isPresent()) {
            repository.saveAndFlush(appUser);
            log.info("Usuario atualizado com sucesso");
        }
    }

    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("procurando user: " + username);
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not Found"));
        //return new User("foo","foo", new ArrayList<>());
    }
}
