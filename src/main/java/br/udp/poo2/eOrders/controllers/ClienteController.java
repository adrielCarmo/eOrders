package br.udp.poo2.eOrders.controllers;

import br.udp.poo2.eOrders.models.Cliente;
import br.udp.poo2.eOrders.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eorders/clientes")
public class ClienteController {

    @Autowired
    private ClienteService serv;

    public ClienteController(ClienteService serv) {
        this.serv = serv;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        List<Cliente> clientes = this.serv.browse();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id){
        Optional<Cliente> opt = this.serv.read(id);
        return opt
            .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Cliente> post(@RequestBody Cliente c) {
        Cliente cliente = this.serv.add(c);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Cliente> put(@RequestBody Cliente c){
        Cliente cliente = this.serv.edit(c);
        if (cliente != null){
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable Long id){
        Cliente cliente = this.serv.delete(id);
        if (cliente != null){
            return new ResponseEntity<>(cliente, HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
