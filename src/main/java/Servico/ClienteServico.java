/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.ClienteDao;
import modelo.Cliente;


public class ClienteServico {
	ClienteDao dao;
	Scanner entrada;

	public ClienteServico() {// construtor
		this.dao = new ClienteDao();
		this.entrada = new Scanner(System.in); // cria instancia de entrada de dados
	}

	public void desenvolvimentoCliente() {
		int opcCliente = 0;
		do {
			System.out.println("===========Menu de Opções de cliente===========");
			System.out.println("1- Inserir Cliente");
			System.out.println("2- Deletar Cliente");
			System.out.println("3- Alterar Cliente");
			System.out.println("Digite Zero para voltar ao menu anterior");
			opcCliente = entrada.nextInt();
			Cliente cliente = new Cliente();
			switch (opcCliente) {
			case 0:
				break;
			case 1: // Cadastro de clientes
				cliente = new Cliente();
				System.out.print("Nome do Cliente: ");
				cliente.setNome(entrada.next());
				System.out.print("Endereço: ");
				cliente.setEndereço(entrada.next());
				System.out.print("Cidade: ");
				cliente.setCidade(entrada.next());
				// recebe informações e chama metodo para salvar dados
				this.inserir(cliente);
				System.out.println("Registro salvo com sucesso!");
				return;
			case 2:// apagar cliente
				System.out.println("Informe o codigo do cliente: ");
				int codigoDeletar = entrada.nextInt();
				// recebe codigo do cliente e chama metodo para buscar o cliente a ser apagado
				cliente = this.getCliente(codigoDeletar);
				if (cliente != null) {
					this.deletar(cliente);
				}
				System.out.println("Registro apagado com sucesso!");
				return;
			case 3:// atualizar dados
				System.out.println("Informe o codigo do cliente: ");
				int codigoAtualizar = entrada.nextInt();
				// recebe codigo do cliente e chama metodo para buscar o cliente a ser
				// atualizado
				cliente = new Cliente();
				cliente.setCodigo(codigoAtualizar);
				System.out.print("Novo nome do Cliente: ");
				cliente.setNome(entrada.next());
				System.out.print("Novo endereço do Cliente: ");
				cliente.setEndereço(entrada.next());
				System.out.print("Nova cidade do Cliente: ");
				cliente.setCidade(entrada.next());
				// recebe todos os dados do cliente e atualiza no BD
				cliente = this.atualizar(cliente);
				if (cliente != null) {// caso retorne cliente indica que ele foi atualizado
					System.out.println("Registro atualizado com sucesso!"); // mostra sucesso
				}
				break;
			default:
				System.out.println("Digite uma opção válida");
			}
		} while (opcCliente != 0);
	}

	// metodo para inserir dados, verifica regra de negocio para inserir cliente
	public Cliente inserir(Cliente cliente) {
		if (cliente == null) {
			throw new IllegalArgumentException("Cliente não pode ser nulo");
		}

		if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
			throw new IllegalArgumentException("Nome do cliente não pode ser nulo ou vazio");
		}

		if (cliente.getEndereco() == null || cliente.getEndereco().isEmpty()) {
			throw new IllegalArgumentException("Endereço do cliente não pode ser nulo ou vazio");
		}

		if (cliente.getCidade() == null || cliente.getCidade().isEmpty()) {
			throw new IllegalArgumentException("Cidade do cliente não pode ser nula ou vazia");
		}
		dao.inserir(cliente);
		return null;
	}

	// metodo para inserir dados, verifica regra de negocio para atualizar cliente
	public Cliente atualizar(Cliente cliente) {
		if (cliente == null) {
			throw new IllegalArgumentException("Cliente não pode ser nulo");
		}

		if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
			throw new IllegalArgumentException("Nome do cliente não pode ser nulo ou vazio");
		}

		if (cliente.getEndereco() == null || cliente.getEndereco().isEmpty()) {
			throw new IllegalArgumentException("Endereço do cliente não pode ser nulo ou vazio");
		}

		if (cliente.getCidade() == null || cliente.getCidade().isEmpty()) {
			throw new IllegalArgumentException("Cidade do cliente não pode ser nula ou vazia");
		}
		dao.atualizar(cliente,cliente.getCodigo());
		return null;

	}

	public Cliente getCliente(int codigo) {// busca cliente pelo codigo
		Cliente clienteExistente = dao.getByCodigo(codigo);
		return clienteExistente; // retorne o ckiente
	}

	// metodo para deletar dados, verifica regra de negocio para deletar cliente
	public boolean deletar(Cliente cliente) {
		if (cliente != null) {
			dao.deletar(cliente.getCodigo());
			return true;
		} else {
			System.out.println("Cliente não encontrado para deletar.");
		}
		return false;
	}
	
	public void deletarTodosClientes() {
		dao.deletarTodosClientes();
	}

	public void listaClientes() {// metodo responsavel por imprimir relatorio do cliente com base na entrada do usuario
		System.out.println("Deseja realmente imprimir o relatório (S\\N)");
		String resposta = entrada.next();
		if (resposta.equalsIgnoreCase("S")) {
			Cliente[] clientes = dao.getClientes();
			for (Cliente cliente : clientes) {
				System.out.println(cliente.toString());
			}
		}
	}

}