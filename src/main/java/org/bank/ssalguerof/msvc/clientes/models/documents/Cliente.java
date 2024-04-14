package org.bank.ssalguerof.msvc.clientes.models.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "clientes")
public class Cliente {
    @Id
    private String id;
    private String codTipoCliente;
    private String DescTipoCliente;
    private String nombre;
    private String apePaterno;
    private String apeMaterno;
    private String codTipoDocumento;
    private String descTipoDocumento;
    private String numDocumento;
    private String email;
    private String telefono;
    private Date   fecRegistro;

}
