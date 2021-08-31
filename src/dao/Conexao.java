package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    //CONFIGURAÇÃO DO BANCO
    private static final String URL = "jdbc:mysql://localhost:3306/Cadastro_LoginJAVA";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static Connection connection;

    public static Connection conectar() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD); //ABRINDO CONEXÃO
            return connection;
        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
        } catch (ClassNotFoundException e1) {
            System.out.println("Erro aqui: " + e1.getMessage());
        }
        return null;
    }

    public static void desconectar() {
        try {
            if (connection != null) {
                connection.close(); //FECHANDO CONEXÃO
                connection = null;
            }
        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }
    }

}
