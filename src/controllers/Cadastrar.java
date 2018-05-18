package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.dao.MobiWineDAO;
import model.entities.Vinho;

@WebServlet("/Cadastro")
@MultipartConfig
public class Cadastrar extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Chamando a view de cadastro
		request.getRequestDispatcher("CadastroVinho.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtendo os dados dos campos do formul·rio
		String nome   	      = request.getParameter("nome");
		String pais  	      = request.getParameter("pais");
		String regiao 	      = request.getParameter("regiao");
		String nomeProdutor   = request.getParameter("nome_produtor");
		String anoSafra  	  = request.getParameter("ano_safra");
		String descricao 	  = request.getParameter("descricao");
		String preco     	  = request.getParameter("preco");
		String tipoVinho      = request.getParameter("tipo_vinho");
		
//		//Obtendo a imagem (foto do aluno) 
//		Part arquivo = request.getPart("txtArquivo");
//		
//		if (arquivo.getSize() != 0) {	
//			//Obtendo a extens√£o do arquivo
//			String ext = arquivo.getSubmittedFileName().split("\\.")[1];
//
//			//Obtendo o caminho das imagens
//			String caminho = request.getServletContext().getRealPath("imagens");
//			
//			//Monta o nome do arquivo de imagem
//			foto = rgm + "." + ext;
//			
//			//Monta o caminho completo da imagem
//			caminho = caminho + "/" + foto;
//			
//			//Grava a imagem no caminho definido
//			arquivo.write(caminho);
//		} 
		
		//Criando um objeto Vinho
		Vinho vinho = new Vinho(nome, pais, regiao, nomeProdutor, anoSafra, descricao, preco, tipoVinho);
		
		//DAO para inserir registro
		new MobiWineDAO().create(vinho);
		
		//Chamando a view de cadastro novamente
		request.getRequestDispatcher("CadastroVinho.jsp").forward(request, response);
	}
}
