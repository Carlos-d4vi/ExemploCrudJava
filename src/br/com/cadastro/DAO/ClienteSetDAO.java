package br.com.cadastro.DAO;

import br.com.cadastro.domain.Cliente;

import java.util.*;

public class ClienteSetDAO implements IClienteDAO {

    private Set<Cliente> set;

    public ClienteSetDAO() {
        set = new HashSet<>();
    }

    @Override
    public Boolean cadastrar(Cliente cliente) {
        if (set.contains(cliente)) {
        return false;
        }
        set.add(cliente);
        return true;
    }

    @Override
    public void alterar(Cliente cliente) {
        if (set.contains(cliente)) {
            Cliente clienteCadastrado = cliente;
            clienteCadastrado.setNome(cliente.getNome());
            clienteCadastrado.setTelefone(cliente.getTelefone());
            clienteCadastrado.setEndereco(cliente.getEndereco());
            clienteCadastrado.setCidade(cliente.getCidade());
            clienteCadastrado.setEstado(cliente.getEstado());
            set.remove(cliente);
        }
    }

    @Override
    public void excluir(Long cpf) {
        if(set.contains(cpf)) {
            set.remove(cpf);
        }
    }

    @Override
    public Cliente consultar(Long cpf) {
        if (set.contains(cpf)) {
            for (Cliente cliente : set) {
                if (cliente.getCpf().equals(cpf)) {
                    return cliente;
                }
            }
        }
        return null;
    }

    @Override
    public Collection<Cliente> listarTodos() {
        Collection<Cliente> collection = new ArrayList<>();
        for (Cliente cliente : set) {
            collection.add(cliente);
        }
        return collection;
    }
}
