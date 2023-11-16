package teste;

import comandos.ComandosProdutos;
import comandos.ComandosVenda;

import javax.swing.*;

public class TestaComandosVendas {
    public static void main(String[] args) {
        /*ComandosVenda venda = new ComandosVenda();
        //venda.inserirBD(4,"2023/10/08",8,15);
        venda.pesquisarVendas("SELECT * from tb_venda");*/

        //deletar
        /*ComandosVenda comando = new ComandosVenda();
        if (comando.deletarVendas("delete from tb_venda where where id_venda = 10;")) {
            JOptionPane.showMessageDialog(null,"Deletado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null,"Problema de integridade ou produto inexistente");
        }*/

        //alterar
        ComandosVenda comando = new ComandosVenda();
        if (comando.alterarVenda("UPDATE tb_venda set quantidade_prod = '23', data_venda ='", "12/03/2001","' ,id_cliente = '30' ," +
                "id_produto = '23' where id_venda = 37;")) {
            JOptionPane.showMessageDialog(null,"Venda alterada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Venda não alterada!");
        }
        comando.pesquisarVendas("select * from tb_venda;");
    }
}
