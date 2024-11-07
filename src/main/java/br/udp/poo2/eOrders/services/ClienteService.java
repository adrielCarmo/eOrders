package br.udp.poo2.eOrders.services;

import br.udp.poo2.eOrders.models.Cliente;
import br.udp.poo2.eOrders.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IBaseServiceJPA<Cliente>{

    @Autowired
    private IClienteRepository repo;

    public ClienteService(IClienteRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Cliente> browse() {
        return this.repo.findAll();
    }

    @Override
    public Optional<Cliente> read(Long id) {
        return this.repo.findById(id);
    }

    @Override
    public Cliente edit(Cliente entity) {
        /*
        (LEGADO) Implementação antiga para verificar se um cliente existe, e se existir,
        copiar os valores manuais de entity para cliente.

        Optional<Cliente> clienteOld = this.repo.findById(entity.getId());
        if(clienteOld.isPresent()) {
            Cliente cliente = clienteOld.get();
            cliente.setNome(entity.getNome());
            cliente.setCpf(entity.getCpf());
            cliente.setEmail(entity.getEmail());
            cliente.setTelefone(entity.getTelefone());
            this.repo.save(cliente);
        }
        */

        // Simplesmente salva e retorna a entidade, o JPA é f#@&# e cuida da atualização!
        // O Spring Data JPA automaticamente verifica se o objeto tem um ID válido...
        // Se tiver, ele tenta atualizar o registro correspondente no banco de dados.
        // Se não tiver (ou o ID não existe), ele cria um novo registro.
        return this.repo.save(entity);
    }

    @Override
    public Cliente add(Cliente entity) {
        return this.repo.save(entity);
    }

    @Override
    public Cliente delete(Long id) {
        return this.repo.findById(id) // procura no banco com esse id
            .map(cliente -> { // como retorna um Optional, se achar, executa uma funcao (lambda)
                this.repo.delete(cliente); // essa funcao recebe o cliente e depois deleta ele
                return cliente; // por fim, retorna o próprio cliente como resultado
            })
            .orElse(null); // o correto aqui seria lançar uma nova exceção
    }
}
