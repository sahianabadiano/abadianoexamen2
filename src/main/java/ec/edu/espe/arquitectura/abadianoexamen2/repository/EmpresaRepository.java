package ec.edu.espe.arquitectura.abadianoexamen2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ec.edu.espe.arquitectura.abadianoexamen2.model.Empresa;


public interface EmpresaRepository extends MongoRepository<Empresa, String> {
   Empresa findByRuc(String ruc);
}