package br.udp.poo2.eOrders.repository;

import br.udp.poo2.eOrders.models.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
