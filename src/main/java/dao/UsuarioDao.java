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
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.Usuario;

public class UsuarioDao {
	EntityManagerFactory emf;
	EntityManager em;

	public UsuarioDao() {
		this.emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		this.em = emf.createEntityManager();
	}

	// metodo responsavel por inserir user
	public Usuario inserir(Usuario usuario) {
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
		return null;
	}

	public boolean logar (Usuario usuario) {
		 try {
	            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha", Usuario.class);
	            query.setParameter("email", usuario.getEmail());
	            query.setParameter("senha", usuario.getSenha());
	            Usuario usuario2 = query.getSingleResult();
	            return true;
	        } catch (NoResultException e) {
	            return false;
	        }
	}

}
