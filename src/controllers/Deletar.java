package controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MobiWineDAO;

@WebServlet("/Deletar")
public class Deletar extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		if (request.getParameter("id") == null || request.getParameter("id").isEmpty())
			return;
		int id = Integer.parseInt(request.getParameter("id"));
		new MobiWineDAO().delete(id);
		String caminhoPasta = request.getServletContext().getRealPath("/imagens");
		try {
			deletarImagemExistente(caminhoPasta, id);
		} catch (Exception e) {
			System.err.println("Não foi possível deletar a imagem cadastrada");
		}
		request.getRequestDispatcher("CadastroVinho.jsp").forward(request, response);
	}
	
	
	private void deletarImagemExistente(String caminhoPasta, int id)
		throws Exception
	{
		System.out.println("Tentando deletar uma possível imagem cadastrada");
		String strId = String.valueOf(id);
		File diretorio = new File(caminhoPasta);
		String nomeImg = null;
		for (String nomeArquivo : diretorio.list()) {
			if (nomeArquivo.split("\\.")[0].equals(strId)) {
				nomeImg = nomeArquivo;
				break;
			}
		}
		if (nomeImg != null)
			Files.delete(Paths.get(caminhoPasta, nomeImg));
	}
}
