package controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
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

@WebServlet("/Editar")
@MultipartConfig()
public class Editar extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		if (request.getParameter("id") == null)
			return;
		if (request.getParameter("id").isEmpty())
			return;
		//Obtendo o rgm do aluno a ser atualizado
		int id = Integer.parseInt(request.getParameter("id"));

		//Obtendo o registro do vinho com base no ID
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
		int id = Integer.parseInt(request.getParameter("id"));
		String nome   	      = request.getParameter("nome");
		System.out.println(nome);
		String pais  	      = request.getParameter("pais");
		String regiao 	      = request.getParameter("regiao");
		String nomeProdutor   = request.getParameter("nome_produtor");
		String anoSafra  	  = request.getParameter("ano_safra");
		String descricao 	  = request.getParameter("descricao");
		String preco     	  = request.getParameter("preco");
		String tipoVinho      = request.getParameter("tipoVinho");
		Part imagem           = request.getPart("imgFile");
		// Cria um objeto vinho
		Vinho vinho = new Vinho(nome, pais, regiao, nomeProdutor, anoSafra, descricao, preco, tipoVinho);


		// Atualiza o registro no banco
		System.out.println("Atualizando produto de id: " + String.valueOf(id));
		new MobiWineDAO().update(vinho);


		// Salva/Substitui uma imagem, caso tenha sido upada no formato <id>.<extensao>
		if (imagem.getSize() != 0) {
			try {
				String caminhoPasta = request.getServletContext().getRealPath("/imagens");
				removerImagemExistente(caminhoPasta, id);
			} catch (Exception e) {
				System.err.println("Não foi possível deletar uma possível imagem já cadastrada");
			}
			String extensao = imagem.getSubmittedFileName().split("\\.")[1];
			String nomeImagem = String.valueOf(id) +  "." + extensao;
			// Obtendo caminho da pasta 'imagens'
			String caminho = request.getServletContext().getRealPath("/imagens");
			// Obtendo caminho completo para salvar a imagem
			Path caminhoCompleto = Paths.get(caminho, nomeImagem);
			System.out.println("Salvando imagem em: " + caminhoCompleto);
			// Grava a imagem no caminho definido
			imagem.write(caminhoCompleto.toString());
		}


		// Exibindo alerta de sucesso e redirencionando para tela de vinhos
		PrintWriter out = response.getWriter();
		out.println("<script> alert('Cadastro Alterado com Sucesso');  top.window.location = 'CadastroVinho"
				+ ".jsp';</script>");

	}
	
	
	private void removerImagemExistente(String caminhoPasta, int id)
		throws Exception
	{
		System.out.println("Tentando remover uma possível img já cadastrada...");
		String imgParaDeletar = null;
		String strId = String.valueOf(id);
		File diretorio = new File(caminhoPasta);
		for (String nomeArquivo : diretorio.list()) {
			if (nomeArquivo.split("\\.")[0].equals(strId)) {
				imgParaDeletar = nomeArquivo;
				break;
			}
		}
		if (imgParaDeletar != null)
			Files.deleteIfExists(Paths.get(caminhoPasta, imgParaDeletar));
	}


}
