//package Projeto;
//
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.util.Vector;
//
//public class Main extends JFrame {
//
//    private JTable table;
//
//    public Main() {
//        setTitle("Horário de Aulas");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(800, 600);
//
//        // Exemplo de dados
//        Vector<String> columnNames = new Vector<>();
//        columnNames.add("Curso");
//        columnNames.add("Unidade Curricular");
//        columnNames.add("Turno");
//        columnNames.add("Turma");
//        columnNames.add("Inscritos no Turno");
//        columnNames.add("Dia da Semana");
//        columnNames.add("Hora Início");
//        columnNames.add("Hora Fim");
//        columnNames.add("Data da Aula");
//        columnNames.add("Características da Sala");
//        columnNames.add("Sala Atribuída");
//
//        Vector<Vector<String>> rowData = new Vector<>();
//        rowData.add(new Vector<>(Vector.of("Curso A", "UC1", "Manhã", "Turma 1", "20",
//                "Segunda", "08:00", "10:00", "2024-03-17",
//                "Características da Sala A", "Sala 101")));
//        rowData.add(new Vector<>(Vector.of("Curso B", "UC2", "Tarde", "Turma 2", "15",
//                "Terça", "14:00", "16:00", "2024-03-18",
//                "Características da Sala B", "Sala 102")));
//        // Adicione mais dados conforme necessário
//
//        // Criar modelo de tabela
//        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
//
//        // Criar tabela
//        table = new JTable(model);
//        JScrollPane scrollPane = new JScrollPane(table);
//
//        getContentPane().add(scrollPane, BorderLayout.CENTER);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            Main main = new Main();
//            main.setVisible(true);
//        });
//    }
//}

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

        // Cria o modelo da tabela com os nomes das colunas
        String[] columnNames = {"Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no Turno",
                "Dia da Semana", "Hora Início", "Hora Fim", "Data da Aula", "Características da Sala", "Sala Atribuída"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Lê os dados do arquivo CSV e adiciona ao modelo da tabela
        List<HorarioAula> horarios = readHorariosFromCSV("HorarioDeExemplo.csv");
        for (HorarioAula horario : horarios) {
            model.addRow(new Object[]{horario.getCurso(), horario.getUnidadeCurricular(), horario.getTurno(),
                    horario.getTurma(), horario.getInscritosNoTurno(), horario.getDiaSemana(),
                    horario.getHoraInicioAula(), horario.getHoraFimAula(), horario.getDataAula(),
                    horario.getCaracteristicasSalaPedida(), horario.getSalaAtribuida()});
        }

        // Cria a tabela com o modelo de dados
        JTable table = new JTable(model);

        // Adiciona a tabela a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        // Exibe a janela
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





