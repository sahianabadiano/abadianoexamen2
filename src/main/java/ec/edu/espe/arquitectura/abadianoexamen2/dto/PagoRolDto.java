package ec.edu.espe.arquitectura.abadianoexamen2.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PagoRolDto {
    private Integer mes;
    private Date fechaProceso;
    private String rucEmpresa;
    private String cuentaPrincipal;

    private List<EmpleadoPagoDto> empleadosPago;
}

