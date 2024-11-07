package br.udp.poo2.eOrders.services;

import br.udp.poo2.eOrders.models.Produto;
import br.udp.poo2.eOrders.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService implements IBaseServiceJPA<Produto>{

    @Autowired
    private IProdutoRepository repo;

    public ProdutoService(IProdutoRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Produto> browse() {
        return this.repo.findAll();
    }

    @Override
    public Optional<Produto> read(Long id) {
        return this.repo.findById(id);
    }

    @Override
    public Produto edit(Produto entity) {
        return this.repo.save(entity);
    }

    @Override
    public Produto add(Produto entity) {
        return this.repo.save(entity);
    }

    @Override
    public Produto delete(Long id) {
        return this.repo.findById(id)
            .map(produto -> {
                this.repo.delete(produto);
                return produto;
            })
            .orElse(null);
    }
}
