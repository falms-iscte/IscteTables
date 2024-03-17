
package Projeto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
	

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
    
    
    private static void createAndShowGUI() {
        // Cria a janela principal
        JFrame frame = new JFrame("Horários de Aula");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        
        
        String[] columnNames = {"Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no Turno",
                "Dia da Semana", "Hora de Início", "Hora de Fim", "Data da Aula", "Características pedidas para Sala", "Sala Atribuída"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        
        
        // le dados do arquivo CSV e adiciona ao modelo da tabela
        List<HorarioAula> horarios = readHorariosFromCSV("HorarioDeExemplo.csv");
        for (HorarioAula horario : horarios) {
            model.addRow(new Object[]{horario.getCurso(), horario.getUnidadeCurricular(), horario.getTurno(),
                    horario.getTurma(), horario.getInscritosNoTurno(), horario.getDiaSemana(),
                    horario.getHoraInicioAula(), horario.getHoraFimAula(), horario.getDataAula(),
                    horario.getCaracteristicasSalaPedida(), horario.getSalaAtribuida()});
        }

        // cria tabela com o modelo de dados
        JTable table = new JTable(model);

      
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        
        frame.setVisible(true);
    }


    
    
    
    
    
    
    private static List<HorarioAula> readHorariosFromCSV(String csvFile) {
        List<HorarioAula> horarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            
            boolean firstLine = true; // Flag para identificar a primeira linha
            
            while ((line = br.readLine()) != null) {
            	
            	 if (firstLine) {
                     firstLine = false; // Ignora a primeira linha pq é a linha do cabeçalho
                     
                     continue;
                 }
            	
                String[] data = line.split(";");
                HorarioAula horario = new HorarioAula(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]),
                        data[5], data[6], data[7], data[8], data[9], data[10]);
                horarios.add(horario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return horarios;
    }
    
}





