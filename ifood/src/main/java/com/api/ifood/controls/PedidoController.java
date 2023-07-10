package com.api.ifood.controls;

import com.api.ifood.dtos.PedidoRecordDto;
import com.api.ifood.models.Client;
import com.api.ifood.models.Establishment;
import com.api.ifood.models.Pedido;
import com.api.ifood.repository.ClienteRepository;
import com.api.ifood.repository.EstabelecimentoRepository;
import com.api.ifood.repository.PedidoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRep;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> getAllPedidos(){
        List<Pedido> pedidos = pedidoRep.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }

    @PostMapping("/pedidos")
    public ResponseEntity<Object> savePedido(@RequestBody @Valid PedidoRecordDto pedidoRecordDto){
        var pedido = new Pedido();
        Optional<Establishment> establishmentO = estabelecimentoRepository.findById(pedidoRecordDto.establishment_id());
        Optional<Client> clientO = clienteRepository.findById(pedidoRecordDto.client_id());
        if(clientO.isEmpty() || establishmentO.isEmpty()){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid elements.");
        }
        var establishment = establishmentO.get();
        var client = clientO.get();
        pedido.setClient(client);
        pedido.setEstablishment(establishment);
        pedido.setPreco(pedidoRecordDto.preco());
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoRep.save(pedido));
    }
}
