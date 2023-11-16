package comandos;

import classes.Produtos;
import conexao.ConectaMySql;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ComandosProdutos {
    Connection conexao;

    public boolean inserirBD(String produto, String categoria, float preco) {
        //Criação do comando a ser executado no banco de dados
        String comando = "INSERT INTO tb_produto" + "VALUES(null, ?, ?, ?);";

        //conexão do banco de dados
        ConectaMySql conecta = new ConectaMySql();
        conexao = conecta.iniciarConexao();

        //preparar a conexão com o comando que será executado nela
        try {
            PreparedStatement ps = conexao.prepareStatement(comando);

        //Passagem de valores para cada ? do comando INSERT
            ps.setString(1, produto);
            ps.setString(2, categoria);
            ps.setFloat(3, preco);

        //Execução do comando no banco de dados e teste do que foi retornado
            if (ps.executeUpdate() != 0) {
        //Se alterou pelo menos 1 linha, então o INSERT deu certo
            conexao.close();//Fecha a conexão e libera o recurso

                return true;
            }

        } catch (SQLException e) {

        }
        return false;
    }

    //Pequisar
    public Set<Produtos> pesquisaProdutos(String comando) {
        Set<Produtos> produtos = new HashSet<>();
        conexao = new ConectaMySql().iniciarConexao();

        try {
            PreparedStatement ps = conexao.prepareStatement(comando);
            ResultSet resultado = ps.executeQuery();
            /*System.out.println("Produto\tCategoria\tPreço");*/
            while (resultado.next()) {
                /*System.out.println(resultado.getString(1) + "\t" + resultado.getString(2) + "\t" + resultado.getFloat(3));
                System.out.println();*/
                produtos.add(new Produtos(resultado.getInt(1),resultado.getString(2),resultado.getString(3),resultado.getFloat(4)));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produtos;
    }

    //Deletar
    public boolean deletarProduto(String comandoSQL) {
        conexao = new ConectaMySql().iniciarConexao();
        try {
            PreparedStatement ps = conexao.prepareStatement(comandoSQL);
            if (ps.executeUpdate() != 0) {
                conexao.close();
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    //alterar
    public boolean alterarProdutos(String comandoSQL) {
        conexao = new ConectaMySql().iniciarConexao();

        try {
            PreparedStatement ps = conexao.prepareStatement(comandoSQL);
            if (ps.executeUpdate() != 0) {
                conexao.close();
                JOptionPane.showMessageDialog(null,"Item Alterado");
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
