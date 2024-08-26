package br.com.cadastro;

import br.com.cadastro.DAO.ClienteMapDAO;
import br.com.cadastro.domain.Cliente;

import javax.swing.*;
import java.util.Collection;

public class App {
    public static void main(String[] args) {
        ClienteMapDAO dao = new ClienteMapDAO();

        String options;
        do {
            options = JOptionPane.showInputDialog(null,
                    "1 - Cadastrar cliente \n2- Consultar cliente \n3- Atualizar cliente \n4 - Cancelar cliente \n5 - Consultar todos.\n6 - fechar",
                    "Cadastrar cliente", JOptionPane.PLAIN_MESSAGE);

            if (options == null) {
                // Usuário clicou em Cancelar ou fechou a janela, então saia do loop
                break;
            }

            switch (options) {
                case "1":
                    String novoCliente = JOptionPane.showInputDialog(null, "Adicione: Nome, cpf, telefone, endereço, número, cidade e estado.");
                    novoCliente = novoCliente.trim();
                    String[] informacaoClienteSeparada = novoCliente.split(",");

                    if (informacaoClienteSeparada.length < 7) {
                        JOptionPane.showMessageDialog(null, "Dados insuficientes. Por favor, forneça todas as informações solicitadas.", "Erro", JOptionPane.ERROR_MESSAGE);
                        continue; // Volta para o início do loop
                    }

                    String nome = informacaoClienteSeparada[0];
                    String cpf = informacaoClienteSeparada[1];
                    String telefone = informacaoClienteSeparada[2];
                    String endereco = informacaoClienteSeparada[3];
                    String numero = informacaoClienteSeparada[4];
                    String cidade = informacaoClienteSeparada[5];
                    String estado = informacaoClienteSeparada[6];

                    if(cpf.length() < 11) {
                        JOptionPane.showMessageDialog(null,"Cpf inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                    Cliente cliente = new Cliente(nome, cpf, telefone, endereco, numero, cidade, estado);
                    boolean cadastradoComSucesso = dao.cadastrar(cliente);

                    if (cadastradoComSucesso) {
                        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar o cliente. Verifique os dados fornecidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                // Adicione os outros casos do switch para as outras opções do menu
                case "2":
                    String resposta = JOptionPane.showInputDialog(null, "Digite o cpf do cliente:", JOptionPane.PLAIN_MESSAGE);

                    if (resposta == null || resposta.length() < 11) {
                        JOptionPane.showMessageDialog(null, "parece que algo está errado, confirme que você digitou todos os 11 dígitos.","Erro", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    else{
                        Cliente clienteConsulta = dao.consultar(Long.parseLong(resposta.trim()));
                        JOptionPane.showMessageDialog(null,clienteConsulta);
                        break;
                    }
                case "3":
                    String consultarCpfExistente = JOptionPane.showInputDialog(null, "Digite o cpf do cliente:", JOptionPane.PLAIN_MESSAGE);

                    String[] infos = JOptionPane.showInputDialog(null, "Adicione: Nome, telefone, endereço, número, cidade e estado.").split(",");

                    if (infos.length < 6) {
                        JOptionPane.showMessageDialog(null, "Erro, informação não preenchida!", "Erro", JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                    Cliente clienteComInfosAlteradas = new Cliente(infos[0],consultarCpfExistente, infos[1], infos[2], infos[3], infos[4], infos[5]);

                    dao.alterar(clienteComInfosAlteradas);

                    Cliente clienteAtualizado = dao.consultar(Long.parseLong(consultarCpfExistente));
                   JOptionPane.showMessageDialog(null, clienteAtualizado);

                case "4":
                    String excluirCpf = JOptionPane.showInputDialog(null,"Cpf do cliente:");
                    if (dao.consultar(Long.parseLong(excluirCpf)) == null){
                        JOptionPane.showMessageDialog(null, "não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    else{
                        dao.excluir(Long.parseLong(excluirCpf));
                    }

                case "5":
                    Collection clientes = dao.listarTodos();
                    JOptionPane.showMessageDialog(null,clientes);
                    break;
            }
        } while (options != null && !options.equals("6"));
    }
}
