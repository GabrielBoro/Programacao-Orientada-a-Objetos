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

@WebServlet("/ServletPista")
public class ServletPista extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement p;
	private String sql;
	private ResultSet rs;
	private Connection connection;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Double latCerta[] = new Double[] { 41.9009, 41.8946, 41.9010, 41.8989, 41.8902, 41.8986, 41.9062, 41.8910 };
		Double lonCerta[] = new Double[] { 12.4832, 12.4830, 12.4426, 12.4731, 12.4930, 12.4768, 12.4527, 12.4466 };
		double latitude = -1;
		if(request.getParameter("latitude")!=null) {			
			latitude = Double.parseDouble(request.getParameter("latitude"));
		}
		double longitude = -1;
		if(request.getParameter("longitude")!=null) {			
			longitude = Double.parseDouble(request.getParameter("longitude"));
		}			
		String confirmaBot = (String) request.getAttribute("confirmaBot");

		for (int x = 0; x < latCerta.length; x++) {
			try {
				if (confirmaBot != null || latitude != -1  && longitude != -1 && latitude == latCerta[x] && longitude == lonCerta[x]) {
					connection = new Conexao().conectar();
					sql = "select max(id_pista) from Pista";
					p = connection.prepareStatement(sql);
					rs = p.executeQuery(sql);
					rs.next();
					int qtdPista = rs.getInt(1);
					if (qtdPista != 0) {
						String[] pista = new String[qtdPista];
						sql = "select pista from Pista";
						p = connection.prepareStatement(sql);
						rs = p.executeQuery(sql);
						for (int y = 0; y < qtdPista; y++) {
							rs.next();
							pista[y] = rs.getString(1);
						}
						sql = "select id_chave from Pista";
						p = connection.prepareStatement(sql);
						rs = p.executeQuery(sql);
						rs.next();
						int chaveBot = rs.getInt(1);
						request.setAttribute("chaveBot", chaveBot);
						request.setAttribute("pista", pista);
						RequestDispatcher rd = request.getRequestDispatcher("Final.jsp");
						if(!(response.isCommitted())) {							
						rd.forward(request, response);
						}
					} else {
						String msgBanco = "Nenhuma pista cadastrada no banco";
						request.setAttribute("msgBanco", msgBanco);
						RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
						rd.forward(request, response);
					}
				}
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
		}
		if (!(response.isCommitted())) {
			String msgErro = "Coordenadas incorretas";
			request.setAttribute("msgErro", msgErro);
			RequestDispatcher rd = request.getRequestDispatcher("Coordenadas.jsp");
			rd.forward(request, response);
		}

	}
}