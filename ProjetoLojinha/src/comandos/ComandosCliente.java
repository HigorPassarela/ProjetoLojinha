package comandos;

import conexao.ConectaMySql;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ComandosCliente {
    Connection conexao;
   public boolean inserirBD(String nome,String cpf, String data_nasc) {


        String comando = "INSERT INTO tb_cliente VALUES(null, ?, ?, ?);";
        ConectaMySql conecta = new ConectaMySql();
        conexao = conecta.iniciarConexao();

       try {
           PreparedStatement ps = conexao.prepareStatement(comando);
           //tratando a variavel data de nascimento
           java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd/MM/yyyy");
           java.util.Date data = null;
           data = format.parse(data_nasc);

           long millis = data.getTime();//pegando a data em milisegundos(tipo long)
           Date sqlDate = new Date(millis);//O construtor Date passa um valor long
           ps.setString(1, nome);
           ps.setString(2, cpf);
           ps.setDate(3, sqlDate);
           //Se alterou pelo menos 1 linha, então o INSERT deu certo
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
    /**************pesquisa**************/
    public void pesquisaCliente(String comando) {
        //conexão com o banco de dados
        conexao = new ConectaMySql().iniciarConexao();
        //guardando o comando sql
        try {
            PreparedStatement ps = conexao.prepareStatement(comando);
            //ResultSet
            ResultSet resultado = ps.executeQuery();//importa java.sql.ResultSet
            //Percorrer os valores que o SELECT buscou
            System.out.println("Codigo\tNome\tCPF\tData Nascimento");
            while (resultado.next()) { //Enquanto tiver linhas na tabela
                SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");//formato do sql
                SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
                String data = out.format(in.parse(resultado.getDate(4).toString()));
                System.out.println(resultado.getInt(1) + "\t" + resultado.getString(2) + "\t\t" + resultado.getString(3) + "\t"
                        + data);
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //deletar
    public boolean deletarCliente(String comandoSQL) {
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
    public boolean alterarCliente(String comandoSQL, String dataNorm, String comando) {
        conexao = new ConectaMySql().iniciarConexao();

        try {
            SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");//formato do BD
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
