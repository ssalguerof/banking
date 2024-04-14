package org.bank.ssalguerof.msvc.clientes.models.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosTarjetaCredito {
    public Double limiteCredito;
    public Double saldoUtilizado;
    public Date fechaCorte;
    public Date fechaVencimiento;

}
