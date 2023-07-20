package ec.edu.espe.arquitectura.abadianoexamen2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.edu.espe.arquitectura.abadianoexamen2.dto.EmpresaDto;
import ec.edu.espe.arquitectura.abadianoexamen2.service.EmpresaService;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody EmpresaDto empresaDto) {
        try {
            this.empresaService.save(empresaDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
