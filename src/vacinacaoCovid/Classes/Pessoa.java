package vacinacaoCovid.Classes;

import java.util.ArrayList;

public class Pessoa {

    /**
     * Nome
     */
    private String nome;
    /**
     * Telemóvel
     */
    private int telm;
    /**
     * Lista de Pessoas
     */
    public static ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();

    /**
     * Construtor de Pessoa: inicialização
     */
    public Pessoa() {
        this.nome = "";
        this.telm = 0;
    }

    /**
     * Construtor de Pessoa
     *
     * @param nome
     * @param telm
     */
    public Pessoa(String nome, int telm) {
        this.nome = nome;
        this.telm = telm;
    }

    public Pessoa(Pessoa p) {
        this.nome = p.nome;
        this.telm = p.telm;
    }

    /**
     * Método para retornar Nome
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método para retornar Telemóvel
     *
     * @return
     */
    public int getTelm() {
        return telm;
    }

    /**
     * Método para atribuir Nome
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método para atribuir Telemóvel
     *
     * @param telm
     */
    public void setTelm(int telm) {
        this.telm = telm;
    }

    /**
     * Método para retornar lista com todas as Pessoas
     *
     * @return
     */
    public static ArrayList<Pessoa> listarPessoas() {

        listaPessoas.addAll(Enfermeiro.listarEnfermeiros());
        listaPessoas.addAll(Utente.listarUtentes());

        return Pessoa.listaPessoas;
    }

    /**
     * Método para Escrever em ecrã cabeçalho de tabela de Pessoas
     */
    public static void tabelaPessoasHeader() {
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.format("%-40s%-30s%-30s", "Nome", "Contacto", "Tipo");
        System.out.println("\n");
        System.out.println("------------------------------------------------------------------------------------------------");
    }

    /**
     * Método para Escrever em ecrã linha de marcação de tabela de Pessoas
     */
    public void tabelaPessoasBody() {
        System.out.format("%-40s%-30s%-30s", this.getNome(), this.getTelm(), this.getClass().getSimpleName());
        System.out.println("\n");
    }

    /**
     * Método para Escrever em ecrã rodapé de tabela de Pessoas
     */
    public static void tabelaPessoasFooter(ArrayList<Pessoa> lista) {
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println(" Total: " + lista.size());
        System.out.println("------------------------------------------------------------------------------------------------");
    }

}
