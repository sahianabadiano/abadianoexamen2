package ec.edu.espe.arquitectura.abadianoexamen2.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "pago_rol")
public class PagoRol {

    @Id
    private String id;
    private Integer mes;
    private Date fechaProceso;
    private String rucEmpresa;
    private String cuentaPrincipal;
    private Double valorTotal;
    private Double ValorReal;
    private List<EmpleadoPago> empleadosPago;
    
}
