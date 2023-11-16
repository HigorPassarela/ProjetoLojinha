package classes;

public class Produtos {
    protected int codigo;
    protected String nome;
    protected String categoria;
    protected double preco;

    //Construtor
    public Produtos(int codigo, String nome, String categoria, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
    }

    //
    public String mostrarDados(){
        return "\n" + this.codigo + "\t" + this.nome + "\t" + this.categoria + "\t" + this.preco;
    }
}
