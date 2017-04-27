package Persistencia;

import Entidade.Portal.Tipo;
import Entidade.Portal.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsrDAO {
    
    private static UsrDAO instance = new UsrDAO();
    
    public static UsrDAO getInstance() {
        return instance;
    }
    
    //Grava dados no banco
    public int grava(Usuario usr) {

        int n = 0;
        Connection conn = Conexao.getInstance().criaConexao();

        if (conn != null) {
            PreparedStatement pstmt = null;
            try {
                pstmt = conn.prepareStatement(
                        "INSERT INTO Usuario " +
                        "(tipo_id,login,nome,senha,email,data_nascimento,bloq,super)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt.setInt(1, usr.getTipo_id());
                pstmt.setString(2, usr.getLogin());
                pstmt.setString(3, usr.getNome());
                pstmt.setString(4, usr.getSenha());
                pstmt.setString(5, usr.getEmail());
                if(usr.getData_nascimento() != null){
                    pstmt.setDate(6, new java.sql.Date(usr.getData_nascimento().getTime()));
                }
                else{
                    pstmt.setDate(6, null);
                }
                pstmt.setString(7, usr.getBloq());
                pstmt.setString(8, usr.getSuperUsr());

                n = pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Inclusao Falhou!!!\n" + e.getMessage());
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return n;

    }
    
    // Altera dados pessoais
    
    public int alteraDadosPessoais(Usuario usr, int id) {

        int n = 0;
        Connection conn = Conexao.getInstance().criaConexao();

        if (conn != null) {
            PreparedStatement pstmt = null;
            try {
                pstmt = conn.prepareStatement(
                        "UPDATE usuario SET " +
                        "nome = ?, email = ?, data_nascimento= ?, about = ?" +
                        " WHERE id_user = " + id);
                pstmt.setString(1, usr.getNome());
                pstmt.setString(2, usr.getEmail());
                pstmt.setDate(3, new java.sql.Date(usr.getData_nascimento().getTime()));
                pstmt.setString(4, usr.getAbout());

                n = pstmt.executeUpdate();
                
                
            } catch (SQLException e) {
                System.out.println("Inclusao Falhou!!!\n" + e.getMessage());
                
                n = 0;
                
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return n;

    }

    //RECUPERA UMA  LISTA DE TODOS OS Usuarios DO BANCO
    public List<Usuario> leTodos(int id) {

        Statement stmt = null;
        List<Usuario> lstUsers = new ArrayList<Usuario>();
        Connection conn = Conexao.getInstance().criaConexao();

        if (conn != null) {
            try {
                stmt = conn.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery(
                        "SELECT * FROM usuario where id_user <> "+ id);
                
                    while (rs.next()) {
                    Usuario usr = new Usuario();

                    usr.setId_user(rs.getInt("id_user"));
                    usr.setTipo_id(rs.getInt("Tipo_id"));
                    usr.setLogin(rs.getString("login"));
                    usr.setNome(rs.getString("nome"));
                    usr.setEmail(rs.getString("email"));
                    usr.setData_nascimento(rs.getDate("data_nascimento"));
                    usr.setBloq(rs.getString("bloq"));
                    usr.setSuperUsr(rs.getString("super"));

                    lstUsers.add(usr);
                }

                if(lstUsers.isEmpty()){
                    lstUsers = null;
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
        return lstUsers;
    }
    
    //RECUPERA UMA  LISTA DE TODOS OS REGISTRO DO BANCO
    public List<Tipo> leTipo() {

        Statement stmt = null;
        List<Tipo> lstTipos = new ArrayList<Tipo>();
        Connection conn = Conexao.getInstance().criaConexao();

        if (conn != null) {
            try {
                stmt = conn.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery(
                        "SELECT * FROM Tipo");
                    while (rs.next()) {
                    Tipo tipo = new Tipo();

                    tipo.setId(rs.getInt("id"));
                    tipo.setTipo_user(rs.getString("tipo_user"));

                    lstTipos.add(tipo);
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
        return lstTipos;
    }
    
    // Verifica se existe email ja cadastrado anteriormente
    public Usuario validaEmail(String email) {

        Statement stmt = null;
        Usuario usuario = null;
        Connection conn = Conexao.getInstance().criaConexao();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(
                        "SELECT * FROM Usuario WHERE email ='" +
                        email+"'");
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
    
    
    // Verifica se existe email ja cadastrado anteriormente
    public Usuario validaLogin(String login) {

        Statement stmt = null;
        Usuario usuario = null;
        Connection conn = Conexao.getInstance().criaConexao();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(
                        "SELECT * FROM Usuario WHERE login ='" +
                        login+"'");
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
    
    
    // Carrega dados de um usário para manipulação
    public Usuario carregaDados(int id) {

        Statement stmt = null;
        Usuario usuario = null;
        Connection conn = Conexao.getInstance().criaConexao();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(
                        "SELECT * FROM Usuario WHERE id_user =" +
                        id+" and bloq = 'false'");
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
    
    // Lê os dados de um usário para na servelt
    public Usuario leDados(int id) {

        Statement stmt = null;
        Usuario usuario = null;
        Connection conn = Conexao.getInstance().criaConexao();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(
                        "SELECT * FROM Usuario WHERE id_user =" + id);
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
    
    
    //Bloqueia um usuário no Banco
    public int bloqUsr(int id) {

        int n = 0;
        Connection conn = Conexao.getInstance().criaConexao();

        if (conn != null) {
            PreparedStatement pstmt = null;
            try {
                pstmt = conn.prepareStatement(
                        "UPDATE usuario SET " +
                        "bloq = 'true' WHERE id_user =" + id);
                
                n = pstmt.executeUpdate();
                
            } catch (SQLException e) {
                System.out.println("Inclusao Falhou!!!\n" + e.getMessage());
                n = 0;
                
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return n;

    }
    
    //Restaura um usuário no Banco
    public int restauraUsr(int id) {

        int n = 0;
        Connection conn = Conexao.getInstance().criaConexao();

        if (conn != null) {
            PreparedStatement pstmt = null;
            try {
                pstmt = conn.prepareStatement(
                        "UPDATE usuario SET " +
                        "bloq = 'false' WHERE id_user =" + id);
                
                n = pstmt.executeUpdate();
                
            } catch (SQLException e) {
                System.out.println("Inclusao Falhou!!!\n" + e.getMessage());
                n = 0;
                
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return n;

    }
    
    //Altera a senha de um usuário no Banco
    public int alteraSenha(int id, String senha) {

        int n = 0;
        Connection conn = Conexao.getInstance().criaConexao();

        if (conn != null) {
            PreparedStatement pstmt = null;
            try {
                pstmt = conn.prepareStatement(
                        "UPDATE usuario SET " +
                        "senha = '" + senha + "' WHERE id_user =" + id);
                
                n = pstmt.executeUpdate();
                
            } catch (SQLException e) {
                System.out.println("Inclusao Falhou!!!\n" + e.getMessage());
                n = 0;
                
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return n;

    }
    
    //Deleta um usuário no Banco
    public int delUsr(int id) {

        int n = 0;
        Connection conn = Conexao.getInstance().criaConexao();

        if (conn != null) {
            PreparedStatement pstmt = null;
            try {
                pstmt = conn.prepareStatement(
                        "DELETE FROM usuario WHERE id_user = " + id);
                
                n = pstmt.executeUpdate();
                
            } catch (SQLException e) {
                System.out.println("Inclusao Falhou!!!\n" + e.getMessage());
                n = 0;
                
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return n;

    }
    
    
    // Popula dados na classe com o resultado do select   
    
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
        usuario.setBloq(rs.getString("bloq"));
        usuario.setSuperUsr(rs.getString("super"));

        return usuario;

    }
    
    // Altera os dados pessoais por meio de um acesso administrador    
    public int alteraCadastro(Usuario usr, int id) {

        int n = 0;
        Connection conn = Conexao.getInstance().criaConexao();

        if (conn != null) {
            PreparedStatement pstmt = null;
            try {
                pstmt = conn.prepareStatement(
                        "UPDATE usuario SET " +
                        "tipo_id = ?, nome = ?, senha = ?, email = ?, data_nascimento = ?, super = ?" +
                        " WHERE id_user = " + id);
                pstmt.setInt(1, usr.getTipo_id());
                pstmt.setString(2, usr.getNome());
                pstmt.setString(3, usr.getSenha());
                pstmt.setString(4, usr.getEmail());
                pstmt.setDate(5, new java.sql.Date(usr.getData_nascimento().getTime()));
                pstmt.setString(6, usr.getSuperUsr());

                n = pstmt.executeUpdate();
                
                
            } catch (SQLException e) {
                System.out.println("Atualização Falhou!!!\n" + e.getMessage());
                
                n = 0;
                
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return n;

    }
    
    
}