package org.bank.ssalguerof.msvc.clientes;

import org.bank.ssalguerof.msvc.clientes.models.dao.ClienteDao;
import org.bank.ssalguerof.msvc.clientes.models.dao.ProductoClienteDao;
import org.bank.ssalguerof.msvc.clientes.models.dao.ProductoDao;
import org.bank.ssalguerof.msvc.clientes.models.documents.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class MsvcClientesApplication  implements CommandLineRunner {

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	@Autowired
	private ProductoDao productoDao;
	@Autowired
	private ClienteDao clienteDao;
	@Autowired
	private ProductoClienteDao productoClienteDao;
	private static final Logger log = LoggerFactory.getLogger(MsvcClientesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MsvcClientesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mongoTemplate.dropCollection("clientes").subscribe();
		mongoTemplate.dropCollection("productos").subscribe();
		mongoTemplate.dropCollection("productoscliente").subscribe();
		/*
		Se realizará la carga de data de prueba en la BD mongo
		* */
		//Carga Data producto
		Flux<Producto> productoFlux = Flux.just(new Producto(null, "CTAAHO","Cuenta de Ahorro", "Una cuenta de ahorro es un tipo de cuenta bancaria diseñada para que los individuos puedan depositar y guardar dinero con el objetivo de ahorrar y generar intereses",
								"A","Activo","0",0.0,"1",5,null),
						new Producto(null, "CTACOR","Cuenta Corriente", "Una cuenta corriente es un tipo de cuenta bancaria diseñada para facilitar transacciones financieras regulares",
								"A","Activo","1",12.5,"0",null,null),
						new Producto(null, "CTAPLZ","Cuenta Plazo Fijo", "Una cuenta a plazo fijo es un producto financiero ofrecido por las instituciones bancarias en el cual un cliente deposita una cantidad específica de dinero por un período de tiempo determinado",
								"A","Activo","0",0.0,"0",null,null),
						new Producto(null, "CREPER","Crédito Personal", "Un crédito personal es un tipo de préstamo otorgado por una institución financiera, como un banco o una cooperativa de crédito, a un individuo para cubrir sus necesidades financieras personales.",
								"P","Pasivo","0",0.0,"0",null,null),
						new Producto(null, "CREEMP","Crédito Empresarial", "Un crédito empresarial es un tipo de financiamiento otorgado por una institución financiera a una empresa o negocio con el fin de cubrir sus necesidades financieras relacionadas con operaciones comerciales, expansión, inversión en activos, capital de trabajo u otros fines empresariales.",
								"P","Pasivo","0",0.0,"0",null,null),
						new Producto(null, "CRETAR","Tarjeta de Crédito", "Una tarjeta de crédito es un instrumento financiero que permite a su titular realizar compras o transacciones sin necesidad de disponer de efectivo en ese momento.",
								"P","Pasivo","0",0.0,"0",null,null)
						)
				.flatMap(producto -> {
					producto.setFecRegistro(new Date());
					return productoDao.save(producto);
				});
		productoFlux.subscribe(producto-> log.info("Insert: "+ producto.toString()));

		//Carga de Clientes
		Flux<Cliente> clientesGuardados = Flux.just(new Cliente(null, "1", "Personal", "Juan", "Pérez", "García", "1", "DNI", "12345678", "juan@example.com", "999888777", null),
						new Cliente(null, "2", "Empresarial", "Empresa ABC", null, null, "2", "RUC", "876543211235", "empresa@example.com", "987654321", null),
						new Cliente(null, "1", "Personal", "Carlos", "Pérez", "García", "1", "DNI", "12225678", "carlos@example.com", "933888777", null)
				)
				.flatMap(cliente -> {
					cliente.setFecRegistro(new Date());
					return clienteDao.save(cliente);
				})
				.doOnNext(cliente -> log.info("Insert: " + cliente.toString()));

		Cliente Titular = clientesGuardados.filter(producto->producto.getNumDocumento().equals("12225678")).blockFirst();

				//new Cliente(null, "1", "Personal", "Carlos", "Pérez", "García", "1", "DNI", "12225678", "carlos@example.com", "933888777", null);

		clientesGuardados.flatMap(cliente -> {
					ProductoCliente productoCliente = null;
					if(cliente.getNumDocumento().equals("12345678")){
						productoCliente = new ProductoCliente(null,"1234567890", "9876543210987654",cliente.getId(), cliente.getCodTipoCliente(), "CTAAHO", "Cuenta de Ahorro","A", "Activo", new Date(),new DatosCuenta(5000.0),null,null,null,null,null, Arrays.asList(new Cliente(cliente.getId(), null,null,cliente.getNombre(),cliente.getApePaterno(),cliente.getApeMaterno(),cliente.getCodTipoDocumento(), cliente.getDescTipoDocumento(), cliente.getNumDocumento(), cliente.getEmail(), null, null)));
						return productoClienteDao.save(productoCliente);
					}
					if(cliente.getNumDocumento().equals("876543211235")){
						productoCliente = new ProductoCliente(null,"1234567890", "8765432109876543",cliente.getId(), cliente.getCodTipoCliente(), "CREEMP", "Crédito Empresarial","P", "Pasivo", new Date(),null,null,null,new DatosCredito(10000.0,8000.0, 2,12),null,null, Arrays.asList(new Cliente(Titular.getId(), null,null,Titular.getNombre(),Titular.getApePaterno(),Titular.getApeMaterno(),Titular.getCodTipoDocumento(), Titular.getDescTipoDocumento(), Titular.getNumDocumento(), Titular.getEmail(), null, null)));
						return productoClienteDao.save(productoCliente);
					}
					return Mono.empty();

				})
				.subscribe(cliente -> log.info("Insert: " + cliente.toString()));



	}
}
