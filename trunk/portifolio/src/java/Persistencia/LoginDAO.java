package Persistencia;

import Entidade.Portal.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginDAO {
    
    private static LoginDAO instance = new LoginDAO();
    
    public static LoginDAO getInstance() {
        return instance;
    }

    private LoginDAO() {
    }
    
    public Usuario login(String login, String senha) {

        Statement stmt = null;
        Usuario usuario = null;
        Connection conn = Conexao.getInstance().criaConexao();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(
                        "SELECT * FROM usuario WHERE login = '" +
                        login + "' " + "AND senha = '" + senha + "' AND bloq = 'false'");
                if (rs.next()) {
                    usuario = carregaDadosNoObjeto(rs);
                } else {
                    usuario = null;
                }
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
    }
         return usuario;

    }

    private Usuario carregaDadosNoObjeto(ResultSet rs) throws SQLException {

        Usuario usuario = new Usuario();

        usuario.setId_user(rs.getInt("id_user"));
        usuario.setTipo_id(rs.getInt("tipo_id"));
        usuario.setLogin(rs.getString("login"));
        usuario.setNome(rs.getString("nome"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setEmail(rs.getString("email"));
        usuario.setFoto(rs.getString("foto"));
        usuario.setAbout(rs.getString("about"));
        usuario.setData_nascimento(rs.getDate("data_nascimento"));

        return usuario;

    }
    
}
