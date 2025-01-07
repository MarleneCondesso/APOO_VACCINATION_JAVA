package vacinacaoCovid.Classes;

import java.util.ArrayList;

import vacinacaoCovid.VacinacaoCovid;

public class Local {

    /**
     * Nome
     */
    public String nome;
    /**
     * Morada
     */
    public String morada;
    /**
     * Telefone
     */
    public int telf;
    /**
     * Tipo de Local
     */
    public char tipoDeLocal;
    /**
     * Distrito
     */
    public String distrito;
    /**
     * Lista de Locais
     */
    public static ArrayList<Local> listaLocais = new ArrayList<Local>();

    /**
     * Construtor de Local: inicialização
     */
    public Local() {
        this.nome = "";
        this.morada = "";
        this.telf = 0;
        this.tipoDeLocal = 0;
        this.distrito = "";
    }

    /**
     * Construtor de Local
     *
     * @param tipoDeLocal
     * @param nome
     * @param morada
     * @param telf
     * @param distrito
     */
    public Local(String nome, String morada, int telf, char tipoDeLocal, String distrito) {
        this.nome = nome;
        this.morada = morada;
        this.telf = telf;
        this.tipoDeLocal = tipoDeLocal;
        this.distrito = distrito;
    }

    /**
     * Método para retornar Nome
     *
     * @return
     */
    protected String getNome() {
        return this.nome;
    }

    /**
     * Método para retornar Morada
     *
     * @return
     */
    protected String getMorada() {
        return this.morada;
    }

    /**
     * Método para retornar Telefone
     *
     * @return
     */
    protected int getTelf() {
        return this.telf;
    }

    /**
     * Método para retornar Tipo de Local
     *
     * @return
     */
    public char getTipoDeLocal() {
        return this.tipoDeLocal;
    }

    /**
     * Método para obter Descrição do Tipo de Local, a partir do tipo
     *
     * @param tipo
     * @return descrição
     */
    protected static String getDescricaoTipoLocal(char tipo) {

        String descTipoLocal = "";

        switch (tipo) {
            case 'H':
                descTipoLocal = "Hospital";
                break;
            case 'P':
                descTipoLocal = "Pavilhão";
                break;
            case 'C':
                descTipoLocal = "Centro";
                break;
        }

        return descTipoLocal;
    }

    /**
     * Método para retornar Distrito
     *
     * @return
     */
    protected String getDistrito() {
        return this.distrito;
    }

    /**
     * Método para atribuir Nome
     *
     * @param nome
     */
    protected void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método para atribuir Morada
     *
     * @param morada
     */
    protected void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * Método para atribuir Telefone
     *
     * @param telf
     */
    protected void setTelf(int telf) {
        this.telf = telf;
    }

    /**
     * Método para atribuir Tipo de Local
     *
     * @param tipoDeLocal
     */
    public void setTipoDeLocal(char tipoDeLocal) {
        this.tipoDeLocal = tipoDeLocal;
    }

    /**
     * Método para atribuir Distrito
     *
     * @param distrito
     */
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }



    /**
     * Método para retornar lista de Locais existente em ficheiros (Centros,
     * Pavilhões e Hospitais)
     *
     * @return
     */
    public static ArrayList<Local> listarLocais() {

        listaLocais.addAll(Centro.listarCentros());
        listaLocais.addAll(Pavilhao.listarPavilhoes());
        listaLocais.addAll(Hospital.listarHospitais());

        return Local.listaLocais;
    }

    /**
     * Método para retornar Local por Nome
     *
     * @param nome
     * @return
     */
    public static Local encontrarLocalPorNome(String nome) {
        Local resultado = null;

        for (Local local : listarLocais()) {
            if (local.getNome().toLowerCase().equals(nome.toLowerCase())) {
                resultado = local;
                break;
            }
        }
        return resultado;
    }

    /**
     * Método para verificar se Local existe, através de Nome
     *
     * @param nome
     * @return
     */
    public static Boolean localExiste(String nome) {
        Boolean exists = false;

        for (Local loc : Local.listarLocais()) {
            if (loc.getNome().toLowerCase().equals(nome.toLowerCase())) {
                exists = true;
            }
        }

        if (!exists) {
            System.out.println("!!! O local com o nome \"" + nome + "\" não existe !!!\n");
            System.out.println("Locais: ");
            for (Local loc : Local.listarLocais()) {
                System.out.println("    - " + loc.getNome());
            }
        }

        return exists;
    }

    /**
     * Método para verificar se Local existe, através de Distrito
     *
     * @param distrito
     * @return
     */
    public static Boolean distritoExiste(String distrito) {
        Boolean exists = false;
        ArrayList<String> distritosDisponiveis = new ArrayList<String>();

        for (Local loc : Local.listarLocais()) {
            if (loc.getDistrito().toLowerCase().equals(distrito.toLowerCase())) {
                exists = true;
            } else {
                distritosDisponiveis.add(loc.getDistrito());
            }
        }

        if (!exists) {

            distritosDisponiveis = VacinacaoCovid.removerDuplicadosDeLista(distritosDisponiveis);
            System.out.println("!!! O distrito com o nome \"" + distrito + "\" não existe !!!\n");

            System.out.println("Distritos disponíveis: ");
            for (String distDisp : distritosDisponiveis) {
                System.out.println("- " + distDisp);
            }

        }

        return exists;
    }

    /**
     * Método para Escrever em ecrã cabeçalho de tabela de Locais
     */
    public static void tabelaLocaisHeader() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("%-50s%-60s%-30s%-30s", "Nome", "Morada", "Distrito", "Telefone");
        System.out.println("\n");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Método para Escrever em ecrã linha de local de tabela de Locais
     */
    public void tabelaLocaisBody() {
        System.out.format("%-50s%-60s%-30s%-30s",
                this.nome,
                this.morada,
                this.distrito,
                this.telf
        );
        System.out.println("\n");
    }

    /**
     * Método para Escrever em ecrã rodapé de tabela de Locais
     */
    public static void tabelaLocaisFooter(ArrayList<Local> lista) {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(" Total: " + lista.size());
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

}
