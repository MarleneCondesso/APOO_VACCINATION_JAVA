package vacinacaoCovid.Classes;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import vacinacaoCovid.VacinacaoCovid;
import vacinacaoCovid.Menus.MenuMarcacoes;

public class Marcacao {

    private static Scanner scan = new Scanner(System.in);
    /**
     * Local
     */
    private Local local;
    /**
     * Enfermeiro
     */
    private Enfermeiro enfermeiro;
    /**
     * Utente
     */
    private Utente utente;
    /**
     * Vacina
     */
    private Vacina vacina;
    /**
     * Data
     */
    private LocalDate data;
    /**
     * Hora
     */
    private LocalTime hora;
    /**
     * Zona
     */
    public String zona;
    /**
     * Lista de Marcações
     */
    public static ArrayList<Marcacao> listaMarcacoes = new ArrayList<Marcacao>();

    /**
     * Construtor de marcação: inicialização
     */
    public Marcacao() {
        this.data = null;
        this.hora = null;
        this.local = new Local();
        this.enfermeiro = new Enfermeiro();
        this.utente = new Utente();
        this.vacina = new Vacina();
        this.zona="";
    }

    /**
     * Contrutor de Marcação
     *
     * @param local
     * @param enfermeiro
     * @param utente
     * @param vacina
     * @param data
     * @param hora
     * @param zona
     */
    public Marcacao(Local local, Enfermeiro enfermeiro, Utente utente, Vacina vacina, LocalDate data, LocalTime hora, String zona) {

        this.local = local;
        this.enfermeiro = enfermeiro;
        this.utente = utente;
        this.vacina = vacina;
        this.data = data;
        this.hora = hora;
        this.zona = zona;
    }

    /**
     * Método para retorno de Local
     *
     * @return
     */
    public Local getLocal() {
        return this.local;
    }

    /**
     * Método para retorno de Enfermeiro
     *
     * @return
     */
    public Enfermeiro getEnfermeiro() {
        return this.enfermeiro;
    }

    /**
     * Método para retorno de Utente
     *
     * @return
     */
    protected Utente getUtente() {
        return this.utente;
    }

    /**
     * Método para retorno de Vacina
     *
     * @return
     */
    public Vacina getVacina() {
        return this.vacina;
    }

    /**
     * Método para retorno de Data
     *
     * @return
     */
    public LocalDate getData() {
        return this.data;
    }

    /**
     * Método para retorno de Hora
     *
     * @return
     */
    public LocalTime getHora() {
        return this.hora;
    }

    /**
     * Método para atribuição de Local
     *
     * @param local
     */
    public void setLocal(Local local) {
        this.local = local;
    }

    /**
     * Método para retornar Zona
     *
     * @return
     */
    public String getZona() {
        return this.zona;
    }

    /**
     * Método para atribuição de Centro
     *
     * @param centro
     */
    public void setCentro(Centro centro) {
        this.local = centro;
    }

    /**
     * Método para atribuição de Hospital
     *
     * @param hosp
     */
    public void setHospital(Hospital hosp) {
        this.local = hosp;
    }

    /**
     * Método para atribuição de Pavilhão
     *
     * @param pav
     */
    public void setPavilhao(Pavilhao pav) {
        this.local = pav;
    }

    /**
     * Método para atribuição de Enfermeiro
     *
     * @param enfermeiro
     */
    public void setEnfermeiro(Enfermeiro enfermeiro) {
        this.enfermeiro = enfermeiro;
    }

    /**
     * Método para atribuição de Utente
     *
     * @param utente
     */
    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    /**
     * Método para atribuição de Vacina
     *
     * @param marca
     * @param lote
     */
    public void setVacina(String marca, String lote) {
        this.vacina.setMarca(marca);
        this.vacina.setLote(lote);
    }

    /**
     * Método para atribuição de Data
     *
     * @param data
     */
    protected void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Método para atribuição de Hora
     *
     * @param hora
     */
    protected void setHora(LocalTime hora) {
        this.hora = hora;
    }

    /**
     * Método para atribuir Zona
     *
     * @param zona
     */
    public void setZona(String zona) {
        this.zona = zona;
    }

    /**
     * Método para atribuição de Marcação a partir de valores obtidos de
     * ficheiros externos
     *
     * @param valor
     * @return
     */
    public static Marcacao retornaMarcacaoPorLinha(String valor) {

        Marcacao marc = new Marcacao();

        String[] data = valor.split("\\|");

        switch (data[0].toLowerCase()) {
            case "h":
                marc.setHospital(Hospital.encontrarHospitalPorNome(data[1]));
                break;
            case "c":
                marc.setCentro(Centro.encontrarCentroPorNome(data[1]));
                break;
            case "p":
                marc.setPavilhao(Pavilhao.encontrarPavilhaoPorNome(data[1]));
                break;
            default:
                break;
        }
        marc.setEnfermeiro(Enfermeiro.encontrarEnfermeiroPorNome(data[2]));
        marc.setUtente(Utente.encontrarUtentePorNome(data[3]));
        marc.setVacina(data[4], data[5]);
        marc.setData(VacinacaoCovid.dataStringParaLocalDate(data[6]));
        marc.setHora(VacinacaoCovid.horaStringParaLocalTime(data[7]));
        marc.setZona(data[8]);

        return marc;
    }

    /**
     * Método para Procurar marcação por Utente
     *
     * @param nome
     * @return
     */
    public static Marcacao encontrarMarcacaoPorUtente(String nome) {

        Marcacao resultado = null;

        for (Marcacao marc : Marcacao.listaMarcacoes) {
            if (marc.getUtente().getNome().toLowerCase().equals(nome.toLowerCase())) {
                resultado = marc;
            }
        }
        return resultado;

    }

    /**
     * Método para Procurar Enfermeiros e Utentes por Data e Local
     *
     * @param data
     * @param local
     * @return
     */
    public static ArrayList<Pessoa> encontrarPessoasPorDataLocal(LocalDate data, String local) {

        ArrayList<Pessoa> resultado = new ArrayList<>();

        for (Marcacao marcacao : Marcacao.listaMarcacoes) {

            if (marcacao.getData().equals(data)
                    && marcacao.getLocal().getNome().toLowerCase().equals(local.toLowerCase())) {
                resultado.add(marcacao.enfermeiro);
                resultado.add(marcacao.utente);
                break;
            }

        }

        return resultado;
    }

    /**
     * Método para verificar se existe marcação com a data
     *
     * @param dia
     * @return
     */
    public static Boolean dataExiste(LocalDate dia) {
        Boolean exists = false;
        ArrayList<String> datasDisponiveis = new ArrayList<String>();

        for (Marcacao marc : Marcacao.listaMarcacoes) {

            if (marc.getData().isEqual(dia)) {
                exists = true;
            } else {
                datasDisponiveis.add(marc.getData().toString());
            }
        }

        if (!exists) {
            if (datasDisponiveis.isEmpty()) {
                System.out.println("Não ha marcações na data " + dia);
                MenuMarcacoes.menuMarcacoes();
            } else {
                datasDisponiveis = VacinacaoCovid.removerDuplicadosDeLista(datasDisponiveis);

                System.out.println("!!! Não ha marcações com a data de " + dia + " !!!\n");
                System.out.println("Datas disponíveis: ");
                for (String dataDisp : datasDisponiveis) {
                    System.out.println("- " + dataDisp);
                }
            }

        }

        return exists;
    }

    /**
     * Método para retornar lista de marcações a partir de data inserida
     *
     * @param data
     * @return
     */
    public static ArrayList<Marcacao> encontrarMarcacaoPorData(LocalDate data) {

        ArrayList<Marcacao> resultados = new ArrayList<Marcacao>();

        for (Marcacao marcacao : Marcacao.listaMarcacoes) {
            if (marcacao.getData().isEqual(data)) {
                resultados.add(marcacao);
            }
        }

        return resultados;
    }

    /**
     * Método para Encontrar marcação por Local
     *
     * @param local
     * @return
     */
    public static ArrayList<Marcacao> encontrarMarcacaoPorLocal(String local) {

        ArrayList<Marcacao> resultadoLocal = new ArrayList<Marcacao>();

        for (Marcacao marcacao : Marcacao.listaMarcacoes) {
            if (marcacao.getLocal().getNome().toLowerCase().equals(local.toLowerCase())) {
                resultadoLocal.add(marcacao);
            }
        }

        return resultadoLocal;
    }

    /**
     * Método para Encontrar marcação por Marca e/ou Lote de Vacina
     *
     * @param termo
     * @return
     */
    public static ArrayList<Marcacao> encontrarMarcacaoPorMarcaLoteVacina(String termo) {
        ArrayList<Marcacao> resultadoMarcaLote = new ArrayList<Marcacao>();

        for (Marcacao marcacao : Marcacao.listaMarcacoes) {

            if (marcacao.vacina.getMarca().toLowerCase().equals(termo.toLowerCase())) {
                resultadoMarcaLote.add(marcacao);
            } else if (marcacao.vacina.getLote().toLowerCase().equals(termo.toLowerCase())) {
                resultadoMarcaLote.add(marcacao);
            }
        }

        return resultadoMarcaLote;
    }

    /**
     * Método para Encontrar Marcações por Distrito
     *
     * @param distrito
     * @return
     */
    public static ArrayList<Marcacao> encontrarMarcacoesPorDistrito(String distrito) {

        ArrayList<Marcacao> resultado = new ArrayList<Marcacao>();

        for (Marcacao marcacao : Marcacao.listaMarcacoes) {
            if (marcacao.getLocal().getDistrito().toLowerCase().equals(distrito.toLowerCase())) {
                resultado.add(marcacao);
            }
        }

        return resultado;
    }

    /**
     * Método para verificar se existe marcação no Distrito
     *
     * @param distrito
     * @return
     */
    public static Boolean distritoExiste(String distrito) {
        Boolean exists = false;
        ArrayList<String> distritosDisponiveis = new ArrayList<String>();

        for (Marcacao marc : Marcacao.listaMarcacoes) {

            if (marc.local.getDistrito().toLowerCase().equals(distrito.toLowerCase())) {
                exists = true;
            } else {
                distritosDisponiveis.add(marc.local.getDistrito());
            }
        }

        if (!exists) {

            distritosDisponiveis = VacinacaoCovid.removerDuplicadosDeLista(distritosDisponiveis);

            System.out.println("Distritos disponíveis: ");
            for (String distDisp : distritosDisponiveis) {
                System.out.println("- " + distDisp);
            }

        }

        return exists;
    }

    /**
     * Método para Encontrar Marcações por Zona
     *
     * @param zona
     * @return
     */
    public static ArrayList<Marcacao> encontrarMarcacoesPorZona(String zona) {

        ArrayList<Marcacao> resultado = new ArrayList<Marcacao>();

        for (Marcacao marcacao : Marcacao.listaMarcacoes) {
            if (marcacao.getZona().toLowerCase().equals(zona.toLowerCase())) {
                resultado.add(marcacao);
            }
        }

        return resultado;
    }

    /**
     * Método para verificar se existe marcação na Zona
     *
     * @param zona
     * @return
     */
    public static Boolean zonaExiste(String zona) {
        Boolean exists = false;
        ArrayList<String> zonasDisponiveis = new ArrayList<String>();

        for (Marcacao marc : Marcacao.listaMarcacoes) {

            if (marc.getZona().toLowerCase().equals(zona.toLowerCase())) {
                exists = true;
            } else {
                zonasDisponiveis.add(marc.getZona());
            }
        }

        if (!exists) {

            zonasDisponiveis = VacinacaoCovid.removerDuplicadosDeLista(zonasDisponiveis);

            System.out.println("Zonas disponíveis: ");
            for (String zonaDisp : zonasDisponiveis) {
                System.out.println("- " + zonaDisp);
            }

        }

        return exists;
    }

    /**
     * Método para Encontrar Marcações por Intervalo de Idades
     *
     * @param idades
     * @return
     */
    public static ArrayList<Marcacao> encontrarMarcacoesPorIntervaloIdades(String idades) {

        String[] data = idades.split("-");
        int intervalo1 = Integer.parseInt(data[0]);
        int intervalo2 = Integer.parseInt(data[1]);

        ArrayList<Marcacao> resultados = new ArrayList<Marcacao>();

        for (Marcacao marcacao : Marcacao.listaMarcacoes) {

            int idadeUtente = VacinacaoCovid.calcularIdade(marcacao.utente.getDataNasc());

            if ((idadeUtente > intervalo1) && (idadeUtente < intervalo2)) {
                resultados.add(marcacao);
            }
        }

        return resultados;

    }

    /**
     * Método para Escrever em ecrã cabeçalho de tabela de Marcações
     */
    public static void tabelaMarcacoesHeader() {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("%-20s%-15s%-50s%-25s%-25s%-20s%-30s%-20s", "Data", "Hora", "Local", "Zona", "Vacina", "Lote", "Utente", "Enfermeiro");
        System.out.println("\n");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Método para Escrever em ecrã linha de marcação de tabela de Marcações
     */
    public void tabelaMarcacoesBody() {
        System.out.format("%-20s%-15s%-50s%-25s%-25s%-20s%-30s%-20s",
                this.data,
                this.hora,
                this.local.getNome(),
                this.getZona(),
                this.vacina.getMarca(),
                this.vacina.getLote(),
                this.utente.getNome() + " (" + VacinacaoCovid.calcularIdade(this.utente.getDataNasc()) + " anos)",
                this.enfermeiro.getNome()
        );
        System.out.println("\n");
    }

    /**
     * Método para Escrever em ecrã rodapé de tabela de Marcações
     *
     * @param lista
     */
    public static void tabelaMarcacoesFooter(ArrayList<Marcacao> lista) {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(" Total: " + lista.size());
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Método para eliminar Marcação
     *
     * @param marc
     */
    public static void eliminarMarcacao(Marcacao marc) {

        Marcacao.listaMarcacoes.remove(marc);
        try {
            FileParser.eliminarInfoEmFicheiro("mar", construirLinhaParaExportar(marc), marc.getZona());
        } catch (IOException ex) {
            System.out.println("Erro: " + ex);
        }

        System.out.println("\n");
        System.out.println("..........................................................................");
        System.out.println("A eliminar marcação do utente " + marc.getUtente().getNome() + "...");
        System.out.println("\n\n");
        System.out.println("Marcacão eliminado com sucesso !!");
        System.out.println("..........................................................................");
        System.out.println("\n\n");

        MenuMarcacoes.menuMarcacoes();

    }

    /**
     * Método para editar Marcação
     *
     * @param marc
     * @param data
     * @param hora
     * @param tipoLocal
     * @param nomeLocal
     * @param enf
     * @param zona
     */
    public static void editarMarcacao(Marcacao marc, LocalDate data, LocalTime hora, char tipoLocal, String nomeLocal, Enfermeiro enf, String zona) {

        tabelaMarcacoesHeader();
        marc.tabelaMarcacoesBody();
        System.out.println("\n\n\n");

        marc.setData(data);
        marc.setHora(hora);
        marc.setEnfermeiro(enf);
        marc.setZona(zona);

        switch (tipoLocal) {
            case 'H':
                marc.setHospital(Hospital.encontrarHospitalPorNome(nomeLocal));
                break;
            case 'C':
                marc.setCentro(Centro.encontrarCentroPorNome(nomeLocal));
                break;
            case 'P':
                marc.setPavilhao(Pavilhao.encontrarPavilhaoPorNome(nomeLocal));
                break;
            default:
                break;
        }

        try {
            FileParser.eliminarInfoEmFicheiro("mar", construirLinhaParaExportar(marc), marc.getZona());
            FileParser.acrescentarInfoEmFicheiro("mar", construirLinhaParaExportar(marc), marc.getZona());
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

        System.out.println("\n");
        System.out.println("..........................................................................");
        System.out.println("A editar marcação...");
        System.out.println("\n\n");
        System.out.println("Marcacão editada com sucesso !!");
        System.out.println("..........................................................................");
        System.out.println("\n\n");
        tabelaMarcacoesHeader();
        marc.tabelaMarcacoesBody();
        System.out.println("\n\n\n");

        MenuMarcacoes.menuMarcacoes();
    }

    /**
     * Método para criar Marcação
     *
     * @param ut
     * @param enf
     * @param tipoLocal
     * @param nomeLocal
     * @param data
     * @param hora
     */
    public static void criarMarcacao(Utente ut, Enfermeiro enf, Vacina vac, char tipoLocal, String nomeLocal, LocalDate data, LocalTime hora, String zona) {

        Marcacao marc = new Marcacao();

        marc.setUtente(ut);
        marc.setEnfermeiro(enf);
        marc.setVacina(vac.getMarca(), vac.getLote());
        marc.setData(data);
        marc.setHora(hora);
        marc.setZona(zona);

        switch (tipoLocal) {
            case 'H':
                marc.setHospital(Hospital.encontrarHospitalPorNome(nomeLocal));
                break;
            case 'C':
                marc.setCentro(Centro.encontrarCentroPorNome(nomeLocal));
                break;
            case 'P':
                marc.setPavilhao(Pavilhao.encontrarPavilhaoPorNome(nomeLocal));
                break;
            default:
                break;
        }

        Marcacao.listaMarcacoes.add(marc);
        try {
            FileParser.acrescentarInfoEmFicheiro("mar", construirLinhaParaExportar(marc), marc.getZona());
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

        System.out.println("\n");
        System.out.println("..........................................................................");
        System.out.println("A criar marcação...");
        System.out.println("\n\n");
        System.out.println("Marcacão criada com sucesso !!");
        System.out.println("..........................................................................");
        System.out.println("\n\n");
        tabelaMarcacoesHeader();
        marc.tabelaMarcacoesBody();
        System.out.println("\n\n\n");

        MenuMarcacoes.menuMarcacoes();
    }

    /**
     * Lista de todas as marcações
     *
     * @return
     */
    public static ArrayList<Marcacao> listarMarcacoes() {
        return Marcacao.listaMarcacoes;
    }

    /**
     * Método para construir linha para exportar para ficheiro
     *
     * @param marc
     * @return
     */
    public static String construirLinhaParaExportar(Marcacao marc) {

        String linha = marc.getLocal().getTipoDeLocal()
                + "|"
                + marc.local.getNome()
                + "|"
                + marc.enfermeiro.getNome()
                + "|"
                + marc.utente.getNome()
                + "|"
                + marc.vacina.getMarca()
                + "|"
                + marc.vacina.getLote()
                + "|"
                + marc.data.toString().replace("-", "")
                + "|"
                + marc.hora
                + "|"
                + marc.getZona();

        return linha;
    }

}
