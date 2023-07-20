package ec.edu.espe.arquitectura.abadianoexamen2.dto;

import lombok.Data;

@Data
public class ValidacionRol {
    private Integer mes;
    private String rucEmpresa;
    private Double valorTotal;
    private Double ValorReal;
    private Integer totalTransacciones;
    private Integer errores;
}
