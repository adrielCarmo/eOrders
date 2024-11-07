package br.udp.poo2.eOrders.controllers;

import br.udp.poo2.eOrders.models.Fornecedor;
import br.udp.poo2.eOrders.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eorders/fornecedores")
public class FornecedorController {
    
    @Autowired
    private FornecedorService serv;

    public FornecedorController(FornecedorService serv) {
        this.serv = serv;
    }
    
    @GetMapping
    public ResponseEntity<List<Fornecedor>> getAll() {
        List<Fornecedor> fornecedores = this.serv.browse();
        return new ResponseEntity<>(fornecedores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> getById(@PathVariable Long id){
        Optional<Fornecedor> opt = this.serv.read(id);
        return opt
            .map(fornecedor -> new ResponseEntity<>(fornecedor, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Fornecedor> post(@RequestBody Fornecedor f) {
        Fornecedor fornecedor = this.serv.add(f);
        return new ResponseEntity<>(fornecedor, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Fornecedor> put(@RequestBody Fornecedor f){
        Fornecedor fornecedor = this.serv.edit(f);
        if (fornecedor != null){
            return new ResponseEntity<>(fornecedor, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Fornecedor> delete(@PathVariable Long id){
        Fornecedor fornecedor = this.serv.delete(id);
        if (fornecedor != null){
            return new ResponseEntity<>(fornecedor, HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
