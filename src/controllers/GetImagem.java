package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetImagem")
public class GetImagem extends HttpServlet {
	private String nomeCompletoImg;
	private static String IMG_PLACEHOLDER = "do_not_rename.jpg";
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		if (request.getParameter("id") == null || request.getParameter("id").isEmpty())
			return;
		int id = Integer.parseInt(request.getParameter("id"));
		String caminhoPastaImagem = request.getServletContext().getRealPath("/imagens");
		byte[] imagem = recuperarImagemOuDefault(caminhoPastaImagem, id);
		if (imagem == null)
			return;
		int[] dimensoes = calcularDimensoes(imagem);
		request.setAttribute("naturalWidth", dimensoes[0]);
		request.setAttribute("naturalHeight", dimensoes[1]);
		response.setContentLength(imagem.length);
		System.out.println("Content length: " + String.valueOf(imagem.length));
		response.setContentType(URLConnection.guessContentTypeFromName(this.nomeCompletoImg));
		System.out.println("Content-type: " + URLConnection.guessContentTypeFromName(this.nomeCompletoImg));
		response.getOutputStream().write(imagem);
	}
	
	
	private byte[] recuperarImagemOuDefault(String caminhoPasta, int id)
	{
		byte[] imagem = null;
		try {
			imagem = recuperarImagem(caminhoPasta, id);
			if (imagem == null) {
				imagem = Files.readAllBytes(Paths.get(caminhoPasta, IMG_PLACEHOLDER));
				this.nomeCompletoImg = IMG_PLACEHOLDER;
			}
			System.out.println("Retornando imagem " + this.nomeCompletoImg);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Imagem placeholder (do_not_rename.jpg) não encontrada :(");
		}
		return imagem;
	}
	
	
	private byte[] recuperarImagem(String caminhoPasta, int id) 
	{
		byte[] imagem = null;
		String strId = String.valueOf(id);
		File diretorio = new File(caminhoPasta);
		this.nomeCompletoImg = null;
		for (String nomeArquivo : diretorio.list()) {
			if (nomeArquivo.split("\\.")[0].equals(strId))
				this.nomeCompletoImg = nomeArquivo;
		}
		if (this.nomeCompletoImg == null)
			return null;
		try {
			Path fullCaminhoImagem = Paths.get(caminhoPasta, this.nomeCompletoImg);
			imagem = Files.readAllBytes(fullCaminhoImagem);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Não foi possível abrir a imagem do produto");
		}
		return imagem;
	}
	
	
	private int[] calcularDimensoes(byte[] imagem)
	{
		int[] dimensoes = {100, 100};
		InputStream is = new ByteArrayInputStream(imagem);
		try {
			BufferedImage bi = ImageIO.read(is);
			dimensoes[0] = bi.getWidth();
			dimensoes[1] = bi.getHeight();
		} catch (Exception e) {
			System.err.println("Não foi possível calcular as dimensões da imagem");
		}
		return dimensoes;
	}
}
