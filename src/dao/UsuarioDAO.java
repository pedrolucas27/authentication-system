package dao;

import dao.Conexao;
import dominio.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    public boolean inserir(Usuario u) {
        boolean resultado = false;

        String query = "INSERT INTO Usuario (email,senha,nome) VALUES (?,?,?)"; //query a ser executada
        Connection conexao = Conexao.conectar(); //ABRINDO CONEXÃO

        try {

            PreparedStatement comando = conexao.prepareStatement(query); //PREPARANDO query PARA SER EXECUTADA

            //PREENCHENDO OS VALORES DAS COLUNAS 
            comando.setString(1, u.getEmail());
            comando.setString(2, u.getSenha());
            comando.setString(3, u.getNome());

            comando.execute(); //EXECUTANDO query
            resultado = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar();
        }

        return resultado;
    }

    public ArrayList<Usuario> listar() {
        ArrayList<Usuario> list = null;

        String query = "SELECT * FROM Usuario";
        Connection conexao = Conexao.conectar();

        try {

            PreparedStatement ps = conexao.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            list = new ArrayList<>();

            while (rs.next()) {
                Usuario u = new Usuario();

                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));

                list.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar();
        }

        return list;
    }

    public Usuario validarUsuario(String email, String senha) {
        Usuario u = null;

        String query = "SELECT * FROM Usuario WHERE email = ? AND senha = ?";
        Connection conexao = Conexao.conectar();

        try {

            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new Usuario();
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar();
        }
        return u;
    }

}
