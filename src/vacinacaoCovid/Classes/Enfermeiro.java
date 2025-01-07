package vacinacaoCovid.Classes;

import java.io.IOException;
import java.util.ArrayList;
import vacinacaoCovid.Menus.MenuPessoas;

public class Enfermeiro extends Pessoa {

    /**
     * Cédula Profissional
     */
    private int cedula;
    /**
     * Lista de Enfermeiros
     */
    public static ArrayList<Enfermeiro> listaEnfermeiros = new ArrayList<Enfermeiro>();

    /**
     * Construtor de Enfermeiro: inicialização
     *
     * @param nome
     * @param telm
     */
    public Enfermeiro(String nome, int telm) {
        super(nome, telm);
    }

    /**
     * Construtor de Enfermeiro: inicialização
     */
    public Enfermeiro() {
        super();
        this.cedula = 0;
    }

    // Construtor de Enfermeiro: com os parâmetros da PESSOA
    /**
     *
     * @param celula
     * @param nome
     * @param telm
     */
    public Enfermeiro(int celula, String nome, int telm) {
        super(nome, telm);
        this.cedula = celula;
    }

    /**
     * Método para retornar Cédula Profissional
     *
     * @return
     */
    protected int getCedula() {
        return this.cedula;
    }

    /**
     * Método para atribuir Cédula Profissional
     *
     * @param cedula
     */
    protected void setCedula(int cedula) {
        this.cedula = cedula;
    }

    /**
     * Método para Retornar todos os dados de Enfermeiros por Linha
     *
     * @param valor
     * @return
     */
    public static Enfermeiro retornaEnfermeiroPorLinha(String valor) {
        Enfermeiro enf = new Enfermeiro();

        String[] data = valor.split("\\|");

        enf.setNome(data[0]);
        enf.setTelm(Integer.parseInt(data[1]));
        enf.setCedula(Integer.parseInt(data[2]));

        return enf;
    }

    /**
     * Método para retornar lista de enfermeiros
     *
     * @return
     */
    public static ArrayList<Enfermeiro> listarEnfermeiros() {
        return Enfermeiro.listaEnfermeiros;
    }

    /**
     * Método para Escrever em ecrã cabeçalho de tabela de Enfermeiros
     */
    public static void tabelaEnfermeirosHeader() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.format("%-40s%-30s%-30s", "Nome", "Contacto", "Cédula Profissional");
        System.out.println("\n");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Método para Escrever em ecrã linha de marcação de tabela de Enfermeiros
     */
    public void tabelaEnfermeirosBody() {
        System.out.format("%-40s%-30s%-30s", this.getNome(), this.getTelm(), this.getCedula());
        System.out.println("\n");
    }

    /**
     * Método para Escrever em ecrã rodapé de tabela de Enfermeiros
     */
    public static void tabelaEnfermeirosFooter(ArrayList<Enfermeiro> lista) {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println(" Total: " + lista.size());
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");

    }

    /**
     * Método para encontrar Enfermeiro pelo Nome
     *
     * @param nome
     * @return
     */
    public static Enfermeiro encontrarEnfermeiroPorNome(String nome) {
        Enfermeiro resultado = null;
        for (Enfermeiro enf : Enfermeiro.listarEnfermeiros()) {
            if (enf.getNome().toLowerCase().equals(nome.toLowerCase())) {
                resultado = enf;
            }
        }
        return resultado;
    }

    /**
     * Método para verificar se Enfermeiro existe, por nome
     *
     * @param nome
     * @return
     */
    public static Boolean enfermeiroExiste(String nome) {
        Boolean exists = false;

        for (Enfermeiro enf : Enfermeiro.listarEnfermeiros()) {
            if (enf.getNome().toLowerCase().equals(nome.toLowerCase())) {
                exists = true;
            }
        }

        if (!exists) {
            System.out.println("!!! O enfermeiro com o nome \"" + nome + "\" não existe !!!\n");
            System.out.println("Enfermeiros existentes: ");
            for (Marcacao marc : Marcacao.listarMarcacoes()) {
                System.out.println("    - " + marc.getEnfermeiro().getNome());
            }
        }

        return exists;
    }

    /**
     * Método para adicionar Enfermeiro
     *
     * @param nome
     * @param telm
     * @param cedula
     */
    public static void criarEnfermeiro(String nome, int telm, int cedula) {

        Enfermeiro enf = new Enfermeiro();

        enf.setNome(nome);
        enf.setTelm(telm);
        enf.setCedula(cedula);

        Enfermeiro.listaEnfermeiros.add(enf);
        try {
            FileParser.acrescentarInfoEmFicheiro("enf", construirLinhaParaExportar(enf), "");
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

        System.out.println("\n");
        System.out.println("..........................................................................");
        System.out.println("A adicionar Enfermeiro...");
        System.out.println("\n\n");
        System.out.println("Enfermeiro adicionado com sucesso !!");
        System.out.println("..........................................................................");
        System.out.println("\n\n");
        tabelaEnfermeirosHeader();
        enf.tabelaEnfermeirosBody();
        System.out.println("\n\n\n");

        MenuPessoas.menuPessoas();
    }

    /**
     * Método para construir linha para exportar para ficheiro
     *
     * @param enf
     * @return
     */
    public static String construirLinhaParaExportar(Enfermeiro enf) {

        String linha = enf.getNome()
                + "|"
                + enf.getTelm()
                + "|"
                + enf.getCedula();

        return linha;
    }
}
