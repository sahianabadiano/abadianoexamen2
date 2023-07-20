package ec.edu.espe.arquitectura.abadianoexamen2.dto;

import java.util.List;

import ec.edu.espe.arquitectura.abadianoexamen2.model.Empleado;
import lombok.Data;

@Data
public class EmpresaDto {
    private String ruc;
    private String razonSocial;
    private String cuentaPrincipal;
    private List<Empleado> empleados; 
}
