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
        // Carregar os dados do arquivo CSV
        List<CSVRecord> csvData = readCSVFile("HorariosTESTE.csv");

        // Gerar o conteúdo JSON da tabela Tabulator
        String jsonTableData = convertToJSON(csvData);

        // Gerar o conteúdo HTML da tabela Tabulator
        String htmlContent = generateHTML(jsonTableData);

        // Salvar o conteúdo HTML em um arquivo
        saveHTMLToFile(htmlContent, "table.html");

        // Abrir o arquivo HTML no navegador
        openHTMLFileInBrowser("table.html");
    }

    private static List<CSVRecord> readCSVFile(String csvFile) throws IOException {
        // Ler o arquivo CSV usando Apache Commons CSV e especificar o cabeçalho das colunas
        Reader reader = new FileReader(csvFile);
        CSVParser csvParser = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().parse(reader);
        return csvParser.getRecords();
    }

    private static String convertToJSON(List<CSVRecord> csvData) {
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

    private static void openHTMLFileInBrowser(String fileName) throws IOException {
        // Abrir o arquivo HTML no navegador padrão
        File htmlFile = new File(fileName);
        Desktop.getDesktop().browse(htmlFile.toURI());
    }
}





























