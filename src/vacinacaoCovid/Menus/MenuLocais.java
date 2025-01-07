package vacinacaoCovid.Menus;

import java.util.ArrayList;
import java.util.Scanner;

import vacinacaoCovid.VacinacaoCovid;
import vacinacaoCovid.Classes.Centro;
import vacinacaoCovid.Classes.Hospital;
import vacinacaoCovid.Classes.Local;
import vacinacaoCovid.Classes.Pavilhao;

public class MenuLocais {

    private static Scanner scan = new Scanner(System.in, "ISO-8859-1");

    /**
     * Menu Locais
     */
    public static void menuLocais() {
        int option = 0;

        System.out.println("********************************************************************************************************************************************************************************************************************************");
        System.out.println("                                                                                                           *** LOCAIS ***");
        System.out.println("********************************************************************************************************************************************************************************************************************************");

        do {
            System.out.println("\nAções: ");
            System.out.println("    1) Listar Todos os Locais");
            System.out.println("\n");
            System.out.println("    2) Listar Centros");
            System.out.println("    3) Listar Hospitais");
            System.out.println("    4) Listar Pavilhões");
            System.out.println("    _____");
            System.out.println("    0) VOLTAR AO MENU PRINCIPAL");

            System.out.println("\n     Introduza a opção: ");
            option = scan.nextInt();

            switch (option) {
                case 1:
                    listarLocais();
                    break;
                case 2:
                    // PESQUISAR
                    listarCentros();
                    break;
                case 3:
                    // ADICIONAR
                    listarHospitais();
                    break;
                case 4:
                    // EDITAR;
                    listarPavilhoes();
                    break;
                case 0:
                    VacinacaoCovid.mainMenu();
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }
        } while (option < 0 || option > 4);

    }

    /**
     * Método para listar Locais em tabela
     */
    private static void listarLocais() {

        ArrayList<Local> locais = Local.listarLocais();

        Local.tabelaLocaisHeader();
        for (Local loc : locais) {
            loc.tabelaLocaisBody();
        }
        Local.tabelaLocaisFooter(locais);

        System.out.println("\n\n");
        menuLocais();
    }

    /**
     * Método para listar Locais em tabela
     */
    private static void listarCentros() {

        ArrayList<Centro> centros = Centro.listarCentros();
        System.out.println("\nCENTROS:");
        Centro.tabelaCentrosHeader();
        for (Centro cen : centros) {
            cen.tabelaCentrosBody();
        }
        Centro.tabelaCentrosFooter(centros);

        System.out.println("\n\n");
        menuLocais();
    }

    /**
     * Método para listar Locais em tabela
     */
    private static void listarHospitais() {

        ArrayList<Hospital> hospitais = Hospital.listarHospitais();
        System.out.println("\nHOSPITAIS:");
        Hospital.tabelaHospitaisHeader();
        for (Hospital hosp : hospitais) {
            hosp.tabelaHospitaisBody();
        }
        Hospital.tabelaHospitaisFooter(hospitais);

        System.out.println("\n\n");
        menuLocais();
    }

    /**
     * Método para listar Locais em tabela
     */
    private static void listarPavilhoes() {

        ArrayList<Pavilhao> pavilhoes = Pavilhao.listarPavilhoes();
        System.out.println("\nPAVILHÕES:");
        Pavilhao.tabelaPavilhoesHeader();
        for (Pavilhao pav : pavilhoes) {
            pav.tabelaPavilhoesBody();
        }
        Pavilhao.tabelaPavilhoesFooter(pavilhoes);

        System.out.println("\n\n");
        menuLocais();
    }

}
