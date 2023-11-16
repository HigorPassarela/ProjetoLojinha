package paineis;

import classes.Produtos;
import comandos.ComandosProdutos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class PainelMostrar extends JPanel {
    private JButton jbCliente, jbProduto, jbVenda;
    private JTextArea jtaMostrar;
    private JScrollPane jspMostrar;


    public PainelMostrar() {
        setLayout(null);
        setBackground(Color.CYAN);
        iniciarComponentes();
        criarEventos();

    }

    private void iniciarComponentes() {

        jbCliente = new JButton("Cliente");
        jbProduto = new JButton("Produto");
        jbVenda = new JButton("Venda");
        jtaMostrar = new JTextArea();
        jspMostrar = new JScrollPane(jtaMostrar);

        add(jbCliente);
        add(jbProduto);
        add(jbVenda);
        add(jspMostrar);

        jbCliente.setBounds(20, 20, 100, 20);
        jbProduto.setBounds(140, 20, 100, 20);
        jbVenda.setBounds(260, 20, 100, 20);
        jspMostrar.setBounds(20, 60, 340, 250);


    }

    private void criarEventos() {
        //Evento botão produto
        jbProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComandosProdutos comando = new ComandosProdutos();
                Set<Produtos> produtos = comando.pesquisaProdutos("Select * from tb_produto;");
                jtaMostrar.setText("      ***********PRODUTO***********      ");
                for (Produtos produto:produtos) {
                    jtaMostrar.append(produto.mostrarDados());
                }
            }
        });
    }
}
