package br.udp.poo2.eOrders.repository;

import br.udp.poo2.eOrders.models.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
