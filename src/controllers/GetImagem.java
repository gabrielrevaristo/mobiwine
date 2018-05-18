package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getImagem")
public class GetImagem extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		if (request.getParameter("id") == null)
			return;
		int id = Integer.parseInt(request.getParameter("id"));
		/*
		 * Pode haver problemas ao utilizar o getRealPath(), pois ele retorna um endereço
		 * da pasta 'fotos' na pasta do Tomcat, que demora para sincronizar com a pasta 'fotos'
		 * em WebContent. 
		 * Portanto, as imagens também devem ser salvas utilizando getRealPath()
		 */
		String caminhoPastaImagem = request.getServletContext().getRealPath("imagens");
		byte[] imagem = recuperarImagemOuDefault(caminhoPastaImagem, id);
		if (imagem == null)
			return;
		response.getOutputStream().write(imagem);
	}
	
	
	private byte[] recuperarImagemOuDefault(String caminhoPasta, int id)
	{
		byte[] imagem = null;
		try {
			imagem = recuperarImagem(caminhoPasta, id);
			if (imagem == null)
				imagem = Files.readAllBytes(Paths.get(caminhoPasta, "do_not_rename.jpg"));
		} catch (Exception e) {
				System.err.println("Imagem placeholder (do_not_rename.jpg) não encontrada :(");
		}
		return imagem;
	}
	
	
	private byte[] recuperarImagem(String caminhoPasta, int id) 
	{
		byte[] imagem = null;
		String strId = String.valueOf(id);
		File diretorio = new File(caminhoPasta);
		String nomeImg = null;
		for (String nomeArquivo : diretorio.list()) {
			if (nomeArquivo.split("\\.")[0].equals(strId))
				nomeImg = nomeArquivo;
		}
		Path fullCaminhoImagem = Paths.get(caminhoPasta, nomeImg);
		System.out.println("Tentando recuperar " + fullCaminhoImagem);
		try {
			imagem = Files.readAllBytes(fullCaminhoImagem);
		} catch (Exception e) { }
		return imagem;
	}
}
