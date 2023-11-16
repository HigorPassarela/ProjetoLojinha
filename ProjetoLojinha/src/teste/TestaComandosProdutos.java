package teste;

import comandos.ComandosProdutos;

import javax.swing.*;

public class TestaComandosProdutos {
    public static void main(String[] args) {
        /*ComandosProdutos produto = new ComandosProdutos();
        //produto.inserirBD("Tilapia","Alimento",39.50F);
        produto.pesquisaProdutos("SELECT * from tb_produto");*/

        //deletar
        /*ComandosProdutos comando = new ComandosProdutos();
        if (comando.deletarProduto("delete from tb_produto where where id_produto = 10;")) {
            JOptionPane.showMessageDialog(null,"Deletado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null,"Problema de integridade ou produto inexistente");
        }*/

        //alterar
        ComandosProdutos produto = new ComandosProdutos();
        if (produto.alterarProdutos("UPDATE tb_produto SET nome_produto = 'Conhaque' , categoria_produto = 'Bebidas' WHERE id_produto=6;")) {
            JOptionPane.showMessageDialog(null,"Produto alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null,"Produto não alterado!");
        }
        produto.pesquisaProdutos("select * from tb_produto");
    }
}
