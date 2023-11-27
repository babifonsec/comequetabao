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
import modelo.Produto;

public class ProdutoDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
	EntityManager em = emf.createEntityManager();

	public ProdutoDao() {
		// this.produtos = produtos; //recebe a lista de produtos no construtor da
		// classe
	}

	// metodo responsavel por inserir produto
	public Produto inserir(Produto produto) {
		em.getTransaction().begin();
		em.persist(produto);
		em.getTransaction().commit();
		em.close();
		return null;
	}

	// metodo responsavel por atualizar produto
	public Produto atualizar(Produto produto, int indice) {
		em.getTransaction().begin();
		Produto produtoExistente = em.find(Produto.class, produto.getCodigo());
		if (produtoExistente != null) {
			// Atualiza os atributos do cliente existente com os novos valores
			produtoExistente.setValor(produto.getValor());
			produtoExistente.setDescricao(produto.getDescricao());

			// Persiste as mudanças no banco de dados
			em.merge(produtoExistente); // metodo merge incorpora as alterações
			em.getTransaction().commit();
			em.close();
			return produtoExistente; // Retorna o cliente atualizado
		} else {
			System.out.println("Produto não encontrado.");
			return null;
		}
	}

	// metodo responsavel por deletar um produto
	public void deletar(Integer id) {
		Produto produto = em.find(Produto.class, id);
		if (produto != null) {
			em.getTransaction().begin();
			em.remove(produto);
			em.getTransaction().commit();
			em.close();

		} else {
			System.out.println("Produto não encontrado para deletar.");
		}
	}

	public void deletarTodosProdutos() {
		 em.getTransaction().begin();
	     em.createQuery("DELETE FROM produto").executeUpdate();
	     em.getTransaction().commit();
	     em.close();
	}

	// metodo responsavel por buscar um produto a partir do id
	public Produto getByCodigo(Integer codigo) {
		Produto produtoExistente = em.find(Produto.class, codigo);
		return produtoExistente;
	}

	// busca TODOS os produtos salvos
	public Produto[] getProdutos() {
		em.getTransaction().begin();
		List<Produto> produtosList = em.createQuery("SELECT c FROM Produto c", Produto.class).getResultList();
		Produto[] produtosArray = produtosList.toArray(new Produto[produtosList.size()]);
		em.close();
		return produtosArray;
	}
}
