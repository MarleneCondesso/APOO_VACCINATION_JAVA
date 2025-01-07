package vacinacaoCovid.Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileParser {

    public static String projectDirectory = System.getProperty("user.dir");
    public static String resourcesPath = (projectDirectory + "\\src\\vacinacaoCovid\\Resources\\");

    public static String enfermeirosPath = "";
    public static String utentesPath = "";
    public static String marcacoesPath = "";
    public static ArrayList<String> marcacoesPathList = new ArrayList<String>();
    public static String vacinasPath = "";

    public static String hospitaisPath = "";
    public static String centrosPath = "";
    public static String pavilhoesPath = "";

    public static ArrayList<File> listaFicheiros = new ArrayList<File>();

    /**
     * Método para ler ficheiros externos
     *
     * @throws IOException
     */
    public static void lerDocs() throws IOException {

        File folderPath = new File(resourcesPath);

        System.out.println("Resources Path: " + resourcesPath);
       // System.out.println("Folder Path: " + folderPath.getAbsolutePath());

        File filesList[] = folderPath.listFiles();

        if (filesList == null) {
            System.out.println("Error: Directory not found or inaccessible: " + folderPath.getAbsolutePath());
            return; // Exit the method to avoid NullPointerException
        }

       


        if (enfermeirosPath != null && !enfermeirosPath.isEmpty()) {
            popularEnfermeiros();
        } else {
            System.out.println("Error: Enfermeiros file not found!");
        }

        for (File file : filesList) {

            String fileName = file.getName().toLowerCase();

            if (fileName.startsWith("cen")) {
                centrosPath = file.getPath();
            } else if (fileName.startsWith("enf")) {
                enfermeirosPath = file.getPath();
            } else if (fileName.startsWith("hosp")) {
                hospitaisPath = file.getPath();
            } else if (fileName.startsWith("pav")) {
                pavilhoesPath = file.getPath();
            } else if (fileName.startsWith("ute")) {
                utentesPath = file.getPath();
            } else if (fileName.startsWith("vac")) {
                vacinasPath = file.getPath();
            } else if (fileName.startsWith("mar")) {
                if (!fileName.contains("_")) {
                    marcacoesPath = file.getPath();
                } else {
                    marcacoesPathList.add(file.getPath());
                }

            } else if (fileName.startsWith("_")) {
                continue;
            }
        }

        popularEnfermeiros();
        popularUtentes();
        popularCentros();
        popularPavilhoes();
        popularHospitais();
        popularVacinas();
        popularMarcacoes();

    }

    /**
     * Método para retornar lista de documentos
     *
     * @return
     */
    public static ArrayList<File> listarDocs() {

        File folderPath = new File(resourcesPath);
        File filesList[] = folderPath.listFiles();

        for (File file : filesList) {
            listaFicheiros.add(file);
        }

        return FileParser.listaFicheiros;
    }

    /**
     * Método para preencher lista de Enfermeiros
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void popularEnfermeiros() throws FileNotFoundException, IOException {
        String lineValue = "";

        FileReader fileReader = new FileReader(enfermeirosPath);

        BufferedReader bufferReader = new BufferedReader(fileReader);

        while (bufferReader.ready()) {

            lineValue = bufferReader.readLine();
            Enfermeiro.listaEnfermeiros.add(Enfermeiro.retornaEnfermeiroPorLinha(lineValue));
        }

        fileReader.close();

    }

    /**
     * Método para preencher lista de Utentes
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void popularUtentes() throws FileNotFoundException, IOException {
        String lineValue = "";

        FileReader fileReader = new FileReader(utentesPath);

        File fich = new File(utentesPath);
        String fileName = fich.getName();

        BufferedReader bufferReader = new BufferedReader(fileReader);

        while (bufferReader.ready()) {

            lineValue = bufferReader.readLine();
            Utente.listaUtentes.add(Utente.retornaUtentePorLinha(lineValue));
        }

        fileReader.close();

    }

    /**
     * Método para preencher lista de Vacinas
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void popularVacinas() throws FileNotFoundException, IOException {
        String lineValue = "";

        FileReader fileReader = new FileReader(vacinasPath);

        BufferedReader bufferReader = new BufferedReader(fileReader);

        while (bufferReader.ready()) {
            lineValue = bufferReader.readLine();
            Vacina.listaVacinas.add(Vacina.retornaVacinaPorLinha(lineValue));
        }

        fileReader.close();
    }

    /**
     * Método para preencher lista de Marcações
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void popularMarcacoes() throws FileNotFoundException, IOException {

        String lineValue = "";

        if (marcacoesPath != "") {

            FileReader fileReader = new FileReader(marcacoesPath);

            BufferedReader bufferReader = new BufferedReader(fileReader);

            while (bufferReader.ready()) {

                lineValue = bufferReader.readLine();
                Marcacao.listaMarcacoes.add(Marcacao.retornaMarcacaoPorLinha(lineValue));
            }

            fileReader.close();

        } else {

            for (String fichMarcZona : marcacoesPathList) {

                FileReader fileReader = new FileReader(fichMarcZona);

                File fich = new File(fichMarcZona);

                BufferedReader bufferReader = new BufferedReader(fileReader);

                while (bufferReader.ready()) {

                    lineValue = bufferReader.readLine();
                    Marcacao.listaMarcacoes.add(Marcacao.retornaMarcacaoPorLinha(lineValue));
                }

                fileReader.close();

            }

        }

    }

    /**
     * Método para preencher lista de Hospitais
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void popularHospitais() throws FileNotFoundException, IOException {
        String lineValue = "";

        FileReader fileReader = new FileReader(hospitaisPath);

        File fich = new File(hospitaisPath);
        String fileName = fich.getName();

        BufferedReader bufferReader = new BufferedReader(fileReader);

        while (bufferReader.ready()) {

            lineValue = bufferReader.readLine();
            Hospital.listaHospitais.add(Hospital.retornaHospitalPorLinha(lineValue));
        }

        fileReader.close();

    }

    /**
     * Método para preencher lista de Centros
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void popularCentros() throws FileNotFoundException, IOException {
        String lineValue = "";

        FileReader fileReader = new FileReader(centrosPath);

        File fich = new File(centrosPath);
        String fileName = fich.getName();

        BufferedReader bufferReader = new BufferedReader(fileReader);

        while (bufferReader.ready()) {

            lineValue = bufferReader.readLine();
            Centro.listaCentros.add(Centro.retornaCentroPorLinha(lineValue));
        }

        fileReader.close();

    }

    /**
     * Método para preencher lista de Pavilhões
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void popularPavilhoes() throws FileNotFoundException, IOException {
        String lineValue = "";

        FileReader fileReader = new FileReader(pavilhoesPath);

        File fich = new File(pavilhoesPath);
        String fileName = fich.getName();

        BufferedReader bufferReader = new BufferedReader(fileReader);

        while (bufferReader.ready()) {

            lineValue = bufferReader.readLine();
            Pavilhao.listaPavilhoes.add(Pavilhao.retornaPavilhaoPorLinha(lineValue));
        }

        fileReader.close();

    }

    /**
     * Método para comparar e eliminar linha de ficheiro
     *
     * @param tipoFicheiro
     * @param linha
     * @param zona
     * @throws IOException
     */
    public static void eliminarInfoEmFicheiro(String tipoFicheiro, String linha, String zona) throws IOException {

        String fichPath = "";
        if (tipoFicheiro.equals("mar")) {
            for (String path : marcacoesPathList) {
                if (path.contains(zona) && !zona.equals("")) {
                    fichPath = path;
                } else {
                    zona = zona.substring(0, 1).toUpperCase() + zona.substring(1).toLowerCase();
                    fichPath = resourcesPath + "marcacoes_" + zona + ".txt";
                }
            }
        } //        else if (tipoFicheiro.equals("_mar")) {
        //            fichPath = marcacoesPath;
        //        }
        else if (tipoFicheiro.equals("enf")) {
            fichPath = enfermeirosPath;
        } else if (tipoFicheiro.equals("ute")) {
            fichPath = utentesPath;
        }

        File originalFich = new File(fichPath);

        FileReader fileReader = null;
        File novoFich = new File(FileParser.resourcesPath + "tempFile");

        FileWriter out = new FileWriter(novoFich);

        try {
            fileReader = new FileReader(fichPath);

            BufferedReader bufReader = new BufferedReader(fileReader);

            while (bufReader.ready()) {

                String valorLinha = bufReader.readLine();

                //Compara a linha existente no ficheiro com a linha a eliminar
                if (!valorLinha.toLowerCase().equals(linha.toLowerCase())) {
                    out.write(valorLinha + "\r\n");
                }

            }

            fileReader.close();
            bufReader.close();
            out.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

        originalFich.delete();

        if (novoFich.renameTo(new File(fichPath))) {
//            System.out.println("________");
//            System.out.println("******* Ficheiro externo atualizado com sucesso! *******\n\n");
        } else {
            System.out.println("Ocorreu um erro...");
        }

    }

    /**
     * Método para acrescentar linha a ficheiro
     *
     * @param tipoFicheiro
     * @param linha
     * @param zona
     * @throws IOException
     */
    public static void acrescentarInfoEmFicheiro(String tipoFicheiro, String linha, String zona) throws IOException {

        String fichPath = "";

        if (tipoFicheiro.equals("mar")) {
            for (String path : marcacoesPathList) {
                if (path.contains(zona) && !zona.equals("")) {
                    fichPath = path;
                } else {
                    zona = zona.substring(0, 1).toUpperCase() + zona.substring(1).toLowerCase();
                    fichPath = resourcesPath + "marcacoes_" + zona + ".txt";
                }
            }
//        else if (tipoFicheiro.equals("_mar")) {
//            fichPath = marcacoesPath;
//        }
        } else if (tipoFicheiro.equals("enf")) {
            fichPath = enfermeirosPath;
        } else if (tipoFicheiro.equals("ute")) {
            fichPath = utentesPath;
        }

        FileWriter fileWriter = new FileWriter(fichPath, true); // True = APPEND
        fileWriter.write(linha + "\n");
        fileWriter.close();

    }

    /**
     * Método para exportar Informação para Ficheiro
     *
     * @param tipoFicheiro
     * @param lista
     * @param zona
     * @throws IOException
     */
    public static void exportarInfoParaFicheiro(String tipoFicheiro, ArrayList<String> lista, String zona) throws IOException {

        String fichPath = "";

        if (tipoFicheiro.equals("mar")) {
            for (String path : marcacoesPathList) {
                if (path.contains(zona) && !zona.equals("")) {
                    fichPath = path;
                } else {
                    zona = zona.substring(0, 1).toUpperCase() + zona.substring(1).toLowerCase();
                    fichPath = resourcesPath + "marcacoes_" + zona + ".txt";
                }
            }
//        else if (tipoFicheiro.equals("_mar")) {
//            fichPath = marcacoesPath;
//        }
        } else if (tipoFicheiro.equals("enf")) {
            fichPath = enfermeirosPath;
        } else if (tipoFicheiro.equals("ute")) {
            fichPath = utentesPath;
        }

        FileWriter fileWriter = new FileWriter(fichPath, false); // False = (Re)Write

        for (String item : lista) {
            fileWriter.write(item + "\n");
        }

        fileWriter.close();
        System.out.println("Exportação para o ficheiro realizada com sucesso.");

    }

}
