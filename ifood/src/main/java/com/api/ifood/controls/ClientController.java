package com.api.ifood.controls;

import com.api.ifood.dtos.ClientRecordDto;
import com.api.ifood.models.Client;
import com.api.ifood.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ClientController {

    @Autowired
    ClienteRepository clientRep;

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients = clientRep.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(name = "id") UUID id){
        Optional<Client> cliO = clientRep.findById(id);
        if(cliO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        var cli = cliO.get();
        return  ResponseEntity.status(HttpStatus.FOUND).body(cli);
    }

    @PostMapping("/clients")
    public ResponseEntity<Object> savaClient(@RequestBody @Valid ClientRecordDto clientDto){
        var cli = new Client();
        BeanUtils.copyProperties(clientDto,cli);
        if(clientRep.existsByNome(cli.getNome())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Client is already registered");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clientRep.save(cli));
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(name = "id") UUID id){
        Optional<Client> cliO = clientRep.findById(id);
        if(cliO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        var cli = cliO.get();
        clientRep.delete(cli);
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully.");
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Object> putClient(@PathVariable(name = "id") UUID id, @RequestBody @Valid ClientRecordDto clientRecordDto){
        Optional<Client> cliO = clientRep.findById(id);
        if(cliO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        var client = cliO.get();
        BeanUtils.copyProperties(clientRecordDto,client);
        return  ResponseEntity.status(HttpStatus.OK).body(clientRep.save(client));
    }

}
