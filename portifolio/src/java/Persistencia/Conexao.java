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
        
        String driver = "com.mysql.cj.jdbc.Driver";
        String paransServ = "?useSSL=false&useTimezone=true&serverTimezone=UTC";
        String strConexao = "jdbc:mysql://localhost/portifolio" + paransServ;
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
