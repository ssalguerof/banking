package org.bank.ssalguerof.msvc.clientes.models.dao;

import org.bank.ssalguerof.msvc.clientes.models.documents.Cliente;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ClienteDao extends ReactiveMongoRepository<Cliente, String> {
    Mono<Cliente> findByNumDocumento(String numDocumento);
}
