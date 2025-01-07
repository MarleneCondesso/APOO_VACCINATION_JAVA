package vacinacaoCovid.Classes;

import java.util.ArrayList;

public class Centro extends Local {

    /**
     * Lista de Centros
     */
    public static ArrayList<Centro> listaCentros = new ArrayList<Centro>();

    /**
     * Construtor de Centro: inicialização
     */
    public Centro() {

    }

    /**
     * Construtor de Centro
     *
     * @param nome
     * @param morada
     * @param telf
     * @param tipoDeLocal
     * @param distrito
     */
    public Centro(String nome, String morada, int telf, char tipoDeLocal, String distrito) {
        super(nome, morada, telf, tipoDeLocal, distrito);
    }

    /**
     * Retorna todos os dados de Centro por Linha
     *
     * @param valor
     * @return
     */
    public static Centro retornaCentroPorLinha(String valor) {
        Centro c = new Centro();

        String[] data = valor.split("\\|");
        c.setTipoDeLocal(data[0].charAt(0));
        c.setNome(data[1]);
        c.setMorada(data[2]);
        c.setDistrito(data[3]);
        c.setTelf(Integer.parseInt(data[4]));

        return c;
    }

    /**
     * Método para retornar lista de Centros
     *
     * @return
     */
    public static ArrayList<Centro> listarCentros() {
        return Centro.listaCentros;
    }

    /**
     * Método para retornar Centro por nome
     *
     * @param nome
     * @return
     */
    public static Centro encontrarCentroPorNome(String nome) {
        Centro resultado = null;
        for (Centro cen : Centro.listarCentros()) {
            if (cen.getNome().toLowerCase().equals(nome.toLowerCase())) {
                resultado = cen;
            }
        }
        return resultado;
    }

    /**
     * Método para Escrever em ecrã cabeçalho de tabela de Centros
     */
    public static void tabelaCentrosHeader() {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("%-30s%-50s%-40s%-30s%-30s", "Tipo de Local", "Nome", "Morada", "Distrito", "Contacto");
        System.out.println("\n");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Método para Escrever em ecrã linha de utente de tabela de Centros
     */
    public void tabelaCentrosBody() {
        System.out.format("%-20s%-50s%-50s%-30s%-30s",
                Local.getDescricaoTipoLocal(this.getTipoDeLocal()),
                this.getNome(),
                this.getMorada(),
                this.getDistrito(),
                this.getTelf());
        System.out.println("\n");
    }

    /**
     * Método para Escrever em ecrã rodapé de tabela de Centros
     */
    public static void tabelaCentrosFooter(ArrayList<Centro> lista) {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(" Total: " + lista.size());
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

}
