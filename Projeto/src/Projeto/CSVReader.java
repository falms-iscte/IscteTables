package Projeto;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;  
;

public class CSVReader {

	public static void lerHorario(String caminhoArquivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;
        while ((linha = reader.readLine()) != null) {
            // Processar linha do arquivo CSV e criar objetos HorarioAula
        }
        reader.close();
    }
	
	
	
	
	
}
