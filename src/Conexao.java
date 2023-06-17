import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    // Definir o caminho de conexão, usuário e senha
    private static String DB_URL = "jdbc:mysql://localhost:3306/senhasegura";
    private static String USERNAME = "root";
    private static String PASSWORD = "";

    private static Connection connection = null;
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            } catch (SQLException e) {
                System.out.println("Falha na conexão com o banco de dados");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
