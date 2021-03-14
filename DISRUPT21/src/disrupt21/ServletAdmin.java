package disrupt21;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BancoDeDados.Conexao;

@WebServlet("/ServletAdmin")
public class ServletAdmin extends HttpServlet {

	public ServletAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int qtdPista = Integer.parseInt(request.getParameter("pista"));
		int chave = Integer.parseInt(request.getParameter("chave"));
		String[] pista = new String[qtdPista];
		for (int x = 0; x < qtdPista; x++) {
			pista[x] = request.getParameter("pista" + x);
		}
		inserir(pista, chave, request, response);
	}

	private static final long serialVersionUID = 1L;
	private PreparedStatement p;
	private String sql;
	private Connection connection;

	public void inserir(String[] pista, int chave, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		connection = new Conexao().conectar();
		try {
			sql = "delete from Pista";
			p = connection.prepareStatement(sql);
			p.execute();
			
			sql = "insert into Pista (id_pista, pista, id_chave) values(?, ?, ?)";
			p = connection.prepareStatement(sql);
			for (int x = 0; x < pista.length; x++) {
				p.setInt(1, x+1);
				p.setString(2, pista[x]);
				p.setInt(3, chave);
				p.execute();
			}

			String msgSuc = "Pistas cadastradas com sucesso";
			request.setAttribute("msgSuc", msgSuc);
			RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
			rd.forward(request, response);

		} catch (SQLException e) {
			System.out.println("Erro ao inserir dados no banco\n" + e);
		}

	}

}
