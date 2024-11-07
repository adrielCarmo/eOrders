package br.udp.poo2.eOrders.repository;

import br.udp.poo2.eOrders.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProdutoRepository extends JpaRepository<Produto, Long> {
}
