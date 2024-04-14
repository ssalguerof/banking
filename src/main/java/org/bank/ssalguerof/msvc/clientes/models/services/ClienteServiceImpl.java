package org.bank.ssalguerof.msvc.clientes.models.services;

import org.bank.ssalguerof.msvc.clientes.models.dao.ClienteDao;
import org.bank.ssalguerof.msvc.clientes.models.documents.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteServiceImpl implements ClienteService{
    @Autowired
    ClienteDao clienteDao;

    @Override
    public Flux<Cliente> findAll() {
        return clienteDao.findAll();
    }

    @Override
    public Mono<Cliente> findbyId(String id) {
        return clienteDao.findById(id);
    }

    @Override
    public Mono<Cliente> save(Cliente cliente) {
        return clienteDao.save(cliente);
    }

    @Override
    public Mono<Void> delete(Cliente cliente) {
        return clienteDao.delete(cliente);
    }

    @Override
    public Mono<Cliente> findByNumDocumento(String numDocumento) {
        return clienteDao.findByNumDocumento(numDocumento);
    }
}
