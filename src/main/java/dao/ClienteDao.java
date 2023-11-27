/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import modelo.Cliente;

public class ClienteDao {
	EntityManagerFactory emf;
	EntityManager em;

	public ClienteDao() {
		this.emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		this.em = emf.createEntityManager();
	}

	// metodo responsavel por inserir cliente
	public Cliente inserir(Cliente cliente) {
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
	
		return null;
	}

	// metodo responsavel por atualizar um cliente
	public Cliente atualizar(Cliente cliente, Integer id) {
		em.getTransaction().begin();
		Cliente clienteExistente = em.find(Cliente.class, id);
		if (clienteExistente != null) {
			// Atualiza os atributos do cliente existente com os novos valores
			clienteExistente.setNome(cliente.getNome());
			clienteExistente.setEndereço(cliente.getEndereco());
			clienteExistente.setCidade(cliente.getCidade());
			// Persiste as mudanças no banco de dados
			clienteExistente = em.merge(clienteExistente);
			em.getTransaction().commit();
		
			return clienteExistente; // Retorna o cliente atualizado
		} else {
			System.out.println("Cliente não encontrado.");
			return null;
		}
	}

	// metodo responsavel por deletar um cliente pelo id
	public void deletar(Integer id) {
		Cliente clientedeleta = em.find(Cliente.class, id);
		if (clientedeleta != null) {
			em.getTransaction().begin();
			em.remove(clientedeleta);
			em.getTransaction().commit();
			
		} else {
			System.out.println("Cliente não encontrado para deletar.");
		}
	}

	public void deletarTodosClientes() {
		 em.getTransaction().begin();
	     em.createNativeQuery("DELETE FROM cliente").executeUpdate();
	     em.getTransaction().commit();
	     
	}

	// metodo responsavel por buscar um cliente pelo id
	public Cliente getByCodigo(Integer codigo) {
		Cliente clienteExistente = em.find(Cliente.class, codigo);
		return clienteExistente; // retorne o ckiente
	}

	// retorna TODOS os clientes salvos
	public Cliente[] getClientes() {
		em.getTransaction().begin();
		List<Cliente> clientesList = em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
		Cliente[] clientesArray = clientesList.toArray(new Cliente[clientesList.size()]);
		
		return clientesArray;
	}
}
