package vacinacaoCovid;

import java.io.IOException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import vacinacaoCovid.Classes.FileParser;
import vacinacaoCovid.Menus.MenuLocais;
import vacinacaoCovid.Menus.MenuMarcacoes;
import vacinacaoCovid.Menus.MenuPessoas;

public class VacinacaoCovid {

    private static Scanner scan = new Scanner(System.in);

    /**
     * Início do Programa
     *
     * @param args
     */
    public static void main(String[] args) {

        try {
            FileParser parser = new FileParser();
            parser.lerDocs();
            
        } catch (IOException ex) {
            System.out.println("Erro: " + ex);
        }

        mainHeader();
        mainMenu();
    }

    /**
     * Cabeçalho do Programa
     */
    private static void mainHeader() {
        System.out.println("********************************************************************************************************************************************************************************************************************************");
        System.out.println("                                                                                                 APLICAÇÃO VACINAÇÃO COVID-19");
        System.out.println("\n");
        System.out.println("                                                                                            Desenvolvido por Marlene Lima e Rute Lontro");
        System.out.println("                                                                                APOO | 2020/2021 | CTESP em Desenvolvimento Agil de Software | ISEP");
        System.out.println("********************************************************************************************************************************************************************************************************************************");
        System.out.println("\n");
    }

    /**
     * Menu Principal
     */
    public static void mainMenu() {
        int option = 0;

        System.out.println("********************************************************************************************************************************************************************************************************************************");
        System.out.println("                                                                                                    *** MENU PRINCIPAL ***");
        System.out.println("********************************************************************************************************************************************************************************************************************************");

        do {
            System.out.println("\n");
            System.out.println("    1) Marcações");
            System.out.println("    2) Pessoal");
            System.out.println("    3) Listas de Locais");
            System.out.println("    _____");
            System.out.println("    0) SAIR DO PROGRAMA ");
            System.out.println("\n");
            System.out.println("    Introduza a opção de navegação: ");
            option = scan.nextInt();

            switch (option) {
                case 1:
                    // MARCAÇÕES
                    MenuMarcacoes.menuMarcacoes();
                    break;
                case 2:
                    // PESSOAL
                    MenuPessoas.menuPessoas();
                    break;
                case 3:
                    // LOCAIS
                    MenuLocais.menuLocais();
                    break;
                case 0:
                    System.out.println("********************************************************************************************************************************************************************************************************************************");
                    System.out.println("                                                                                                   *** A sair do programa ***");
                    System.out.println("********************************************************************************************************************************************************************************************************************************");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }
        } while (option < 0 || option > 3);
    }

    /**
     * Método que transforma Data em String para Objecto LocalDate
     *
     * @param data
     * @return
     */
    public static LocalDate dataStringParaLocalDate(String data) {
        data = data.replace("-", "");
        DateTimeFormatter inputDateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate dataFormatada = LocalDate.parse(data, inputDateFormatter);

        return dataFormatada;

    }

    /**
     * Método para verificar se a data introduzida está no formato correcto
     *
     * @param data
     * @return
     */
    public static Boolean dataValida(String data) {
        Boolean dataValida = false;

        String datePattern = "\\d{4}-\\d{2}-\\d{2}";
        dataValida = data.matches(datePattern);

        return dataValida;
    }

    /**
     * Método que transforma Hora em String para Objecto LocalTime
     *
     * @param hora
     * @return
     */
    public static LocalTime horaStringParaLocalTime(String hora) {
        hora = hora.replace(":", "");
        DateTimeFormatter inputHourFormatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime horaFormatada = LocalTime.parse(hora, inputHourFormatter);

        return horaFormatada;

    }

    /**
     * Método para verificar se as horas introduzidas estão no formato correcto
     *
     * @param horas
     * @return
     */
    public static Boolean horaValida(String horas) {

        String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

        Pattern p = Pattern.compile(regex);

        if (horas == null) {
            return false;
        }

        Matcher m = p.matcher(horas);

        return m.matches();
    }

    /**
     * Método para verificar se o intervalo de idades introduzido estão no
     * formato correcto
     *
     * @param idades
     * @return
     */
    public static Boolean intervaloIdadesValida(String idades) {

        String regex = "([0-9]?[0-9])-([0-9]?[0-9])";

        Pattern p = Pattern.compile(regex);

        if (idades == null) {
            return false;
        }

        Matcher m = p.matcher(idades);

        return m.matches();
    }

    /**
     * Método para calcular Idade
     *
     * @param dataNasc
     * @return
     */
    public static int calcularIdade(LocalDate dataNasc) {
        LocalDate diaAtual = LocalDate.now();
        return Period.between(dataNasc, diaAtual).getYears();
    }

    /**
     * Método que solicita confirmação da ação
     *
     * @param message
     * @return
     */
    public static Boolean confirmaAcao(String message) {
        String simOuNao = "";
        boolean acao = false;

        do {
            System.out.println("Tem a certeza que " + message + "?");
            System.out.println("Digite ' S ' para Confirmar ou ' N ' para Cancelar.");
            simOuNao = scan.next().toLowerCase();
            System.out.println("\n");

        } while (!simOuNao.equals("s") && !simOuNao.equals("n"));

        if (simOuNao.equals("s")) {
            acao = true;
        }

        return acao;
    }

    /**
     * Método para remover duplicados de uma lista
     *
     * @param <T>
     * @param lista
     * @return
     */
    public static <T> ArrayList<T> removerDuplicadosDeLista(ArrayList<T> lista) {
        ArrayList<T> novaLista = new ArrayList<T>();

        for (T item : lista) {

            if (!novaLista.contains(item)) {

                novaLista.add(item);
            }
        }

        return novaLista;
    }

    /**
     * Método para remover acentuação de string
     *
     * @param texto
     * @return
     */
    public static String removerAcentuacaoDeString(String texto) {
        texto = Normalizer.normalize(texto, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return texto;
    }

}
