package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MobiWineDAO;
import model.entities.Vinho;

@WebServlet("/Editar")
public class Editar extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{

		//Obtendo o rgm do aluno a ser atualizado
				int id = Integer.parseInt(request.getParameter("id"));
				
				//Obtendo o registro do aluno com base no RGM
				Vinho vinho= new MobiWineDAO().retrieve(id);
				if (vinho == null) {
					response.getWriter().println("'vinho' is null");
					return;
				}
						
				//Passando o objeto aluno como atributo para ser exibido no JSP
				request.setAttribute("vinho", vinho);
				request.setAttribute("tipoVinho", vinho.getTipoVinho());
				
				//Encaminhando a requisição para o arquivo JSP
				request.getRequestDispatcher("EditarVinho.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		
		//Obtendo os dados dos campos do formulário
				int id                = Integer.parseInt(request.getParameter("id"));
				String nome   	      = request.getParameter("nome");
				String pais  	      = request.getParameter("pais");
				String regiao 	      = request.getParameter("regiao");
				String nomeProdutor   = request.getParameter("nome_produtor");
				String anoSafra  	  = request.getParameter("ano_safra");
				String descricao 	  = request.getParameter("descricao");
				String imagem  	 	  = "teste"; //request.getParameter("txtimagem");
				String preco     	  = request.getParameter("preco");
						
//				//Obtendo a imagem (foto do aluno) 
//				Part arquivo = request.getPart("txtArquivo");
//				
//				if (arquivo.getSize() != 0) {	
//					//Obtendo a extensão do arquivo
//					String ext = arquivo.getSubmittedFileName().split("\\.")[1];
//
//					//Obtendo o caminho das imagens
//					String caminho = request.getServletContext().getRealPath("imagens");
//					
//					//Monta o nome do arquivo de imagem
//					foto = rgm + "." + ext;
//					
//					//Monta o caminho completo da imagem
//					caminho = caminho + "/" + foto;
//					
//					//Grava a imagem no caminho definido
//					arquivo.write(caminho);
//				} 
//				
				//Criando um objeto Aluno
				Vinho vinho = new Vinho(id, nome, pais, regiao, nomeProdutor, anoSafra, descricao, imagem, preco);
				
				//DAO para atualização do registro
				new MobiWineDAO().update(vinho);

				
				
			    //Exibindo alerta de sucesso e redirencionando para tela de vinhos
				PrintWriter out = response.getWriter();
				out.println("<script> alert('Cadastro Alterado com Sucesso');  top.window.location = 'EditarVinho"
						+ ".jsp';</script>");


				
				
			}
		
	
}
