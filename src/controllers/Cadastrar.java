package controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
		//Obtendo os dados dos campos do formulário
		String nome   	      = request.getParameter("nome");
		String pais  	      = request.getParameter("pais");
		String regiao 	      = request.getParameter("regiao");
		String nomeProdutor   = request.getParameter("nome_produtor");
		String anoSafra  	  = request.getParameter("ano_safra");
		String descricao 	  = request.getParameter("descricao");
		String preco     	  = request.getParameter("preco");
		String tipoVinho      = request.getParameter("tipo_vinho");
		Part imagem           = request.getPart("imgFile");

		// Cria um objeto vinho
		Vinho vinho = new Vinho(nome, pais, regiao, nomeProdutor, anoSafra, descricao, preco, tipoVinho);
		
		
		// Salva objeto no banco de dados
		int id = new MobiWineDAO().create(vinho);
		System.out.println("id cadastrado: " + String.valueOf(id));
		
		
		// Salva uma imagem, caso tenha sido upada no formato <id>.<extensao>
		if (imagem.getSize() != 0) {
			String extensao = imagem.getSubmittedFileName().split("\\.")[1];
			String nomeImagem = String.valueOf(id) +  "." + extensao;
			// Obtendo caminho da pasta 'imagens'
			String caminho = request.getServletContext().getRealPath("imagens");
			// Obtendo caminho completo para salvar a imagem
			Path caminhoCompleto = Paths.get(caminho, nomeImagem);
			System.out.println("Salvando imagem em: " + caminhoCompleto);
			// Grava a imagem no caminho definido
			imagem.write(caminhoCompleto.toString());
		}
		
		
		//Chamando a view de cadastro novamente
		request.getRequestDispatcher("CadastroVinho.jsp").forward(request, response);
	}
}
