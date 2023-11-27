/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servico;

import java.util.Scanner;

import dao.ProdutoDao;
import dao.VendaDao;
import modelo.Cliente;
import modelo.Produto;
import modelo.Venda;


public class ProdutoServico {
    ProdutoDao dao; 
    Scanner entrada;
            
    public ProdutoServico() {
        this.dao = new ProdutoDao();
        this.entrada = new Scanner(System.in);
    }
    
    public void desenvolvimentoProduto(){
        int opcProduto = 0;
        do {      
            System.out.println("===========Menu de Opções de produto===========");
            System.out.println("1- Inserir Produto");
            System.out.println("2- Deletar Produto");
            System.out.println("3- Alterar Produto");
            System.out.println("Digite Zero para voltar ao menu anterior");
            opcProduto = entrada.nextInt();  
            Produto produto = new Produto();
            switch(opcProduto){
                case 0: break;
                case 1:
                    produto = new Produto();
                    System.out.print("Valor: ");
                    produto.setValor(entrada.nextFloat());
                    System.out.print("Descrição: ");
                    produto.setDescricao(entrada.next());
                    this.inserir(produto);
                    System.out.println("Registro salvo com sucesso!");
                    return;
                case 2:
                    System.out.println("Informe o codigo do produto: ");
                    int codigoDeletar = entrada.nextInt();
                    produto = this.getProduto(codigoDeletar);
                    if(produto != null){
                        this.deletar(produto);
                    }
                    System.out.println("Registro apagado com sucesso!");
                    return;
                case 3:
                    System.out.println("Informe o codigo do produto: ");
                    int codigoAtualizar = entrada.nextInt();
                    produto = new Produto();
                    produto.setCodigo(codigoAtualizar);
                    System.out.print("Novo valor do Produto: ");
                    produto.setValor(entrada.nextFloat());
                    System.out.print("Nova descrição do Produto: ");
                    produto.setDescricao(entrada.next());
                    produto = this.atualizar(produto);
                    if(produto != null){
                        System.out.println("Registro atualizado com sucesso!");
                    }
                    break;
                default:
                    System.out.println("Digite uma opção válida");
            }
        } while (opcProduto != 0);
    }
    
    public Produto inserir(Produto produto) {
    	if (produto == null) {
    		throw new IllegalArgumentException("Produto não pode ser nulo");
    	}

    	if (produto.getDescricao() == null || produto.getDescricao().isEmpty()) {
    		throw new IllegalArgumentException("Nome do produto não pode ser nulo ou vazio");
    	}

    	if (produto.getValor() == null || produto.getValor()==null) {
    		throw new IllegalArgumentException("Endereço do produto não pode ser nulo ou vazio");
    	}
    	dao.inserir(produto);
    	return null;
    }

	public void deletarTodosProdutos() {
		dao.deletarTodosProdutos();
	}

    public Produto atualizar(Produto produto) {
    	if (produto == null) {
    		throw new IllegalArgumentException("Produto não pode ser nulo");
    	}

    	if (produto.getDescricao() == null || produto.getDescricao().isEmpty()) {
    		throw new IllegalArgumentException("Nome do produto não pode ser nulo ou vazio");
    	}

    	if (produto.getValor() == null || produto.getValor()==null) {
    		throw new IllegalArgumentException("Endereço do produto não pode ser nulo ou vazio");
    	}

    	dao.atualizar(produto, produto.getCodigo());
    	return null;
    }

    
    public Produto getProduto(int codigo) {
        Produto produtoExistente = dao.getByCodigo(codigo);
        return produtoExistente;
    }

    
    public boolean deletar(Produto produto) {
        if (produto != null) {
            dao.deletar(produto.getCodigo());
            return true;
        } else {
            System.out.println("Produto não encontrado para deletar.");
        }
        return false;
    }

    
    
    public void listaProdutos() {
        System.out.println("Deseja realmente imprimir o relatório (S\\N)");
        String resposta = entrada.next();
        if (resposta.equalsIgnoreCase("S")) {
        	Produto[] produtos = dao.getProdutos();
        	for (Produto produto : produtos) {
        		System.out.println(produto.toString());
        	}

        }
           }
    }




