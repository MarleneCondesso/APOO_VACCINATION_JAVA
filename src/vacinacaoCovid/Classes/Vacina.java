package vacinacaoCovid.Classes;

import java.util.ArrayList;

import vacinacaoCovid.VacinacaoCovid;
import vacinacaoCovid.Menus.MenuMarcacoes;

public class Vacina {

    /**
     * Marca de Vacina
     */
    private String marca;
    /**
     * Lote de Vacina
     */
    private String lote;
    /**
     * Lista de Vacinas
     */
    public static ArrayList<Vacina> listaVacinas = new ArrayList<Vacina>();

    //Construtores
    public Vacina() {
        this.marca = "";
        this.lote = "";
    }

    /**
     * Construtor de Vacina
     *
     * @param marca
     * @param lote
     */
    public Vacina(String marca, String lote) {
        this.marca = marca;
        this.lote = lote;
    }

    /**
     * Construtor de Vacina
     *
     * @param marca
     * @param lote
     */
    public Vacina(Vacina vac) {
        this.marca = vac.marca;
        this.lote = vac.lote;
    }

    /**
     * Método para retornar marca
     *
     * @return
     */
    protected String getMarca() {
        return this.marca;
    }

    /**
     * Método para retornar lote
     *
     * @return
     */
    protected String getLote() {
        return this.lote;
    }

    /**
     * Método para atribuir marca
     *
     * @param marca
     */
    protected void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Método para atribuir lote
     *
     * @param lote
     */
    protected void setLote(String lote) {
        this.lote = lote;
    }

    /**
     * Método para atribuição de Vacina a partir de valores obtidos de ficheiros
     * externos
     *
     * @param valor
     * @return
     */
    public static Vacina retornaVacinaPorLinha(String valor) {
        Vacina vac = new Vacina();

        String[] data = valor.split("\\|");

        vac.setMarca(data[0]);
        vac.setLote(data[1]);

        return vac;
    }

    /**
     * Método para Retornar lista de vacinas
     *
     * @return
     */
    public static ArrayList<Vacina> listarVacinas() {
        return Vacina.listaVacinas;
    }

    /**
     * Método para retornar vacina a partir de marca
     *
     * @param marca
     * @return
     */
    public static Vacina encontrarVacinaPorMarca(String marca) {
        Vacina resultado = null;
        for (Vacina vac : Vacina.listarVacinas()) {
            if (vac.getMarca().toLowerCase().equals(marca.toLowerCase())) {
                resultado = vac;
            }
        }
        return resultado;
    }

    /**
     * Método para retornar vacina a partir de lote
     *
     * @param lote
     * @return
     */
    public static Vacina encontrarVacinaPorLote(String lote) {
        Vacina resultado = null;
        for (Vacina vac : Vacina.listarVacinas()) {
            if (vac.getLote().toLowerCase().equals(lote.toLowerCase())) {
                resultado = vac;
            }
        }
        return resultado;
    }

    /**
     * Método para retornar vacina a partir de marca e lote
     *
     * @param marca
     * @param lote
     * @return
     */
    public static Vacina encontrarVacinaPorMarcaLote(String marca, String lote) {
        Vacina resultado = null;
        for (Vacina vac : Vacina.listarVacinas()) {
            if (vac.getMarca().toLowerCase().equals(marca.toLowerCase()) && vac.getLote().toLowerCase().equals(lote.toLowerCase())) {
                resultado = vac;
            }
        }
        return resultado;
    }

    /**
     * Método para verificar se Marca de Vacina existe
     *
     * @param termo
     * @return
     */
    public static Boolean marcaVacinaExiste(String termo) {

        Boolean exists = false;
        ArrayList<String> marcasDisponiveis = new ArrayList<String>();

        for (Vacina vac : Vacina.listaVacinas) {
            if (vac.getMarca().toLowerCase().equals(termo.toLowerCase())) {
                exists = true;
            } else {
                marcasDisponiveis.add(vac.getMarca());
            }
        }

        if (!exists) {
            if (marcasDisponiveis.isEmpty()) {
                System.out.println("!!! A vacina da marca \"" + termo + "\" não existe !!!\n");
                MenuMarcacoes.menuMarcacoes();
            } else {
                marcasDisponiveis = VacinacaoCovid.removerDuplicadosDeLista(marcasDisponiveis);

                System.out.println("Marcas disponíveis: ");
                for (String marcaDisp : marcasDisponiveis) {
                    System.out.println("- " + marcaDisp);
                }
            }
        }

        return exists;
    }

    /**
     * Método para verificar se Lote de Vacina existe
     *
     * @param termo
     * @return
     */
    public static Boolean loteVacinaExiste(String termo) {
        Boolean exists = false;
        ArrayList<String> lotesDisponiveis = new ArrayList<String>();

        for (Vacina vac : Vacina.listaVacinas) {
            if (vac.getLote().toLowerCase().equals(termo.toLowerCase())) {
                exists = true;
            } else {
                if (vac.getMarca().toLowerCase().equals(termo.toLowerCase())) {
                    lotesDisponiveis.add(vac.getLote());
                }

            }
        }

        if (!exists) {
            if (lotesDisponiveis.isEmpty()) {
                System.out.println("!!! A vacina com o lote \"" + termo + "\" não existe !!!\n");
                MenuMarcacoes.menuMarcacoes();
            } else {
                lotesDisponiveis = VacinacaoCovid.removerDuplicadosDeLista(lotesDisponiveis);

                System.out.println("Lotes disponíveis: ");
                for (String loteDisp : lotesDisponiveis) {
                    System.out.println("- " + loteDisp);
                }
            }
        }

        return exists;
    }

    /**
     * Método para verificar se Lote de Vacina existe
     *
     * @param marca
     * @param lote
     * @return
     */
    public static Boolean marcaLoteVacinaExiste(String marca, String lote) {
        Boolean exists = false;

        for (Vacina vac : Vacina.listaVacinas) {
            if (vac.getMarca().toLowerCase().equals(marca.toLowerCase()) && vac.getLote().toLowerCase().equals(lote.toLowerCase())) {
                exists = true;
            }
        }

        if (!exists) {
            System.out.println("!!! O vacina \"" + marca + " | Lote " + lote + "\" não existe !!!\n");
            System.out.println("Lotes disponíveis: ");
            for (Vacina vac : Vacina.listaVacinas) {
                if (vac.getMarca().toLowerCase().equals(marca.toLowerCase())) {
                    System.out.println("    - " + vac.getMarca() + " | Lote " + vac.getLote());
                }
            }
        }

        return exists;
    }
}
