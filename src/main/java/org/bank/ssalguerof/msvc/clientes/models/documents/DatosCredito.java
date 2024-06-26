package org.bank.ssalguerof.msvc.clientes.models.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosCredito {
    private Double montoAprobado;
    private Double saldoPendiente;
    private Integer cuotasPagadas;
    private Integer cuotasTotales;
}
