import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static void main(String[] args) {
        Connection conexao = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao =  DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/banco_a3",
                    "sa",
                    "");
    } catch (ClassNotFoundException ex) {
        System.out.println("Driver não encontrado: " + ex.getMessage());
    } catch (SQLException ex) {
        System.out.println("Erro de conexão: " + ex.getMessage());
    } finally {
        if (conexao != null){
            try {
                conexao.close();
            } catch (SQLException ec) {
                System.out.println("Erro ao fechar conexão: " + ec.getMessage());
            }
        }
    }
}

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/banco_a3",
            "sa",
            ""
        );
    }
}
