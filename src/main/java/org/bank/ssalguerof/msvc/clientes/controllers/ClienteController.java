package org.bank.ssalguerof.msvc.clientes.controllers;

import org.bank.ssalguerof.msvc.clientes.models.documents.Cliente;
import org.bank.ssalguerof.msvc.clientes.models.services.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping
    public Flux<Cliente> findAll(){
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Cliente> findById(@PathVariable String id){return clienteService.findbyId(id);}

    @PostMapping
    public Mono<Cliente> saveCustomer(@RequestBody Cliente customer){
        return  clienteService.save(customer);
    }
    @PutMapping
    public Mono<Cliente> updateCustomer(@RequestBody Cliente customer){
        return clienteService.findbyId(customer.getId())
                .flatMap(existingCustomer->{
                    BeanUtils.copyProperties(customer, existingCustomer, "id");
                    return  clienteService.save(existingCustomer);
                });
    }

    @PutMapping("/{numDocumento}")
    public Mono<Cliente> updateByNumDocumento(@PathVariable String numDocumento, @RequestBody Cliente customer) {
        return clienteService.findByNumDocumento(numDocumento).flatMap(existingCustomer->{
            BeanUtils.copyProperties(customer, existingCustomer, "id");
            return  clienteService.save(existingCustomer);
        });
    }
}
