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
import javax.persistence.Query;

import modelo.Cliente;
import modelo.Venda;


public class VendaDao {
	EntityManagerFactory emf;
	EntityManager em;

	public VendaDao() {
		this.emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		this.em = emf.createEntityManager();
	}

	public Venda inserir(Venda venda) {
		em.getTransaction().begin();
		em.persist(venda);
		em.getTransaction().commit();
		em.close();
		return null;
	}

	public Venda getByCodigo(Integer id) {
		Venda vendaExistente = em.find(Venda.class, id);
		return vendaExistente;
	}
	
	  public void deletarTodasVendas() {
	 em.getTransaction().begin();
     em.createNativeQuery("DELETE FROM venda").executeUpdate();
     em.getTransaction().commit();
     em.close();

	    }	

	public Venda[] getVendasDoCliente(Integer id) {
	   
	    try {
	        Query query = em.createQuery("SELECT v FROM Venda v WHERE v.cliente.id = :id");
	        query.setParameter("id", id);
	        List<Venda> vendaList = query.getResultList();
	        Venda[] vendas = vendaList.toArray(new Venda[vendaList.size()]);
	        return vendas;
	    } finally {
	        em.close();
	    }
	}
	
	public Venda[] getVendas() {
		List<Venda> vendasList = em.createQuery("SELECT c FROM Venda c", Venda.class).getResultList();
		Venda[] vendasArray = vendasList.toArray(new Venda[vendasList.size()]);
		return vendasArray;
		
	}
	
	
}
