package org.bank.ssalguerof.msvc.clientes.models.dao;

import org.bank.ssalguerof.msvc.clientes.models.documents.Cliente;
import org.bank.ssalguerof.msvc.clientes.models.documents.ProductoCliente;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductoClienteDao  extends ReactiveMongoRepository<ProductoCliente, String> {

}
