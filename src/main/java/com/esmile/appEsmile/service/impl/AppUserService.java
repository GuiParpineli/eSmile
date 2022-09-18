package com.esmile.appEsmile.service.impl;

import com.esmile.appEsmile.entity.AppUser;
import com.esmile.appEsmile.repository.IAppUserRepository;
import com.esmile.appEsmile.service.IService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public class AppUserService implements IService<AppUser> {
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

    public void delete(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            log.info("Usuario deletado com sucesso");
        }
    }

    public void update(AppUser appUser) {
        if (appUser != null & repository.findById(appUser.getId()).isPresent()) {
            repository.saveAndFlush(appUser);
            log.info("Usuario atualizado com sucesso");
        }
    }

    public AppUser loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("procurando user: " + email);
        return repository.findByEmail(email);
        // return new User("foo","foo", new ArrayList<>());
    }
}
