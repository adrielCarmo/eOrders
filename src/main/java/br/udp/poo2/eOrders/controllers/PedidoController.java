package br.udp.poo2.eOrders.controllers;

import br.udp.poo2.eOrders.models.Pedido;
import br.udp.poo2.eOrders.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eorders/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService serv;

    public PedidoController(PedidoService serv) {
        this.serv = serv;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getAll() {
        List<Pedido> pedidos = this.serv.browse();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable Long id){
        Optional<Pedido> opt = this.serv.read(id);
        return opt
            .map(pedido -> new ResponseEntity<>(pedido, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Pedido> post(@RequestBody Pedido p) {
        Pedido pedido = this.serv.add(p);
        return new ResponseEntity<>(pedido, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Pedido> put(@RequestBody Pedido p){
        Pedido pedido = this.serv.edit(p);
        if (pedido != null){
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pedido> delete(@PathVariable Long id){
        Pedido pedido = this.serv.delete(id);
        if (pedido != null){
            return new ResponseEntity<>(pedido, HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
