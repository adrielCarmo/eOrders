package br.udp.poo2.eOrders.controllers;

import br.udp.poo2.eOrders.models.Produto;
import br.udp.poo2.eOrders.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eorders/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService serv;

    public ProdutoController(ProdutoService serv) {
        this.serv = serv;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        List<Produto> produtos = this.serv.browse();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id){
        Optional<Produto> opt = this.serv.read(id);
        return opt
            .map(produto -> new ResponseEntity<>(produto, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Produto> post(@RequestBody Produto p) {
        Produto produto = this.serv.add(p);
        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Produto> put(@RequestBody Produto p){
        Produto produto = this.serv.edit(p);
        if (produto != null){
            return new ResponseEntity<>(produto, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> delete(@PathVariable Long id){
        Produto produto = this.serv.delete(id);
        if (produto != null){
            return new ResponseEntity<>(produto, HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
