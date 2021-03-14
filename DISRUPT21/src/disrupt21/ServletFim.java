package disrupt21;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BancoDeDados.Conexao;

@WebServlet("/ServletFim")
public class ServletFim extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement p;
	private String sql;
	private ResultSet rs;
	private Connection connection;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		connection = new Conexao().conectar();

		try {
			sql = "select id_chave from Pista";
			p = connection.prepareStatement(sql);
			rs = p.executeQuery(sql);
			rs.next();
			int chaveCerta = rs.getInt(1);
			int chave = Integer.parseInt(request.getParameter("chave"));
			if (chave == chaveCerta) {
				response.sendRedirect("Parabens.jsp");
			} else {
				sql = "select max(id_pista) from Pista";
				p = connection.prepareStatement(sql);
				rs = p.executeQuery(sql);
				rs.next();
				int qtdPista = rs.getInt(1);
				String[] pista = new String[qtdPista];
				sql = "select pista from Pista";
				p = connection.prepareStatement(sql);
				rs = p.executeQuery(sql);
				for (int y = 0; y < qtdPista; y++) {
					rs.next();
					pista[y] = rs.getString(1);
				}
				request.setAttribute("pista", pista);
				String msgChave = "Chave incorreta";
				request.setAttribute("msgChave", msgChave);
				RequestDispatcher rd = request.getRequestDispatcher("Final.jsp");
				rd.forward(request, response);
			}
		} catch (IOException |
				SQLException e) {
			e.printStackTrace();
		}

	}
}