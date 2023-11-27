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

import dao.UsuarioDao;
import modelo.Cliente;
import modelo.Usuario;


public class UsuarioServico {
	UsuarioDao dao;
	Scanner entrada;

	public UsuarioServico() {// construtor
		this.dao = new UsuarioDao();
		this.entrada = new Scanner(System.in); // cria instancia de entrada de dados
	}


	public boolean logar(Usuario usuario) {
		if (usuario == null) {
			throw new IllegalArgumentException("Cliente não pode ser nulo");
		}

		if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Nome do cliente não pode ser nulo ou vazio");
		}

		if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
			throw new IllegalArgumentException("Endereço do cliente não pode ser nulo ou vazio");
		}

		boolean resultado = dao.logar(usuario);
		
		return resultado;
	}

}