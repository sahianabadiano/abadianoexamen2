package ec.edu.espe.arquitectura.abadianoexamen2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.arquitectura.abadianoexamen2.dto.EmpresaDto;
import ec.edu.espe.arquitectura.abadianoexamen2.model.Empresa;
import ec.edu.espe.arquitectura.abadianoexamen2.repository.EmpresaRepository;


@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional
    public void save(EmpresaDto empresaDto) {
        Empresa empresa = new Empresa();

        empresa.setCuentaPrincipal(empresaDto.getCuentaPrincipal());
        empresa.setRuc(empresaDto.getRuc());
        empresa.setRazonSocial(empresaDto.getRazonSocial());
        empresa.setEmpleados(empresaDto.getEmpleados());

        this.empresaRepository.save(empresa);
    }

}