package aplicacao;

import Servico.ClienteServico;
import Servico.ProdutoServico;
import Servico.UsuarioServico;
import Servico.VendaServico;
import modelo.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();

		Scanner entrada = new Scanner(System.in);
		ClienteServico clienteServico = new ClienteServico();
		VendaServico vendaServico = new VendaServico();
		ProdutoServico produtoServico = new ProdutoServico();
		UsuarioServico usuarioServico = new UsuarioServico();

		int opc;
		System.out.println("=============================================="); 
		System.out.println("Bem Vindo ao Sistema de Controle de Refeição");
		System.out.println("=============================================");
		System.out.println("================== Login ====================");
		System.out.println("Email:");
		Usuario user = new Usuario(); //USUARIO: jj
        String email = entrada.next();
		System.out.println("Senha:"); //SENHA: jj
		String senha = entrada.next();
		user.setEmail(email);
		user.setSenha(senha);
				if (usuarioServico.logar(user)) {
		
					do {
						mostraMenuPrincipal();
						opc = entrada.nextInt();
						switch (opc) {
						case 0:
							System.exit(0);
							break;
						case 1:
							clienteServico.desenvolvimentoCliente();
							break;
						case 2:
							produtoServico.desenvolvimentoProduto();
							break;
						case 3:
							new VendaServico().desenvolvimentoVenda();
							break;
						case 4:
							System.out.println("==============================================");
							System.out.println("Listagem dos clientes cadastrados");
							System.out.println("==============================================");
							new ClienteServico().listaClientes();
							break;
						case 5:
							System.out.println("==============================================");
							System.out.println("Listagem dos produtos cadastrados");
							System.out.println("==============================================");
							new ProdutoServico().listaProdutos();
							break;
						case 6:
							System.out.println("==============================================");
							System.out.println("Listagem das vendas registradas");
							System.out.println("==============================================");
							new VendaServico().listaVendas();
							break;
						case 7:
							System.out.println("==============================================");
							System.out.println("Nota FISCAL");
							System.out.println("==============================================");
							new VendaServico().notaFiscal();
							break;
						case 8:
							System.out.println("==============================================");
							System.out.println("DELETAR BANCO DE DADOS");
							System.out.println("==============================================");
							System.out.println("Deseja realmente limpar o banco de dados? (S\\N)");
							if (entrada.next().equals("S")) {
								vendaServico.deletarTodasVendas();
								produtoServico.deletarTodosProdutos();
								clienteServico.deletarTodosClientes();
							}
							break;
						case 9:
							System.out.println("=================================================");
							System.out.println("Relatorio da maior refeição cadastrada do cliente");
							System.out.println("=================================================");
							new VendaServico().maiorValorVenda();
							break;
						case 10:
							System.out.println("=================================================");
							System.out.println("Relatorio da menor refeição cadastrada do cliente");
							System.out.println("=================================================");
							new VendaServico().menorValorVenda();
							break;
						case 11:
							System.out.println("=================================================");
							System.out.println("Relatorio do total das refeições cadastradas do cliente");
							System.out.println("=================================================");
							new VendaServico().totalVendaCliente();
							break;
						default:
							System.out.println("Digite uma opção válida");
						}
					} while (true);
				} else {
					System.out.println("Usuário ou senha inválidos");
				}
				
	} 

	public static void mostraMenuPrincipal() {
		System.out.println("===========Menu de Opções===========");
		System.out.println("1- Cadrasto de Cliente");
		System.out.println("2- Cadastro de Produto ");
		System.out.println("3- Lançamento de Vendas");
		System.out.println("4- Listar dados dos Cliente");
		System.out.println("5- Listar dados dos Produtos");
		System.out.println("6- Listar vendas cadastradas");
		System.out.println("7- Emitir nota fiscal");
		System.out.println("8- Limpar banco de dados");
		System.out.println("9- Relatorio- Maior valor da refeição do cliente");
		System.out.println("10- Relatorio- Menor valor da refeição do cliente");
		System.out.println("11- Relatorio- tonalizar das refeições do cliente");
		System.out.println("Digite Zero para terminar");
	}
}
