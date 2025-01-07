package vacinacaoCovid.Menus;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import vacinacaoCovid.VacinacaoCovid;
import vacinacaoCovid.Classes.Enfermeiro;
import vacinacaoCovid.Classes.FileParser;
import vacinacaoCovid.Classes.Local;
import vacinacaoCovid.Classes.Marcacao;
import static vacinacaoCovid.Classes.Marcacao.construirLinhaParaExportar;
import vacinacaoCovid.Classes.Utente;
import vacinacaoCovid.Classes.Vacina;

public class MenuMarcacoes {

    private static Scanner scan = new Scanner(System.in, "ISO-8859-1");

    /**
     * Menu Marcações
     */
    public static void menuMarcacoes() {
        int option = 0;

        System.out.println("********************************************************************************************************************************************************************************************************************************");
        System.out.println("                                                                                                    *** MARCAÇÕES DE VACINAÇÃO ***");
        System.out.println("********************************************************************************************************************************************************************************************************************************");

        do {
            System.out.println("\nAções: ");
            System.out.println("    1) Lista de Todas as Marcações");
            System.out.println("\n");
            System.out.println("    2) Gestão de Ficheiros: Lista de Marcações por Zona: ");
            System.out.println("    3) Gestão de Ficheiros: Criar Nova Zona");
            System.out.println("    4) PESQUISAR");
            System.out.println("\n");
            System.out.println("    5) Adicionar Marcação");
            System.out.println("    6) Editar Marcação");
            System.out.println("    7) Eliminar Marcação");
            // System.out.println("    8) Gravar marcações existentes em ficheiro");
            System.out.println("    _____");
            System.out.println("    0) VOLTAR AO MENU PRINCIPAL");

            System.out.println("\n     Introduza a opção: ");
            option = scan.nextInt();

            switch (option) {
                case 1:
                    listarTodasMarcacoes();
                    break;
                case 2:
                    listarMarcacoesPorZona();
                    break;
                case 3:
                    criarNovaZona();
                    break;
                case 4:
                    pesquisarMarcacoes();
                    break;
                case 5:
                    // ADICIONAR
                    adicionarMarcacao();
                    break;
                case 6:
                    // EDITAR;
                    editarMarcacao();
                    break;
                case 7:
                    // ELIMINAR
                    eliminarMarcacao();
                    break;
//                case 8:
//                    // GRAVAR EM FICHEIRO
//                    exportarMarcacoesEmFicheiro();
//                    break;
                case 0:
                    VacinacaoCovid.mainMenu();
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }
        } while (option < 0 || option > 7);

    }

    /**
     * Menu Mais Ações
     */
    public static void maisAcoes() {
        int option = 0;

        do {
            System.out.println("\nAções: ");
            System.out.println("    1) Adicionar Marcação");
            System.out.println("    2) Editar Marcação");
            System.out.println("    3) Eliminar Marcação");
//            System.out.println("    4) Gravar marcações existentes em ficheiro");
            System.out.println("    _____");
            System.out.println("    0) VOLTAR ATRAS");

            System.out.println("\n     Introduza a opção: ");
            option = scan.nextInt();
            System.out.println("\n");

            switch (option) {
                case 1:
                    // ADICIONAR
                    adicionarMarcacao();
                    break;
                case 2:
                    // EDITAR;
                    editarMarcacao();
                    break;
                case 3:
                    // ELIMINAR
                    eliminarMarcacao();
                    break;
//                case 4:
//                    // GRAVAR EM FICHEIRO
//                    exportarMarcacoesEmFicheiro();
//                    break;
                case 0:
                    menuMarcacoes();
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }
        } while (option < 0 || option > 3);
    }

    /**
     * Tabela de opções de pesquisa de Marcações
     */
    private static void pesquisarMarcacoes() {
        int option = 0;

        System.out.println("\n");
        System.out.println("********************************************************************************************************************************************************************************************************************************");
        System.out.println("                                                                                                          *** PESQUISA DE MARCAÇÕES ***");
        System.out.println("********************************************************************************************************************************************************************************************************************************");

        do {
            System.out.println("\nPesquisar por... ");
            System.out.println("    1) Nome de Utente");
            System.out.println("    2) Intervalo de Idades");
            System.out.println("    3) Marca e Local da Vacina");
            System.out.println("    4) Dia de Marcação");
            System.out.println("    5) Local de Marcação");
            System.out.println("    6) Distrito");
            System.out.println("\n");
            System.out.println("    _____");
            System.out.println("    0) VOLTAR ATRAS");

            System.out.println("\n     Introduza a opção: ");
            option = scan.nextInt();

            switch (option) {
                case 1:
                    pesquisarPorUtente();
                    break;
                case 2:
                    pesquisarPorIntervaloIdades();
                    break;
                case 3:
                    pesquisarPorMarcaLoteVacina();
                    break;
                case 4:
                    pesquisarPorDia();
                    break;
                case 5:
                    pesquisarPorLocal();
                    break;
                case 6:
                    pesquisarMarcacoesPorDistrito();
                case 0:
                    menuMarcacoes();
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }
        } while (option < 0 || option > 5);

    }

    /**
     * Método para criar marcação
     */
    private static void adicionarMarcacao() {

        System.out.println("******************************************************************************************");
        System.out.println("CRIAR MARCAÇÃO ...");
        System.out.println("\n");

        String nomeUtente = "";
        String nomeEnf = "";
        char tipoLocal = 0;
        String nomeLocal = "";
        String marcaVacina = "";
        String loteVacina = "";
        String data = "";
        LocalDate dataObj = null;
        String hora = "";
        LocalTime horaObj = null;
        String zona = "";

        // UTENTE
        do {
            System.out.println("Indique qual o nome do Utente:");
            nomeUtente = scan.next();
            nomeUtente += scan.nextLine();
            System.out.println("\n");

        } while (!Utente.utenteExiste(nomeUtente));
        Utente ut = Utente.encontrarUtentePorNome(nomeUtente);

        // ENFERMEIRO
        do {
            System.out.println("Indique qual o nome do Enfermeiro:");
            nomeEnf = scan.next();
            nomeEnf += scan.nextLine();
            System.out.println("\n");

        } while (!Enfermeiro.enfermeiroExiste(nomeEnf));
        Enfermeiro enf = Enfermeiro.encontrarEnfermeiroPorNome(nomeEnf);

        // VACINA
        do {
            System.out.println("Indique qual a marca da Vacina:");
            marcaVacina = scan.next();
            marcaVacina += scan.nextLine();

            System.out.println("\n");
        } while (!Vacina.marcaVacinaExiste(marcaVacina));
        do {
            System.out.println("Indique qual a lote da Vacina:");
            loteVacina = scan.next();
            loteVacina += scan.nextLine();

            System.out.println("\n");
        } while (!Vacina.marcaLoteVacinaExiste(marcaVacina, loteVacina));
        Vacina vac = Vacina.encontrarVacinaPorMarcaLote(marcaVacina, loteVacina);

        // LOCAL
        do {
            System.out.println("Indique qual o Local de Vacinação:");
            nomeLocal = scan.next();
            nomeLocal += scan.nextLine();

            System.out.println("\n");
        } while (!Local.localExiste(nomeLocal));
        tipoLocal = Local.encontrarLocalPorNome(nomeLocal).getTipoDeLocal();

        // ZONA
        do {
            System.out.println("Indique qual a Zona da Marcação:");
            zona = scan.next();
            zona += scan.nextLine();
            System.out.println("\n");

        } while (zona == "");

        // DATA
        do {
            System.out.println("Indique qual o Dia, no formato AAAA-MM-DD :");
            data = scan.next();
            System.out.println("\n");

        } while (!VacinacaoCovid.dataValida(data));
        dataObj = VacinacaoCovid.dataStringParaLocalDate(data);

        // HORA
        do {
            System.out.println("Indique a nova hora,no formato HH:MM :");
            hora = scan.next();
            System.out.println("\n");

            horaObj = VacinacaoCovid.horaStringParaLocalTime(hora);
        } while (!VacinacaoCovid.horaValida(hora));

        // FINALIZAÇÃO DA CRIAÇÃO
        Boolean acao = VacinacaoCovid.confirmaAcao("CRIAR esta marcação");

        if (acao) {
            Marcacao.criarMarcacao(ut, enf, vac, tipoLocal, nomeLocal, dataObj, horaObj, zona);
        } else {
            menuMarcacoes();
        }
    }

    /**
     * Método para editar marcação: enfermeiro, local, data e hora
     */
    private static void editarMarcacao() {

        System.out.println("******************************************************************************************");
        System.out.println("EDITAR MARCAÇÃO ...");
        System.out.println("\n");

        Marcacao marc = null;
        String nomeUtente = "";
        char tipoLocal = 0;
        String nomeLocal = "";
        String data = "";
        LocalDate dataObj = null;
        String hora = "";
        LocalTime horaObj = null;
        String nomeEnf = "";
        String zona = "";

        // UTENTE
        do {
            System.out.println("Indique qual o utente da marcação que quer alterar:");
            nomeUtente = scan.next();
            nomeUtente += scan.nextLine();
            System.out.println("\n");

        } while (!Utente.utenteExiste(nomeUtente));

        // MARCAÇÃO
        marc = Marcacao.encontrarMarcacaoPorUtente(nomeUtente);

        System.out.println("\n\n");
        System.out.println("*** Marcação a alterar: ***");
        Marcacao.tabelaMarcacoesHeader();
        marc.tabelaMarcacoesBody();
        System.out.println("\n");

        // LOCAL
        do {
            System.out.println("Indique qual o novo Local de Vacinação:");
            nomeLocal = scan.next();
            nomeLocal += scan.nextLine();

            System.out.println("\n");
        } while (!Local.localExiste(nomeLocal));
        tipoLocal = Local.encontrarLocalPorNome(nomeLocal).getTipoDeLocal();

        // ENFERMEIRO
        do {
            System.out.println("Indique qual o nome do enfermeiro que quer alterar:");
            nomeEnf = scan.next();
            nomeEnf += scan.nextLine();
            System.out.println("\n");
        } while (!Enfermeiro.enfermeiroExiste(nomeEnf));
        Enfermeiro enf = Enfermeiro.encontrarEnfermeiroPorNome(nomeEnf);

        // DATA
        do {
            System.out.println("Indique qual o novo dia, no formato AAAA-MM-DD :");
            data = scan.next();
            System.out.println("\n");

            dataObj = VacinacaoCovid.dataStringParaLocalDate(data);
        } while (dataObj.isEqual(marc.getData()));

        // HORA
        do {
            System.out.println("Indique a nobva hora,no formato HH:MM :");
            hora = scan.next();
            System.out.println("\n");

            horaObj = VacinacaoCovid.horaStringParaLocalTime(hora);
        } while (!VacinacaoCovid.horaValida(hora) || (horaObj.equals(marc.getHora()) && marc.getData().isEqual(dataObj)));

        // ZONA
        do {
            System.out.println("Indique qual a Zona da Marcação:");
            zona = scan.next();
            zona += scan.nextLine();
            System.out.println("\n");

        } while (zona == "");

        // FINALIZAÇÃO DA EDIÇÃO
        Boolean acao = VacinacaoCovid.confirmaAcao("ALTERAR esta marcação");

        if (acao) {
            Marcacao.editarMarcacao(marc, dataObj, horaObj, tipoLocal, nomeLocal, enf, zona);
        } else {
            menuMarcacoes();
        }
    }

    /**
     * Método para eliminar marcação
     */
    private static void eliminarMarcacao() {

        System.out.println("******************************************************************************************");
        System.out.println("ELIMINAR MARCAÇÃO ...");
        System.out.println("\n");

        String nome = "";
        do {

            System.out.println("Indique o utente da marcação:");
            nome = scan.next();
            nome += scan.nextLine();

        } while (!Utente.utenteExiste(nome));

        Marcacao marc = Marcacao.encontrarMarcacaoPorUtente(nome);
        System.out.println("\n\n");
        System.out.println("MARCAÇÃO A ELIMINAR:");
        Marcacao.tabelaMarcacoesHeader();
        marc.tabelaMarcacoesBody();
        System.out.println("\n");

        Boolean action = VacinacaoCovid.confirmaAcao("ELIMINAR esta marcação");

        if (action) {
            Marcacao.eliminarMarcacao(marc);
        }

        menuMarcacoes();
    }

    /**
     * Método para Exportar para Ficheiro as Marcações
     */
    private static void exportarMarcacoesEmFicheiro() {

        Boolean action = VacinacaoCovid.confirmaAcao("EXPORTAR marcações para ficheiro");

        if (action) {

            ArrayList<String> listaParaExportar = new ArrayList<String>();

            for (Marcacao item : Marcacao.listaMarcacoes) {
                listaParaExportar.add(construirLinhaParaExportar(item));
            }

            try {
                FileParser.exportarInfoParaFicheiro("mar", listaParaExportar, "");
            } catch (IOException ex) {
                System.out.println("Erro: " + ex);
            }

        }

        menuMarcacoes();
    }

    /**
     * Método para listar a Marcação a partir do nome de Utente inserido
     */
    private static void pesquisarPorUtente() {

        String nome = "";
        Marcacao resultadoNome;

        do {
            System.out.println("Insira o nome do Utente: ");
            nome = scan.next();
            nome += scan.nextLine();
            System.out.println("");
        } while (!Utente.utenteExiste(nome));
        resultadoNome = Marcacao.encontrarMarcacaoPorUtente(nome);

        System.out.println("MARCAÇÕES POR UTENTE:");
        Marcacao.tabelaMarcacoesHeader();
        resultadoNome.tabelaMarcacoesBody();

        pesquisarMarcacoes();

    }

    /**
     * Método para listar as Marcações a partir do Intervalo de Idades inserido
     */
    private static void pesquisarPorIntervaloIdades() {

        String intervalo = "";

        do {
            System.out.println("_____");
            System.out.println("Indique o intervalo de idades:");
            System.out.println("Exemplo: \" 23-30 \" ");
            intervalo = scan.next();

            System.out.println("\n");

        } while (!VacinacaoCovid.intervaloIdadesValida(intervalo));

        ArrayList<Marcacao> listaFiltrada = Marcacao.encontrarMarcacoesPorIntervaloIdades(intervalo);

        System.out.println("MARCAÇÕES POR INTERVALO DE IDADES:");
        if (!listaFiltrada.isEmpty()) {
            Marcacao.tabelaMarcacoesHeader();
            for (Marcacao marc : listaFiltrada) {
                marc.tabelaMarcacoesBody();
            }
            Marcacao.tabelaMarcacoesFooter(listaFiltrada);
        } else {
            System.out.println("Nenhum Utente se enquadra nos parâmetros de Idade inseridos...");
        }

        pesquisarMarcacoes();
    }

    /**
     * Método para listar a marcação da Marca/Lote inserida/o pelo Utilizador
     */
    private static void pesquisarPorMarcaLoteVacina() {

        ArrayList<Marcacao> resultadoMarc = new ArrayList<Marcacao>();
        String termo = "";

        do {

            System.out.println("Insira uma Marca de Vacina ou Lote de Vacina\n");
            termo = scan.next();
            termo += scan.nextLine();
            System.out.println("\n");

//        } while (!Vacina.marcaVacinaExiste(termo) || !Vacina.loteVacinaExiste(termo));
        } while (termo.equals(""));

        resultadoMarc = Marcacao.encontrarMarcacaoPorMarcaLoteVacina(termo);

        System.out.println("MARCAÇÕES POR MARCA OU LOTE DE VACINA:");
        if (!resultadoMarc.isEmpty()) {
            Marcacao.tabelaMarcacoesHeader();
            for (Marcacao marc : resultadoMarc) {
                marc.tabelaMarcacoesBody();
            }
        } else {
            System.out.println("Sem resultados...");
        }

        pesquisarMarcacoes();
    }

    /**
     * Método para listar as Marcações a partir do Dia inserido
     */
    private static void pesquisarPorDia() {

        ArrayList<Marcacao> listaDia = new ArrayList<Marcacao>();

        String data = "";
        LocalDate diaObj = null;

        do {
            System.out.println("_____");
            System.out.println("Indique o dia, no formato AAAA-MM-DD :");
            data = scan.next();

            System.out.println("\n");

            diaObj = VacinacaoCovid.dataStringParaLocalDate(data);

        } while (!Marcacao.dataExiste(diaObj));

        listaDia = Marcacao.encontrarMarcacaoPorData(diaObj);

        System.out.println("MARCAÇÕES POR DIA:");
        Marcacao.tabelaMarcacoesHeader();
        for (Marcacao item : listaDia) {
            item.tabelaMarcacoesBody();
        }
        Marcacao.tabelaMarcacoesFooter(listaDia);

        maisAcoes();

        pesquisarMarcacoes();
    }

    /**
     * Método para listar as Marcações a partir do Local inserido
     */
    private static void pesquisarPorLocal() {

        ArrayList<Marcacao> resultadoLocal = new ArrayList<Marcacao>();
        String local = "";

        do {
            System.out.println("Insira o Local de Marcação\n");
            local = scan.next();
            local += scan.nextLine();
            System.out.println("\n");
        } while (!Local.localExiste(local));

        resultadoLocal = Marcacao.encontrarMarcacaoPorLocal(local);

        System.out.println("MARCAÇÕES POR LOCAL:");
        Marcacao.tabelaMarcacoesHeader();
        if (!resultadoLocal.isEmpty()) {
            for (Marcacao marcacao : resultadoLocal) {
                marcacao.tabelaMarcacoesBody();
            }
            Marcacao.tabelaMarcacoesFooter(resultadoLocal);
        } else {
            System.out.println("Sem resultados...");
        }

        pesquisarMarcacoes();
    }

    /**
     * Método para listar todas as Marcações
     */
    public static void listarTodasMarcacoes() {

        ArrayList<Marcacao> marcacoes = Marcacao.listaMarcacoes;

        Marcacao.tabelaMarcacoesHeader();

        for (Marcacao marc : marcacoes) {
            marc.tabelaMarcacoesBody();
        }
        Marcacao.tabelaMarcacoesFooter(marcacoes);

        maisAcoes();
    }

    /**
     * Método para listar Marcações por Distrito
     */
    public static void pesquisarMarcacoesPorDistrito() {

        ArrayList<Marcacao> resultados = new ArrayList<Marcacao>();
        String distrito = "";
        do {
            System.out.println("\nInsira o Distrito:\n");
            distrito = scan.next();
            distrito += scan.nextLine();
            System.out.println("\n");
        } while (!Marcacao.distritoExiste(distrito));

        resultados = Marcacao.encontrarMarcacoesPorDistrito(distrito);

        System.out.println("MARCAÇÕES POR DISTRITO:");
        Marcacao.tabelaMarcacoesHeader();
        if (!resultados.isEmpty()) {
            for (Marcacao marc : resultados) {
                marc.tabelaMarcacoesBody();
            }
            Marcacao.tabelaMarcacoesFooter(resultados);
        } else {
            System.out.println("Sem resultados...");
        }

        String simOuNao = "";
        Boolean acao = false;

        do {
            System.out.println("Deseja criar uma nova zona?");
            System.out.println("Digite ' S ' para Confirmar ou ' N ' para Cancelar.");
            simOuNao = scan.next().toLowerCase();
            System.out.println("\n");

        } while (!simOuNao.equals("s") && !simOuNao.equals("n"));

        if (acao) {
            criarNovaZona();
        } else {
            menuMarcacoes();
        }
    }

    /**
     * Método para listar Documentos de Marcações por Zona
     */
    private static void listarMarcacoesPorZona() {

        ArrayList<Marcacao> resultadosZona = new ArrayList<Marcacao>();
        ArrayList<File> docs = new ArrayList<File>();
        docs = FileParser.listarDocs();

        System.out.println("\n\nFICHEIROS DE MARCAÇÕES POR ZONAS:");
        for (int i = 0; i < docs.size(); i++) {

            if (docs.get(i).getName().toLowerCase().startsWith("mar") && docs.get(i).getName().contains("_")) {
                String[] data = docs.get(i).getName().split("\\.");
                String nomeDocZona = data[0];
                System.out.println("    Nº" + i + " - " + nomeDocZona);
            }

        }

        String zona = "";
        do {
            System.out.println("\n*** GERIR ZONA ***          [ Prima 0 para sair ]");
            System.out.println("Indique qual a zona que pretende abrir?");
            zona = scan.next();
            zona += scan.nextLine();

            if (zona.equals("0")) {
                menuMarcacoes();
            }

        } while (!Marcacao.zonaExiste(zona) || zona.equals("0"));

        resultadosZona = Marcacao.encontrarMarcacoesPorZona(zona);

        System.out.println("\n\nMARCAÇÕES DA ZONA " + zona.toUpperCase() + ":");
        Marcacao.tabelaMarcacoesHeader();
        if (!resultadosZona.isEmpty()) {
            for (Marcacao marc : resultadosZona) {
                marc.tabelaMarcacoesBody();
            }
            Marcacao.tabelaMarcacoesFooter(resultadosZona);
        } else {
            System.out.println("Sem resultados...");
        }
        docs.clear();
        System.out.println("\n\n\n");
        menuMarcacoes();

    }

    /**
     * Método para criar uma nova zona
     */
    private static void criarNovaZona() {

        String zona = "";
        do {
            System.out.println("\n");
            System.out.println("Indique qual a nova zona:");
            zona = scan.next();
            zona += scan.nextLine();

        } while (zona.equals(""));

        adicionarMarcacaoExistente(zona);

    }

    /**
     * Método para adicionar uma marcação existente a uma zona
     *
     * @param zona
     */
    private static void adicionarMarcacaoExistente(String zona) {

        String nomeUtente = "";

        if (zona.equals("")) {
            do {
                System.out.println("\n");
                System.out.println("Indique qual a nova zona:");
                zona = scan.next();
                zona += scan.nextLine();

            } while (zona.equals(""));
        }

        System.out.println("\n\nADICIONAR MARCAÇÃO EXISTENTE A NOVA ZONA:");

        // UTENTE
        do {
            System.out.println("Indique qual o utente da marcação em que pretende alterar a Zona:");
            nomeUtente = scan.next();
            nomeUtente += scan.nextLine();
            System.out.println("\n");

        } while (!Utente.utenteExiste(nomeUtente));

        // MARCAÇÃO
        Marcacao marc = Marcacao.encontrarMarcacaoPorUtente(nomeUtente);

        // FINALIZAÇÃO DA EDIÇÃO
        Boolean acao = VacinacaoCovid.confirmaAcao("\n\nALTERAR A ZONA desta marcação");

        if (acao) {

            try {
                FileParser.eliminarInfoEmFicheiro("mar", construirLinhaParaExportar(marc), marc.getZona());
            } catch (IOException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }

            Marcacao.editarMarcacao(marc, marc.getData(), marc.getHora(), marc.getLocal().getTipoDeLocal(), marc.getLocal().nome, marc.getEnfermeiro(), zona
            );

            String simOuNao = "";
            Boolean acao2 = false;

            do {
                System.out.println("\n\nDeseja adicionar outra marcação à zona " + zona + "?");
                System.out.println("Digite ' S ' para Confirmar ou ' N ' para Cancelar.");
                simOuNao = scan.next().toLowerCase();
                System.out.println("\n");

            } while (!simOuNao.equals("s") && !simOuNao.equals("n"));

            if (acao2) {
                adicionarMarcacaoExistente(zona);
            } else {
                menuMarcacoes();
            }
        } else {
            menuMarcacoes();
        }

    }

}
