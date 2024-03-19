package Projeto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static DefaultTableModel model;

    public static void main(String[] args) throws IOException {

        String currentPath = new java.io.File(".").getCanonicalPath();
        System.out.println("Current dir:" + currentPath);

        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // Cria a janela principal
        JFrame frame = new JFrame("Horários de Aula");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        String[] columnNames = { "Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no Turno",
                "Dia da Semana", "Hora de Início", "Hora de Fim", "Data da Aula", "Características pedidas para Sala",
                "Sala Atribuída" };
        model = new DefaultTableModel(columnNames, 0);

        // le dados do arquivo CSV e adiciona ao modelo da tabela
        List<HorarioAula> horarios = readHorariosFromCSV(
                "HorarioDeExemplo.csv");
        for (HorarioAula horario : horarios) {
            model.addRow(new Object[] { horario.getCurso(), horario.getUnidadeCurricular(), horario.getTurno(),
                    horario.getTurma(), horario.getInscritosNoTurno(), horario.getDiaSemana(),
                    horario.getHoraInicioAula(), horario.getHoraFimAula(), horario.getDataAula(),
                    horario.getCaracteristicasSalaPedida(), horario.getSalaAtribuida() });
        }

        // cria tabela com o modelo de dados
        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Campo de texto para filtro
        JTextField filterText = new JTextField();
        filterText.setPreferredSize(new Dimension(200, 25)); // Definindo o tamanho preferido

        JButton filterButton = new JButton("Filtrar");

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        filterPanel.add(new JLabel("Filtrar por nome:"));
        filterPanel.add(filterText);
        filterPanel.add(filterButton);

        filterButton.addActionListener(e -> {
            String text = filterText.getText().toLowerCase();
            filter(text);
        });

        frame.add(filterPanel, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    private static void filter(String text) {
        // Limpa o modelo atual
        model.setRowCount(0);

        // le dados do arquivo CSV e adiciona ao modelo da tabela
        List<HorarioAula> horarios = readHorariosFromCSV(
                "HorarioDeExemplo.csv");
        for (HorarioAula horario : horarios) {
            // Verifica se o nome contém o texto de filtro
            if (horario.getCurso().toLowerCase().contains(text) ||
                    horario.getUnidadeCurricular().toLowerCase().contains(text) ||
                    horario.getTurno().toLowerCase().contains(text) ||
                    horario.getTurma().toLowerCase().contains(text) ||
                    horario.getDiaSemana().toLowerCase().contains(text) ||
                    horario.getDataAula().toLowerCase().contains(text) ||
                    horario.getCaracteristicasSalaPedida().toLowerCase().contains(text) ||
                    horario.getSalaAtribuida().toLowerCase().contains(text)) {
                model.addRow(new Object[] { horario.getCurso(), horario.getUnidadeCurricular(), horario.getTurno(),
                        horario.getTurma(), horario.getInscritosNoTurno(), horario.getDiaSemana(),
                        horario.getHoraInicioAula(), horario.getHoraFimAula(), horario.getDataAula(),
                        horario.getCaracteristicasSalaPedida(), horario.getSalaAtribuida() });
            }
        }
    }

    private static List<HorarioAula> readHorariosFromCSV(String csvFile) {
        List<HorarioAula> horarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;

            boolean firstLine = true; // Flag para identificar a primeira linha

            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length != 11) { // Verifica se o número de campos está correto
                    // Lidar com linhas incompletas ou inválidas, se necessário
                    continue; // Ignora esta linha e passa para a próxima
                }

                // Verifica se o campo "Inscritos no turno" contém um valor numérico
                int inscritosNoTurno;
                try {
                    inscritosNoTurno = Integer.parseInt(data[4]);
                } catch (NumberFormatException e) {
                    // Lidar com o caso em que o campo "Inscritos no turno" não é um número
                    // Por exemplo, atribuir um valor padrão ou ignorar esta linha
                    continue; // Ignora esta linha e passa para a próxima
                }
                HorarioAula horario = new HorarioAula(data[0], data[1], data[2], data[3], inscritosNoTurno,
                        data[5], data[6], data[7], data[8], data[9], data[10]);
                horarios.add(horario);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return horarios;
    }
}
