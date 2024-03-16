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
import java.awt.*;
import java.util.Vector;

public class Main extends JFrame {

    private JTable table;

    public Main() {
        setTitle("Horário de Aulas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Exemplo de dados dummy
        String[] columnNames = {"Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no Turno",
                "Dia da Semana", "Hora Início", "Hora Fim", "Data da Aula", "Características da Sala", "Sala Atribuída"};

        Object[][] rowData = {
                {"Curso A", "UC1", "Manhã", "Turma 1", "20",
                        "Segunda", "08:00", "10:00", "2024-03-17",
                        "Características da Sala A", "Sala 101"},
                {"Curso B", "UC2", "Tarde", "Turma 2", "15",
                        "Terça", "14:00", "16:00", "2024-03-18",
                        "Características da Sala B", "Sala 102"}
                
        };

        // Criar modelo de tabela
        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);

        // Criar tabela
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
        });
    }
}
