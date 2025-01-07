package vacinacaoCovid.Classes;

import java.util.ArrayList;

public class Pavilhao extends Local {

    /**
     * Secção
     */
    private int seccao;
    /**
     * Lista de Pavilhões
     */
    public static ArrayList<Pavilhao> listaPavilhoes = new ArrayList<Pavilhao>();

    /**
     * Construtor de Pavilhão: inicialização
     *
     * @param nome
     * @param morada
     * @param telf
     * @param tipoLocal
     * @param distrito
     */
    public Pavilhao(String nome, String morada, int telf, char tipoLocal, String distrito) {
        super(nome, morada, telf, tipoLocal, distrito);
    }

    /**
     * Construtor de Pavilhão: inicialização
     */
    public Pavilhao() {
        this.seccao = 0;
    }

    /**
     * Construtor de Pavilhão: com os parâmetros da PESSOA
     *
     * @param seccao
     * @param nome
     * @param morada
     * @param telf
     * @param tipoLocal
     * @param distrito
     */
    public Pavilhao(int seccao, String nome, String morada, int telf, char tipoLocal, String distrito) {
        super(nome, morada, telf, tipoLocal, distrito);
        this.seccao = seccao;
    }

    /**
     * Método para retornar Secção
     *
     * @return
     */
    protected int getSeccao() {
        return seccao;
    }

    /**
     * Método para atribuir Secção
     *
     * @param seccao
     */
    protected void setSeccao(int seccao) {
        this.seccao = seccao;
    }

    /**
     * Método para Retornar todos os dados de Pavilhão por Linha
     *
     * @param valor
     * @return
     */
    public static Pavilhao retornaPavilhaoPorLinha(String valor) {
        Pavilhao p = new Pavilhao();

        String[] data = valor.split("\\|");

        p.setTipoDeLocal(data[0].charAt(0));
        p.setNome(data[1]);
        p.setMorada(data[2]);
        p.setDistrito(data[3]);
        p.setTelf(Integer.parseInt(data[4]));
        p.setSeccao(Integer.parseInt(data[5]));

        return p;
    }

    /**
     * Método para retornar Pavilhão por nome
     *
     * @param nome
     * @return
     */
    public static Pavilhao encontrarPavilhaoPorNome(String nome) {
        Pavilhao resultado = null;
        for (Pavilhao pav : Pavilhao.listaPavilhoes) {
            if (pav.getNome().toLowerCase().equals(nome.toLowerCase())) {
                resultado = pav;
            }
        }
        return resultado;
    }

    /**
     * Método para Escrever em ecrã cabeçalho de tabela de Pavilhões
     */
    public static void tabelaPavilhoesHeader() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("%-30s%-50s%-50s%-30s%-30s%-30s", "Tipo de Local", "Nome", "Morada", "Distrito", "Contacto", "Lote");
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Método para Escrever em ecrã linha de utente de tabela de Pavilhões
     */
    public void tabelaPavilhoesBody() {
        System.out.format("%-30s%-50s%-50s%-30s%-30s%-30s",
                Local.getDescricaoTipoLocal(this.getTipoDeLocal()),
                this.getNome(),
                this.getMorada(),
                this.getDistrito(),
                this.getTelf(),
                this.getSeccao()
        );
        System.out.println("\n");
    }

    /**
     * Método para Escrever em ecrã rodapé de tabela de Pavilhões
     */
    public static void tabelaPavilhoesFooter(ArrayList<Pavilhao> lista) {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(" Total: " + lista.size());
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Método para retornar lista de Pavilhões
     *
     * @return
     */
    public static ArrayList<Pavilhao> listarPavilhoes() {
        return Pavilhao.listaPavilhoes;
    }

}
