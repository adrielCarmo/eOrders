package br.udp.poo2.eOrders.services;

import java.util.List;
import java.util.Optional;

public interface IBaseServiceJPA<T>{
    List<T> browse(); // findAll JPA
    Optional<T> read(Long id); // findById JPA
    T edit(T entity); // save JPA
    T add(T entity); // save JPA
    T delete(Long id); // delete JPA
}