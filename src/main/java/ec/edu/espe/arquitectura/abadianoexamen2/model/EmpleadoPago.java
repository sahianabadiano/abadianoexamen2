package ec.edu.espe.arquitectura.abadianoexamen2.model;

import lombok.Data;

@Data
public class EmpleadoPago {

    private String numeroCuenta;
    private Double valor;
    private String estado;
}
