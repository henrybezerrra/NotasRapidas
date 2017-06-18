package dragonapp.com.br.easynotes.dao;

/**
 * Created by Murilo Henrique on 31/05/2017.
 */

public class Nota {

    //Declaração de varialeis privadas
    private int id;
    private String nome;
    private String descricao;

    /*
    Os métodos get e set são responsaveis pelo trafego de dados entre os objetos, são atravez
    deles que vc obtem ou atribui dados a um determinado Objeto de uma classe qualquer.
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    //Remover o diretorio de memoria na hora da apresentação na lista e passa a mostrar o nome da nota
    public String toString(){
        return getNome();
    }
}
