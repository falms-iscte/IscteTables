package Projeto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*; 



class MainTest {

    @Test
    void testReadHorariosFromCSV() {
        // Preparação
        Main main = new Main();
        String csvFile = "HorarioDeExemplo.csv"; // Arquivo de exemplo contendo os dados
        List<HorarioAula> expectedHorarios = criarListaHorariosEsperados(); // Definir a lista de horários esperados

        // Execução
        List<HorarioAula> horarios = main.readHorariosFromCSV(csvFile);

        // Verificação
        assertEquals(expectedHorarios.size(), horarios.size(), "Número de horários lidos deve ser igual ao esperado");

        // Verificar cada horário individualmente
        for (int i = 0; i < expectedHorarios.size(); i++) {
            HorarioAula expected = expectedHorarios.get(i);
            HorarioAula actual = horarios.get(i);
            assertEquals(expected.getCurso(), actual.getCurso(), "Curso do horário " + i + " incorreto");
            assertEquals(expected.getUnidadeCurricular(), actual.getUnidadeCurricular(), "Unidade Curricular do horário " + i + " incorreta");
            // Continuar verificando outros campos conforme necessário
        }
    }

    // Método auxiliar para criar a lista de horários de aula esperados para teste
    private List<HorarioAula> criarListaHorariosEsperados() {
        // Implementar conforme necessário com dados fictícios para teste
    
    	 List<HorarioAula> horariosEsperados = new ArrayList<>();

         // Adicione alguns horários de aula fictícios para teste
         horariosEsperados.add(new HorarioAula("Curso1", "Matemática", "Manhã", "TurmaA", 30, "Segunda", "08:00", "10:00", "01/01/2024", "SalaA", "101"));
         horariosEsperados.add(new HorarioAula("Curso2", "História", "Tarde", "TurmaB", 25, "Terça", "13:00", "15:00", "02/01/2024", "SalaB", "102"));
         // Adicione mais horários conforme necessário

         return horariosEsperados;
    }
}
