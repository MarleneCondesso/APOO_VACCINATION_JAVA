package vacinacaoCovid.Classes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import vacinacaoCovid.VacinacaoCovid;
import vacinacaoCovid.Menus.MenuPessoas;

public class Utente extends Pessoa {

    /**
     * Género
     */
    private String genero;
    /**
     * Data de Nascimento
     */
    private LocalDate dataNasc;
    /**
     * Número de Utente
     */
    private int numUtente;
    /**
     * Lista de Utentes
     */
    public static ArrayList<Utente> listaUtentes = new ArrayList<Utente>();

    /**
     * Construtor de Utente: inicialização
     *
     * @param nome
     * @param telm
     */
    public Utente(String nome, int telm) {
        super(nome, telm);
    }

    /**
     * Construtor de Utente: inicialização
     */
    public Utente() {
        super();
        this.dataNasc = null;
        this.genero = "";
        this.numUtente = 0;

    }

    /**
     * Construtor de Utente: com os parâmetros da PESSOA
     *
     * @param genero
     * @param dataNasc
     * @param numUtente
     * @param nome
     * @param telm
     */
    public Utente(String genero, LocalDate dataNasc, int numUtente, String nome, int telm) {
        super(nome, telm);
        this.genero = genero;
        this.dataNasc = dataNasc;
        this.numUtente = numUtente;
    }

    /**
     * Construtor de Utente
     *
     * @param u
     */
    public Utente(Utente u) {
        this.genero = u.genero;
        this.dataNasc = u.dataNasc;
        this.numUtente = u.numUtente;
    }

    /**
     * Método para retornar Género
     *
     * @return
     */
    protected String getGenero() {
        return this.genero;
    }

    /**
     * Método para retornar Data de Nascimento
     *
     * @return
     */
    protected LocalDate getDataNasc() {
        return this.dataNasc;
    }

    /**
     * Método para retornar Número de Utente
     *
     * @return
     */
    protected int getNumUtente() {
        return this.numUtente;
    }

    /**
     * Método para atribuir Género
     *
     * @param genero
     */
    protected void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Método para atribuir Data de Nascimento
     *
     * @param dataNasc
     */
    protected void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    /**
     * Método para atribuir Número de Utente
     *
     * @param numUtente
     */
    protected void setNumUtente(int numUtente) {
        this.numUtente = numUtente;
    }

    /**
     * Método para Retornar todos os dados de Utente por Linha
     *
     * @param valor
     * @return
     */
    public static Utente retornaUtentePorLinha(String valor) {
        Utente u = new Utente();

        String[] data = valor.split("\\|");

        u.setNome(data[0]);
        u.setTelm(Integer.parseInt(data[1]));
        u.setGenero(data[2]);
        u.setDataNasc(VacinacaoCovid.dataStringParaLocalDate(data[3]));
        u.setNumUtente(Integer.parseInt(data[4]));

        return u;
    }

    /**
     * Método para retornar lista de utentes
     *
     * @return
     */
    public static ArrayList<Utente> listarUtentes() {
        return Utente.listaUtentes;
    }

    /**
     * Método para Escrever em ecrã cabeçalho de tabela de Utentes
     */
    public static void tabelaUtentesHeader() {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("%-40s%-30s%-30s%-30s%-30s", "Nome", "Contacto", "Género", "Data Nascimento", "Num. Utente");
        System.out.println("\n");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Método para Escrever em ecrã linha de utente de tabela de Utentes
     */
    public void tabelaUtentesBody() {
        System.out.format("%-40s%-30s%-30s%-30s%-30s",
                this.getNome(),
                this.getTelm(),
                this.genero,
                this.dataNasc + " (" + VacinacaoCovid.calcularIdade(this.dataNasc) + " anos)",
                this.getNumUtente()
        );
        System.out.println("\n");
    }

    /**
     * Método para Escrever em ecrã rodapé de tabela de Utentes
     */
    public static void tabelaUtentesFooter(ArrayList<Utente> lista) {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(" Total: " + lista.size());
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Método para saber se Utente existe, por nome
     *
     * @param nome
     * @return
     */
    public static Boolean utenteExiste(String nome) {
        Boolean exists = false;

        for (Utente ut : Utente.listarUtentes()) {
            if (ut.getNome().toLowerCase().equals(nome.toLowerCase())) {
                exists = true;
            }
        }

        if (!exists) {
            System.out.println("!!! O utente com o nome \"" + nome + "\" não existe !!!\n");
            System.out.println("Utentes com marcação: ");
            for (Marcacao marc : Marcacao.listaMarcacoes) {
                System.out.println("    - " + marc.getUtente().getNome());
            }
        }

        return exists;
    }

    /**
     * Método para retornar Utente por nome
     *
     * @param nome
     * @return
     */
    public static Utente encontrarUtentePorNome(String nome) {
        Utente resultado = null;
        for (Utente ut : Utente.listarUtentes()) {
            if (ut.getNome().toLowerCase().equals(nome.toLowerCase())) {
                resultado = ut;
            }
        }
        return resultado;
    }

    /**
     * Método para adicionar Utente
     *
     * @param nome
     * @param genero
     * @param telm
     * @param numUtente
     * @param dataNasc
     */
    public static void criarUtente(String nome, char genero, int telm, int numUtente, LocalDate dataNasc) {

        Utente ut = new Utente();
        
        String generoString = "";
        if (Character.toString(genero).toUpperCase().matches("F")) {
            generoString = "Feminino";
        } else if (Character.toString(genero).toUpperCase().matches("M")) {
            generoString = "Masculino";
        }
        ut.setGenero(generoString);
        ut.setNome(nome);
        ut.setTelm(telm);
        ut.setNumUtente(numUtente);
        ut.setDataNasc(dataNasc);

        Utente.listaUtentes.add(ut);
        try {
            FileParser.acrescentarInfoEmFicheiro("ute", construirLinhaParaExportar(ut), "");
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

        System.out.println("\n");
        System.out.println("..........................................................................");
        System.out.println("A adicionar Utente...");
        System.out.println("\n\n");
        System.out.println("Utente adicionado com sucesso !!");
        System.out.println("..........................................................................");
        System.out.println("\n\n");
        tabelaUtentesHeader();
        ut.tabelaUtentesBody();
        System.out.println("\n\n\n");

        MenuPessoas.menuPessoas();
    }

    /**
     * Método para construir linha para exportar para ficheiro
     *
     * @param ut
     * @return
     */
    public static String construirLinhaParaExportar(Utente ut) {

        String linha = ut.getNome()
                + "|"
                + ut.getTelm()
                + "|"
                + ut.getGenero()
                + "|"
                + ut.getDataNasc()
                + "|"
                + ut.getNumUtente();

        return linha;
    }
}
