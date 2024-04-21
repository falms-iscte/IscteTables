package Projeto;

import org.apache.commons.csv.CSVFormat;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // Criar pag inicial
    	String buttonsHTML = generateButtonsHTML();
        saveHTMLToFile(buttonsHTML, "PaginaInicial.html");
        
        
        
        
        
        //Carregar primeiro CSV de horarios
        List<CSVRecord> csvData = readCSVFile("HorariosTESTE.csv");

        // Gerar o conteúdo JSON da tabela Tabulator
        String jsonTableData = convertToJSONfromHorarios(csvData);

        // Gerar o conteúdo HTML da tabela Tabulator
        String htmlContent = generateHTML(jsonTableData);

        // Salvar o conteúdo HTML em um arquivo
        saveHTMLToFile(htmlContent, "Horarios.html");
        
        
        
        
        
        //Carregar o outro csv de salas
        //List<CSVRecord> csvData2 = readCSVFile("CaracterizaçãoDasSalas.csv");

        // Gerar o conteúdo JSON da tabela Tabulator
        String jsonTableData2 = convertToJSONfromSalas("CaracterizaçãoDasSalas.csv");

        // Gerar o conteúdo HTML da tabela Tabulator
        String htmlContent2 = generateHTML(jsonTableData2);

        // Salvar o conteúdo HTML em um arquivo
        saveHTMLToFile(htmlContent2, "Salas.html");
        
        
        
        
        
        // Abrir o arquivo buttons.html no navegador
        openHTMLFileInBrowser("PaginaInicial.html");
    }
    
    private static String generateButtonsHTML() {
        // Gerar o conteúdo HTML com os botões para selecionar entre os diferentes conjuntos de dados
        String html = "<!DOCTYPE html><html><head></head><body>";
        html += "<button onclick='openHTML(\"Horarios.html\")'>Visualizar Tabela 1</button>";
        html += "<button onclick='openHTML(\"Salas.html\")'>Visualizar Tabela 2</button>";
        html += "<script>function openHTML(fileName) { window.open(fileName); }</script>";
        html += "</body></html>";
        return html;
    }

    private static List<CSVRecord> readCSVFile(String csvFile) throws IOException {
        // Ler o arquivo CSV usando Apache Commons CSV e especificar o cabeçalho das colunas
        Reader reader = new FileReader(csvFile);
        CSVParser csvParser = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().parse(reader);
        return csvParser.getRecords();
    }

    private static String convertToJSONfromHorarios(List<CSVRecord> csvData) {
        // Converter os dados do CSV para JSON
        JSONArray jsonArray = new JSONArray();
        for (CSVRecord record : csvData) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Curso", record.get("Curso"));
            jsonObject.put("Unidade Curricular", record.get("Unidade Curricular"));
            jsonObject.put("Turno", record.get("Turno"));
            jsonObject.put("Turma", record.get("Turma"));
            jsonObject.put("Inscritos no Turno", record.get("Inscritos no turno")); // Corrigido para correspondência exata
            jsonObject.put("Dia da Semana", record.get("Dia da semana")); // Corrigido para correspondência exata
            jsonObject.put("Hora de Início", record.get("Hora início da aula")); // Corrigido para correspondência exata
            jsonObject.put("Hora de Fim", record.get("Hora fim da aula")); // Corrigido para correspondência exata
            jsonObject.put("Data da Aula", record.get("Data da aula")); // Corrigido para correspondência exata
            jsonObject.put("Características pedidas para Sala", record.get("Características da sala pedida para a aula")); // Corrigido para correspondência exata
            jsonObject.put("Sala Atribuída", record.get("Sala atribuída à aula")); // Corrigido para correspondência exata
            //jsonObject.put("Semana do Ano", record.get("Semana do Ano")); // Corrigido para correspondência exata
            //jsonObject.put("Semana do Semestre", record.get("Semana do Semestre")); // Corrigido para correspondência exata
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }
    
    private static String convertToJSONfromSalas(String csvFilePath) {
        JSONArray jsonArray = new JSONArray();
        try {
            Reader reader = new FileReader(csvFilePath);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader());
            List<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord record : csvRecords) {
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < record.size(); i++) {
                    jsonObject.put(csvParser.getHeaderNames().get(i), record.get(i));
                }
                jsonArray.put(jsonObject);
            }
            csvParser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray.toString();
    }
    


    private static String generateHTML(String jsonTableData) {
        // Gerar o conteúdo HTML da tabela Tabulator
        String html = "<!DOCTYPE html><html><head><link href='https://unpkg.com/tabulator-tables@4.9.3/dist/css/tabulator.min.css' rel='stylesheet'>";
        html += "<script src='https://unpkg.com/tabulator-tables@4.9.3/dist/js/tabulator.min.js'></script>";
        html += "</head><body><div id='example-table'></div>";
        html += "<script>var table = new Tabulator('#example-table', {data: " + jsonTableData + ", autoColumns:true});</script>";
        html += "</body></html>";
        return html;
    }


    private static void saveHTMLToFile(String htmlContent, String fileName) throws IOException {
        // Salvar o conteúdo HTML em um arquivo
        FileWriter writer = new FileWriter(fileName);
        writer.write(htmlContent);
        writer.close();
    }

    private static void openHTMLFileInBrowser(String filePath) throws IOException {
        // Abrir o arquivo HTML no navegador padrão
        Runtime.getRuntime().exec("cmd /c start " + filePath); // para Windows
        // Runtime.getRuntime().exec("open " + filePath); // para MacOS
        // Runtime.getRuntime().exec("xdg-open " + filePath); // para Linux
    }


}





























