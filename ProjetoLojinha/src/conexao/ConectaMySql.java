package conexao;

import com.mysql.cj.jdbc.Driver;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaMySql {
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DBNAME = "tarde_A_Lojinha";
    private final String URL = "jdbc:mysql://ESN509VMYSQL/" + DBNAME;
    private final String LOGIN = "aluno";
    private final String SENHA = "Senai1234";

    //objeto da classe Connection que será usado para configurar
    //o IP do banco, usúario e senha //import java.sql.Connection;
    private Connection conexao;

    //Criar um método que irá configurar o objeto conexão
    //O método irá retornar o próprio objeto conexão, ou seja, ao final de
    //tudo será retornado o estado da conexão, isto é, conexão aberta ou não

    public Connection iniciarConexao() {
        //configuração do Driver de banco de dados
        try {
            Class.forName(DRIVER);
        //Configuração do caminho (IP), Usúario, senha e banco de dados
        //O método getConnection() tenta iniciar a conexão com os valores
        //passados como parâmetro
            conexao = DriverManager.getConnection(URL, LOGIN, SENHA);
            JOptionPane.showMessageDialog(null,"Conectado com sucesso!");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conexao;
    }

    public void close() {
        try {
            conexao.close();
            System.out.println("Desconectou");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
