package Projeto;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.List;

public class Main {

    private static DefaultTableModel model;
    private static JTable table;
    private static JTextField[] filterTextFields;
    private static JButton filterButton;
    private static JComboBox<String> filterComboBox;

    public static void main(String[] args) throws IOException {

        String currentPath = new java.io.File(".").getCanonicalPath();
        System.out.println("Current dir:" + currentPath);

        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // Cria a janela principal
        JFrame frame = new JFrame("Horários de Aula");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        String[] columnNames = { "Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no Turno",
                "Dia da Semana", "Hora de Início", "Hora de Fim", "Data da Aula", "Características pedidas para Sala",
                "Sala Atribuída" };

        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Adicionando colunas Semana do ano e Semana do semestre
        model.addColumn("Semana do Ano");
        model.addColumn("Semana do Semestre");

        // Configurando o RowSorter
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        filterTextFields = new JTextField[columnNames.length];
        for (int i = 0; i < columnNames.length; i++) {
            JPanel columnPanel = new JPanel(new BorderLayout());
            JLabel label = new JLabel(columnNames[i] + ":");
            columnPanel.add(label, BorderLayout.NORTH);
            JTextField filterField = new JTextField(15);
            filterTextFields[i] = filterField;
            columnPanel.add(filterField, BorderLayout.CENTER);
            topPanel.add(columnPanel, gbc);
            gbc.gridx++;
        }

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = columnNames.length;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        filterComboBox = new JComboBox<>(new String[] { "E", "OU" });
        buttonsPanel.add(filterComboBox);

        filterButton = new JButton("Filtrar");
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyFilters();
            }
        });
        buttonsPanel.add(filterButton);

        JButton showHiddenColumnsButton = new JButton("Mostrar Colunas Escondidas");
        showHiddenColumnsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHiddenColumns();
            }
        });
        buttonsPanel.add(showHiddenColumnsButton);

        topPanel.add(buttonsPanel, gbc);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Adiciona o listener do mouse para o cabeçalho da tabela
        JTableHeader header = table.getTableHeader();
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    showColumnPopupMenu(e);
                }
            }
        });

        frame.setVisible(true);

        // Le os dados do arquivo CSV e popula a tabela
        readAndPopulateData("HorarioDeExemplo.csv");
    }

    private static void readAndPopulateData(String csvFile) {
        List<HorarioAula> horarios = readHorariosFromCSV(csvFile);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (HorarioAula horario : horarios) {
            model.addRow(new Object[] { horario.getCurso(), horario.getUnidadeCurricular(), horario.getTurno(),
                    horario.getTurma(), horario.getInscritosNoTurno(), horario.getDiaSemana(),
                    horario.getHoraInicioAula(), horario.getHoraFimAula(), horario.getDataAula(),
                    horario.getCaracteristicasSalaPedida(), horario.getSalaAtribuida(),
                    horario.getSemanaDoAno(), horario.getSemanaDoSemestre() });
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

    private static void applyFilters() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        List<RowFilter<Object, Object>> filters = new ArrayList<>();
        for (int i = 0; i < filterTextFields.length; i++) {
            String text = filterTextFields[i].getText();
            if (!text.isEmpty()) {
                RowFilter<Object, Object> filter = RowFilter.regexFilter("(?i)" + text, i);
                filters.add(filter);
            }
        }

        RowFilter<Object, Object> combinedFilter;
        if (filterComboBox.getSelectedItem().equals("E")) {
            combinedFilter = RowFilter.andFilter(filters);
        } else {
            combinedFilter = RowFilter.orFilter(filters);
        }

        sorter.setRowFilter(combinedFilter);
    }

    private static void showColumnPopupMenu(MouseEvent e) {
        JTableHeader header = (JTableHeader) e.getSource();
        JPopupMenu popupMenu = new JPopupMenu();
        TableColumnModel columnModel = header.getColumnModel();
        int column = columnModel.getColumnIndexAtX(e.getX());
        int modelColumn = table.convertColumnIndexToModel(column);

        JMenuItem hideColumnItem = new JMenuItem("Esconder Coluna");
        hideColumnItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideColumn(modelColumn);
            }
        });
        popupMenu.add(hideColumnItem);

        popupMenu.show(header, e.getX(), e.getY());
    }

    private static void hideColumn(int columnIndex) {
        TableColumn column = table.getColumnModel().getColumn(columnIndex);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);
        column.setResizable(false);
    }

    private static void showHiddenColumns() {
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setMinWidth(50); // Define a largura mínima da coluna
            column.setMaxWidth(Integer.MAX_VALUE); // Define a largura máxima da coluna
            column.setPreferredWidth(100); // Define a largura preferencial da coluna
            column.setResizable(true); // Permite redimensionamento da coluna
        }
    }
}
