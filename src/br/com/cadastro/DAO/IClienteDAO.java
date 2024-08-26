package br.com.cadastro.DAO;

import br.com.cadastro.domain.Cliente;

import java.util.Collection;

public interface IClienteDAO {
    public Boolean cadastrar(Cliente cliente);
    public void alterar(Cliente cliente);
    public void excluir(Long cpf);
    public Cliente consultar(Long cpf);
    public Collection<Cliente> listarTodos();
}
