package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Conexao instance = new Conexao();

    public static Conexao getInstance() {
        return instance;
    }

    private Conexao() {
    }

    public Connection criaConexao() {
        
        String driver = "org.gjt.mm.mysql.Driver";
        String strConexao = "jdbc:mysql://localhost/portifolio";
        String usuario = "root";
        String senha = "admin";

        Connection conn = null;

        try {
            Class.forName(driver);
            System.out.println("Driver registrado.");
            conn = DriverManager.getConnection(strConexao, usuario, senha);
            System.out.println("Conexão efetuada.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver do banco nao encontrado.");
        } catch (SQLException ex) {
            System.out.print("Erro ao obter conexao: " + ex.getMessage());
        }

        return conn;

    }

}
