package org.bank.ssalguerof.msvc.clientes.models.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "productoscliente")
public class ProductoCliente {
    @Id
    private String id;
    private String numCuenta;
    private String numTarjeta;
    private String clienteId;
    private String codTipoCliente;
    private String codProducto;
    private String nomProducto;
    private String codTipoProducto;
    private String descTipoProducto;
    private Date fecRegistroProducto;
    //Dato para Cuenta de Ahorro o Cuenta Corriente
    private DatosCuenta datosCuentaAhorro;
    private DatosCuenta datosCuentaCorriente;
    private DatosPlazoFijo datosPlazoFijo;
    private DatosCredito datosCreditoEmpresarial;
    private DatosCredito datosCreditoPersonal;
    private DatosTarjetaCredito datosTarjetaCredito;
    private List<Cliente> listaTitulares;

}
