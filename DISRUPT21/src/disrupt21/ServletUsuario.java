package disrupt21;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BancoDeDados.Conexao;

@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Connection connection;

	public ServletUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("bot") != null) {
			connection = new Conexao().conectar();
			RequestDispatcher rd = request.getRequestDispatcher("ServletPista");
			String confirmaBot = "sim";
			request.setAttribute("confirmaBot", confirmaBot);
			rd.forward(request, response);
		} else {
		
			int tipo = Integer.parseInt(request.getParameter("usuario"));
			if (tipo == 1) {
				try {
					if (!(response.isCommitted())) {
						response.sendRedirect("Admin.jsp");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					if (!(response.isCommitted())) {
						response.sendRedirect("Coordenadas.jsp");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
