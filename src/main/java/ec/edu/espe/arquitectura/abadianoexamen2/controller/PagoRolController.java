package ec.edu.espe.arquitectura.abadianoexamen2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.edu.espe.arquitectura.abadianoexamen2.dto.PagoRolDto;
import ec.edu.espe.arquitectura.abadianoexamen2.dto.ValidacionRol;
import ec.edu.espe.arquitectura.abadianoexamen2.service.PagoRolService;

@RestController
@RequestMapping("/pagoRol")
public class PagoRolController {

    @Autowired
    private PagoRolService pagoRolService;

    @PostMapping(path = "/crear")
    public ResponseEntity<String> crear(@RequestBody PagoRolDto pagoRolDto) {
        try {
            this.pagoRolService.save(pagoRolDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(path = "/validacion")
    public ResponseEntity<ValidacionRol> obtainPostById(
            @RequestParam(name = "rucEmpresa", required = true) String rucEmpresa,
            @RequestParam(name = "mes", required = true) Integer mes
    ) {
        try {
            ValidacionRol validacionRol = this.pagoRolService.validacionRol(mes, rucEmpresa);
        
            return ResponseEntity.ok().body(validacionRol);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
