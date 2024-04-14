package org.bank.ssalguerof.msvc.clientes.models.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosPlazoFijo {
    private Double monto;
    private Date fecVencimiento;

}
