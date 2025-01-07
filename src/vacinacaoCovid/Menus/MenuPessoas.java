package vacinacaoCovid.Menus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import vacinacaoCovid.VacinacaoCovid;
import vacinacaoCovid.Classes.Enfermeiro;
import vacinacaoCovid.Classes.Local;
import vacinacaoCovid.Classes.Marcacao;
import vacinacaoCovid.Classes.Pessoa;
import vacinacaoCovid.Classes.Utente;
import static vacinacaoCovid.Menus.MenuMarcacoes.menuMarcacoes;

public class MenuPessoas {

    private static Scanner scan = new Scanner(System.in, "ISO-8859-1");

    /**
     * Menu Pessoas
     */
    public static void menuPessoas() {

        int option = 0;

        System.out.println("********************************************************************************************************************************************************************************************************************************");
        System.out.println("                                                                                                      *** MENU PESSOAL ***");
        System.out.println("********************************************************************************************************************************************************************************************************************************");

        do {
            System.out.println("\nAções: ");
            System.out.println("    1) Lista de Pessoal");
            System.out.println("    2) Pesquisa por dia e por local");
            System.out.println("\n");
            System.out.println("    3) Adicionar Utente");
            System.out.println("    4) Adicionar Enfermeiro");
            System.out.println("    _____");
            System.out.println("    0) VOLTAR AO MENU PRINCIPAL");

            System.out.println("\n     Introduza a opção: ");
            option = scan.nextInt();
            System.out.println("\n");

            switch (option) {
                case 1:
                    listarPessoal();
                    break;
                case 2:
                    listarPessoalNumDiaELocal();
                    break;
                case 3:
                    adicionarUtente();
                    break;
                case 4:
                    adicionarEnfermeiro();
                    break;
                case 0:
                    VacinacaoCovid.mainMenu();
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }
        } while (option < 0 || option > 2);

    }

    /**
     * Menu Mais Ações
     */
    public static void maisAcoes() {
        int option = 0;

        do {
            System.out.println("\nAções: ");
            System.out.println("    1) Ver só Utentes");
            System.out.println("    2) Ver só Enfermeiros");
            System.out.println("    3) Pesquisa por dia e por local");
            System.out.println("    _____");
            System.out.println("    0) VOLTAR ATRAS");

            System.out.println("\n     Introduza a opção: ");
            option = scan.nextInt();
            System.out.println("\n");

            switch (option) {
                case 1:
                    listarUtentes();
                    break;
                case 2:
                    listarEnfermeiros();
                    break;
                case 3:
                    listarPessoalNumDiaELocal();
                    break;
                case 0:
                    menuPessoas();
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }
        } while (option < 0 || option > 3);
    }

    /**
     * Método para listar Pessoas em tabela
     */
    private static void listarPessoal() {

        ArrayList<Pessoa> pessoas = Pessoa.listarPessoas();

        Pessoa.tabelaPessoasHeader();
        for (Pessoa pes : pessoas) {
            pes.tabelaPessoasBody();
        }
        Pessoa.tabelaPessoasFooter(pessoas);

        maisAcoes();
    }

    /**
     * Método para listar Utentes em tabela
     */
    private static void listarUtentes() {

        ArrayList<Utente> utentes = Utente.listarUtentes();

        System.out.println("UTENTES: ");
        Utente.tabelaUtentesHeader();
        for (Utente ut : utentes) {
            ut.tabelaUtentesBody();
        }
        Utente.tabelaUtentesFooter(utentes);

        maisAcoes();
    }

    /**
     * Método para listar Enfermeiros em tabela
     */
    private static void listarEnfermeiros() {

        ArrayList<Enfermeiro> enfermeiros = Enfermeiro.listarEnfermeiros();

        System.out.println("ENFERMEIROS: ");
        Enfermeiro.tabelaEnfermeirosHeader();
        for (Enfermeiro ut : enfermeiros) {
            ut.tabelaEnfermeirosBody();
        }
        Enfermeiro.tabelaEnfermeirosFooter(enfermeiros);

        maisAcoes();
    }

    /**
     * Método Para Listar Pessoas num determinado dia e num local
     */
    private static void listarPessoalNumDiaELocal() {

        ArrayList<Pessoa> resultadoDataLocal = new ArrayList<Pessoa>();

        String local = "";
        String data = "";
        LocalDate dia = null;

        // DATA
        do {
            System.out.println("Indique o dia, no formato AAAA-MM-DD :");
            data = scan.next();

        } while (!VacinacaoCovid.dataValida(data));
        dia = VacinacaoCovid.dataStringParaLocalDate(data);

        // LOCAL
        do {
            System.out.println("Insira um Local de Vacinação:");
            local = scan.next();
            local += scan.nextLine();

            System.out.println("\n");
        } while (!Local.localExiste(local));

        resultadoDataLocal = Marcacao.encontrarPessoasPorDataLocal(dia, local);

        System.out.println("DIA: " + dia + " | LOCAL: " + local);
        if (!resultadoDataLocal.isEmpty()) {

            Pessoa.tabelaPessoasHeader();
            for (Pessoa pessoa : resultadoDataLocal) {
                pessoa.tabelaPessoasBody();
            }
            Pessoa.tabelaPessoasFooter(resultadoDataLocal);

        } else {
            System.out.println("Sem resultados no dia " + dia + " e no local " + local + "...");
        }

        menuPessoas();
    }

    /**
     * Método para adicionar Utente
     */
    private static void adicionarUtente() {

        System.out.println("******************************************************************************************");
        System.out.println("ADICIONAR UTENTE ...");
        System.out.println("\n");

        String nome = "";
        char genero = 0;
        int telm = 0;
        int numUtente = 0;
        String dataNasc = "";
        LocalDate dataNascObj = null;

        // NOME
        System.out.println("Indique qual o nome do Utente:");
        nome = scan.next();
        nome += scan.nextLine();
        System.out.println("\n");

        // GÉNERO
        do {
            System.out.println("Indique qual o género do Utente:");
            System.out.println("( \" M \" para masculino   OU   \" F \" para feminino )");
            genero = scan.next().charAt(0);

            System.out.println("\n");
        } while (!Character.toString(genero).toUpperCase().matches("F|M"));

        // TELEMÓVEL
        System.out.println("Indique qual o número de telemóvel do Utente:");
        telm = scan.nextInt();
        System.out.println("\n");

        // NÚMERO DE UTENTE
        System.out.println("Indique qual o número do Utente:");
        numUtente = scan.nextInt();
        System.out.println("\n");

        // DATA
        do {
            System.out.println("Indique qual a data de nascimento do Utente, no formato AAAA-MM-DD :");
            dataNasc = scan.next();
            System.out.println("\n");

        } while (!VacinacaoCovid.dataValida(dataNasc));
        dataNascObj = VacinacaoCovid.dataStringParaLocalDate(dataNasc);

        // FINALIZAÇÃO DA CRIAÇÃO
        Boolean acao = VacinacaoCovid.confirmaAcao("ADICIONAR este Utente");

        if (acao) {
            Utente.criarUtente(nome, genero, telm, numUtente, dataNascObj);
        } else {
            menuMarcacoes();
        }
    }

    /**
     * Método para adicionar Enfermeiro
     */
    private static void adicionarEnfermeiro() {

        System.out.println("******************************************************************************************");
        System.out.println("ADICIONAR Enfermeiro ...");
        System.out.println("\n");

        String nome = "";
        int telm = 0;
        int cedula = 0;

        // NOME
        System.out.println("Indique qual o nome do Enfermeiro:");
        nome = scan.next();
        nome += scan.nextLine();
        System.out.println("\n");

        // TELEMÓVEL
        System.out.println("Indique qual o número de telemóvel do Utente:");
        telm = scan.nextInt();
        System.out.println("\n");

        // CÉDULA PROFISSIONAL
        System.out.println("Indique qual a Cédula Profissional do Enfermeiro:");
        cedula = scan.nextInt();
        System.out.println("\n");

        // FINALIZAÇÃO DA CRIAÇÃO
        Boolean acao = VacinacaoCovid.confirmaAcao("ADICIONAR este Enfermeiro");

        if (acao) {
            Enfermeiro.criarEnfermeiro(nome, telm, cedula);
        } else {
            menuMarcacoes();
        }

    }

}
