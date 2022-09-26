package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.entity.Address;
import com.esmile.appEsmile.exception.ResourceNotFoundException;
import com.esmile.appEsmile.service.impl.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class AdressController {

    @Autowired
    AddressService addressService;

    @PostMapping
    public ResponseEntity save(@RequestBody Address address) {
        Address addressSave = addressService.save(address);
        return new ResponseEntity(addressSave, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getById(@RequestParam("id") Long id){
        Optional<Address> addressGet = addressService.get(id);
        if(addressGet.isEmpty()){
            return  new ResponseEntity("Nenhum endereço cadastrado", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(addressGet,HttpStatus.OK);
    }

    @GetMapping("/todos")
    public ResponseEntity getAll() {
        List<Address> addresses = addressService.getAll();
        if (addresses == null) {
            return new ResponseEntity("Nenhum Enderço Cadastrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(addresses, HttpStatus.OK);
    }

    @PatchMapping
    public void update(Address address){
        if (address != null && addressService.get(address.getId()).isPresent()) {
            addressService.update(address);
        }
    }

    @DeleteMapping
    public void delete(@RequestParam("id") Long id) throws ResourceNotFoundException {
//        if (address != null && addressService.get(address.getId()).isPresent()) {
//            addressService.delete(address);
//        }

        addressService.delete(id);
    }


}
