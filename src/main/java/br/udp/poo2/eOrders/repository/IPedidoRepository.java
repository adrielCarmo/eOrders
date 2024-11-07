package br.udp.poo2.eOrders.repository;

import br.udp.poo2.eOrders.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPedidoRepository extends JpaRepository<Pedido, Long> {
}
