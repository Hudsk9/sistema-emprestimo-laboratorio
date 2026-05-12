package br.edu.ifpa.laboratorio.model;

public class Equipamento {
    private int id;
    private String nome;
    private boolean disponivel;

    public Equipamento(int id, String nome, boolean disponivel) {
        this.id = id;
        this.nome = nome;
        this.disponivel = disponivel;
    }

    public Equipamento() {} // Construtor vazio para o DAO

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
}