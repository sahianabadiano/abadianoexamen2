package ec.edu.espe.arquitectura.abadianoexamen2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.arquitectura.abadianoexamen2.dto.EmpleadoPagoDto;
import ec.edu.espe.arquitectura.abadianoexamen2.dto.PagoRolDto;
import ec.edu.espe.arquitectura.abadianoexamen2.dto.ValidacionRol;
import ec.edu.espe.arquitectura.abadianoexamen2.model.Empleado;
import ec.edu.espe.arquitectura.abadianoexamen2.model.EmpleadoPago;
import ec.edu.espe.arquitectura.abadianoexamen2.model.Empresa;
import ec.edu.espe.arquitectura.abadianoexamen2.model.PagoRol;
import ec.edu.espe.arquitectura.abadianoexamen2.repository.EmpresaRepository;
import ec.edu.espe.arquitectura.abadianoexamen2.repository.PagoRolRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PagoRolService {
    @Autowired
    private PagoRolRepository pagoRolRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional
    public void save(PagoRolDto pagoRolDto) {
        PagoRol pagoRol = new PagoRol();

        pagoRol.setMes(pagoRolDto.getMes());
        pagoRol.setFechaProceso(pagoRolDto.getFechaProceso());
        pagoRol.setCuentaPrincipal(pagoRolDto.getCuentaPrincipal());
        pagoRol.setRucEmpresa(pagoRolDto.getRucEmpresa());
        pagoRol.setValorReal(0.0);
        List<EmpleadoPago> empleadosPago = new ArrayList<>();
        EmpleadoPago empleadoPago = null;
        Double valorTotalCalculado = 0.0;
        for(EmpleadoPagoDto empleadoPagoDto : pagoRolDto.getEmpleadosPago()){
            empleadoPago = new EmpleadoPago();
            empleadoPago.setEstado("PEN");
            empleadoPago.setNumeroCuenta(empleadoPagoDto.getNumeroCuenta());
            empleadoPago.setValor(empleadoPagoDto.getValor());
            empleadosPago.add(empleadoPago);
            valorTotalCalculado += empleadoPago.getValor();
        }
        pagoRol.setValorTotal(valorTotalCalculado);
        this.pagoRolRepository.save(pagoRol);
    }

    public ValidacionRol validacionRol(Integer mes, String rucEmpresa){
        Empresa empresa = this.empresaRepository.findByRuc(rucEmpresa);
        PagoRol pagoRol = this.pagoRolRepository.findByRucEmpresaAndMes(rucEmpresa,mes);
        Boolean check = null;
        Integer totalTransacciones = 0;
        Integer errores = 0;
        Double valorReal = 0.0;
        ValidacionRol validacionRol = new ValidacionRol();

        for(EmpleadoPago empleadoPago : pagoRol.getEmpleadosPago()){
            check = Boolean.FALSE;
            for(Empleado empleado : empresa.getEmpleados()){
                if(empleadoPago.getNumeroCuenta().equals(empleado.getNumeroCuenta())){
                    empleadoPago.setEstado("VAL");
                    totalTransacciones ++;
                    valorReal = valorReal + empleadoPago.getValor();
                    check = Boolean.TRUE;
                    break;
                }
            }
            if(!check){
                empleadoPago.setEstado("BAD");
                errores++;
            }
        }

        validacionRol.setMes(mes);
        validacionRol.setRucEmpresa(rucEmpresa);
        validacionRol.setTotalTransacciones(totalTransacciones);
        validacionRol.setErrores(errores);
        validacionRol.setValorTotal(pagoRol.getValorTotal());
        validacionRol.setValorReal(valorReal);
        pagoRol.setValorReal(valorReal);
        this.pagoRolRepository.save(pagoRol);
        return validacionRol;
    }

}