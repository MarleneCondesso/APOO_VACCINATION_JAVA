package vacinacaoCovid.Classes;

import java.util.ArrayList;

public class Hospital extends Local {

    /**
     * Nome do Edifício
     */
    private String nomeEdificio;
    /**
     * Extensão Telefónica
     */
    private int extencaoTelf;
    /**
     * Lista de Hospitais
     */
    public static ArrayList<Hospital> listaHospitais = new ArrayList<Hospital>();

    /**
     * Construtor de Hospital: inicialização
     */
    public Hospital() {
        this.extencaoTelf = 0;
        this.nomeEdificio = "";
    }

    /**
     * Construtor de Hospital: com os parâmetros da PESSOA
     *
     * @param nome
     * @param morada
     * @param telf
     * @param tipoDeLocal
     * @param distrito
     */
    public Hospital(String nome, String morada, int telf, char tipoDeLocal, String distrito) {
        super(nome, morada, telf, tipoDeLocal, distrito);
    }

    /**
     * Construtor de Hospital: com os parâmetros da PESSOA
     *
     * @param nomeEdificio
     * @param extencaoTelf
     * @param nome
     * @param morada
     * @param telf
     * @param tipoDeLocal
     * @param distrito
     */
    public Hospital(String nomeEdificio, int extencaoTelf, String nome, String morada, int telf, char tipoDeLocal, String distrito) {
        super(nome, morada, telf, tipoDeLocal, distrito);
        this.nomeEdificio = nomeEdificio;
        this.extencaoTelf = extencaoTelf;
    }

    /**
     * Construtor de Hospital
     *
     * @param hosp
     */
    public Hospital(Hospital hosp) {
        super(hosp.nome, hosp.morada, hosp.telf, hosp.tipoDeLocal, hosp.distrito);
        this.nomeEdificio = hosp.nomeEdificio;
        this.extencaoTelf = hosp.extencaoTelf;
    }

    /**
     * Método para retornar Nome do Edifício
     *
     * @return
     */
    public String getNomeEdificio() {
        return nomeEdificio;
    }

    /**
     * Método para retornar Extensão Telefónica
     *
     * @return
     */
    public int getExtencaoTelf() {
        return extencaoTelf;
    }

    /**
     * Método para atribuir Nome de Edifício
     *
     * @param nomeEdificio
     */
    public void setNomeEdificio(String nomeEdificio) {
        this.nomeEdificio = nomeEdificio;
    }

    /**
     * Método para atribuir Extensão Telefónica
     *
     * @param extencaoTelf
     */
    public void setExtencaoTelf(int extencaoTelf) {
        this.extencaoTelf = extencaoTelf;
    }

    /**
     * Retorna todos os dados de Hospital por Linha
     *
     * @param valor
     * @return
     */
    public static Hospital retornaHospitalPorLinha(String valor) {
        Hospital hosp = new Hospital();

        String[] data = valor.split("\\|");

        hosp.setTipoDeLocal(data[0].charAt(0));
        hosp.setNome(data[1]);
        hosp.setMorada(data[2]);
        hosp.setDistrito(data[3]);
        hosp.setTelf(Integer.parseInt(data[4]));
        hosp.setNomeEdificio(data[5]);
        hosp.setExtencaoTelf(Integer.parseInt(data[6]));

        return hosp;
    }

    /**
     * Método para retornar lista de Hospitais
     *
     * @return
     */
    public static ArrayList<Hospital> listarHospitais() {
        return Hospital.listaHospitais;
    }

    /**
     * Método para retornar Hospital por nome
     *
     * @param nome
     * @return
     */
    public static Hospital encontrarHospitalPorNome(String nome) {
        Hospital resultado = null;
        for (Hospital hosp : Hospital.listarHospitais()) {
            if (hosp.getNome().toLowerCase().equals(nome.toLowerCase())) {
                resultado = hosp;
            }
        }
        return resultado;
    }

    /**
     * Método para Escrever em ecrã cabeçalho de tabela de Hospitais
     */
    public static void tabelaHospitaisHeader() {
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("%-30s%-30s%-30s%-50s%-30s%-30s", "Tipo de Local", "Nome", "Nome de Edifício", "Morada", "Contacto", "Extenção Telefónica");
        System.out.println("\n");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Método para Escrever em ecrã linha de utente de tabela de Hospitais
     */
    public void tabelaHospitaisBody() {
        System.out.format("%-30s%-30s%-30s%-50s%-30s%-30s",
                Local.getDescricaoTipoLocal(this.getTipoDeLocal()),
                this.getNome(),
                this.getNomeEdificio(),
                this.getMorada(),
                this.getTelf(),
                this.getExtencaoTelf()
        );
        System.out.println("\n");
    }

    /**
     * Método para Escrever em ecrã rodapé de tabela de Hospitais
     */
    public static void tabelaHospitaisFooter(ArrayList<Hospital> lista) {
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(" Total: " + lista.size());
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

}
