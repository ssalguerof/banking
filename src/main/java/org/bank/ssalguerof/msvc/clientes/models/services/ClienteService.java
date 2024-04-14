package org.bank.ssalguerof.msvc.clientes.models.services;

import org.bank.ssalguerof.msvc.clientes.models.documents.Cliente;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClienteService {
    public Flux<Cliente> findAll();
    public Mono<Cliente> findbyId(String id);
    public Mono<Cliente> save(Cliente cliente);
    public Mono<Void> delete(Cliente cliente);
    public Mono<Cliente> findByNumDocumento(String numDocumento);

}
