package teste;

import comandos.ComandosCliente;

import javax.swing.*;

public class TestaComandosCliente {
    public static void main(String[] args) {
        /*ComandosCliente cliente = new ComandosCliente();
        //cliente.inserirBD("Higor","782146212","04/11/2005");
        cliente.pesquisaCliente("SELECT * from tb_cliente");*/

        //deletar
        /*ComandosCliente comando = new ComandosCliente();
        if (comando.deletarCliente("delete from tb_cliente where where id_cliente = 10;")) {
            JOptionPane.showMessageDialog(null,"Deletado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null,"Problema de integridade ou produto inexistente");
        }*/

        //alterar
        ComandosCliente clientes = new ComandosCliente();
        if (clientes.alterarCliente("UPDATE tb_cliente SET nome_cliente = 'Marinalva' , cpf_cliente = '111.111.111-11' , data_nasc = '" ,
                "12/03/2001", "' WHERE id_cliente = 64;")) {
            JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não alterado!");
        }
        clientes.pesquisaCliente("select * from tb_cliente;");
    }
}
