package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MobiWineDAO;
import model.entities.Vinho;

/**
 * Servlet implementation class Listar
 */
@WebServlet("/Listar")
public class Listar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Listar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MobiWineDAO dao = new MobiWineDAO();
		ArrayList<Vinho> vinhos = new ArrayList();
		vinhos = dao.RetrieveAll();
		
		//Passando o objeto aluno como atributo para ser exibido no JSP
				
		request.setAttribute("vinhos", vinhos);		
		//Encaminhando a requisição para o arquivo JSP	
		request.getRequestDispatcher("ListarVinho.jsp").forward(request, response);
	}

}
