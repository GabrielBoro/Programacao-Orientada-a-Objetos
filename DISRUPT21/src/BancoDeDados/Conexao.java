package BancoDeDados;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String caminho = "";
	private String login = "";
	private String senha = "";
	private Connection conexao;
	
	public Connection conectar() {
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(caminho, login, senha);
		}catch (ClassNotFoundException e) {
			System.out.println("Erro ao carregar o driver de conexão \n" + e);
		} catch( SQLException e) {
			System.out.println("Erro ao conectar ao banco de dados\n " + e);
		}
		return conexao;
	}
}
