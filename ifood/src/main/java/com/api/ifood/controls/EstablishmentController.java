package com.api.ifood.controls;

import com.api.ifood.dtos.EstabelecimentoDto;
import com.api.ifood.models.Establishment;
import com.api.ifood.repository.EstabelecimentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class EstablishmentController {

    @Autowired
    EstabelecimentoRepository estaRep;

    @GetMapping("/establishments")
    public ResponseEntity<List<Establishment>> getAllEstablishments(){
        List<Establishment> estabelecimentoList = estaRep.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(estabelecimentoList);
    }

    @GetMapping("/establishments/{id}")
    public ResponseEntity<Object> getOneEstablishments(@PathVariable (value = "id") UUID id){
        Optional<Establishment> estO = estaRep.findById(id);
        if(estO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Establishments not found!");
        }
        Establishment establishment = estO.get();
        return  ResponseEntity.status(HttpStatus.OK).body(establishment);
    }

    @PostMapping("/establishments")
    public ResponseEntity<Object> saveEstablishment(@RequestBody @Valid EstabelecimentoDto establishmentRecordDto) {
        var establishment = new Establishment();
        BeanUtils.copyProperties(establishmentRecordDto, establishment);
        if (estaRep.existsByNome(establishment.getNome())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Establishment is already registered");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(estaRep.save(establishment));
    }

    @DeleteMapping("/establishments/{id}")
    public ResponseEntity<Object> deleteEstablishment(@PathVariable(value = "id") UUID id){
        Optional<Establishment> estO = estaRep.findById(id);
        if (estO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Establishment not found!");
        }
        estaRep.delete(estO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Establishment deleted successfully.");
    }

    @PutMapping("/establishments/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid EstabelecimentoDto estabelecimentoDto) {
        Optional<Establishment> estO = estaRep.findById(id);
        if(estO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Establishment not found.");
        }
        var establishment = estO.get();
        BeanUtils.copyProperties(estabelecimentoDto, establishment);
        return ResponseEntity.status(HttpStatus.OK).body(estaRep.save(establishment));
    }
}
