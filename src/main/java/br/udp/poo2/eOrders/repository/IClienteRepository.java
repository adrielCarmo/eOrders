package br.udp.poo2.eOrders.repository;

import br.udp.poo2.eOrders.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
}
