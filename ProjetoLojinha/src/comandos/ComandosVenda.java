package comandos;

import conexao.ConectaMySql;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ComandosVenda {
    Connection conexao;
    public boolean inserirBD(int quantidade_prod, String data_venda, int id_cliente, int id_produto) {

        String comando = "INSERT INTO tb_venda VALUES(null, ?, ?, ?, ?);";
        ConectaMySql conecta = new ConectaMySql();
        conexao = conecta.iniciarConexao();

        try {
            PreparedStatement ps = conexao.prepareStatement(comando);
            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd/MM/yyyy");
            java.util.Date data = null;
            data = format.parse(data_venda);

            long millis = data.getTime();
            Date sqlDate = new Date(millis);
            ps.setInt(1, quantidade_prod);
            ps.setDate(2, sqlDate);
            ps.setInt(3, id_cliente);
            ps.setInt(4, id_produto);
            if (ps.executeUpdate() != 0) {
                conexao.close();
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return false;
    }

    public void pesquisarVendas(String comando) {
        conexao = new ConectaMySql().iniciarConexao();

        try {
            PreparedStatement ps = conexao.prepareStatement(comando);
            ResultSet resultado = ps.executeQuery();
            System.out.println("Quantidade\tData Venda\tId Cliente\tId Produto");
            while (resultado.next()) {
                System.out.println(resultado.getInt(1) + "\t" + resultado.getString(2) + "\t" + resultado.getInt(3) + "\t" +
                        resultado.getInt(4));
                System.out.println();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //deletar
    public boolean deletarVendas(String comandoSQL) {
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
    public boolean alterarVenda(String comandoSQL, String dataNorm, String comando) {
        conexao = new ConectaMySql().iniciarConexao();

        try {
            SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat in = new SimpleDateFormat("dd/MM/yyyy");
            String data = out.format(in.parse(dataNorm));
            comandoSQL = comandoSQL + data + comando;

            PreparedStatement ps = conexao.prepareStatement(comandoSQL);
            if (ps.executeUpdate() != 0) {
                conexao.close();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
