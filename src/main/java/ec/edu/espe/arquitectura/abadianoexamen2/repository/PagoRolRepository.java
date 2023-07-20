package ec.edu.espe.arquitectura.abadianoexamen2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ec.edu.espe.arquitectura.abadianoexamen2.model.PagoRol;


public interface PagoRolRepository extends MongoRepository<PagoRol, String> {
   PagoRol findByRucEmpresaAndMes(String rucEmpresa, Integer mes);
}