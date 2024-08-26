package br.com.cadastro.DAO;

import br.com.cadastro.domain.Cliente;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ClienteMapDAO implements IClienteDAO{
    private Map<Long,Cliente> map;

    public ClienteMapDAO() {
        map = new TreeMap<>();
    }

    @Override
    public Boolean cadastrar(Cliente cliente) {
        if(map.containsKey(cliente.getCpf())){
            return false;
        }
        map.put(cliente.getCpf(),cliente);

        return true;
    }

    @Override
    public void alterar(Cliente cliente) {
        Cliente clienteCadastrado = map.get(cliente.getCpf());
        if(clienteCadastrado != null){
            clienteCadastrado.setNome(cliente.getNome());
            clienteCadastrado.setTelefone(cliente.getTelefone());
            clienteCadastrado.setEndereco(cliente.getEndereco());
            clienteCadastrado.setCidade(cliente.getCidade());
            clienteCadastrado.setEstado(cliente.getEstado());
        }
        else{
            System.out.println("Não encontrado!");
        }
    }

    @Override
    public void excluir(Long cpf) {
        if(map.containsKey(cpf)){
            map.remove(cpf);
        }
    }

    @Override
    public Cliente consultar(Long cpf) {
        if(map.containsKey(cpf)){
            return map.get(cpf);
        }

        return null;
    }

    @Override
    public Collection<Cliente> listarTodos() {
        return map.values();
    }
}
