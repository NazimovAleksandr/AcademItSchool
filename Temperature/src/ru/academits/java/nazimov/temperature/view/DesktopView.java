package ru.academits.java.nazimov.temperature.view;

import ru.academits.java.nazimov.temperature.model.TemperatureConverter;
import ru.academits.java.nazimov.temperature.model.scales.TemperatureScale;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DesktopView implements View {
    private final TemperatureConverter converter;

    public DesktopView(TemperatureConverter converter) {
        this.converter = converter;
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            JFrame frame = new JFrame("Конвертер Температуры");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(Color.WHITE);

            JLabel title = new JLabel("Введите температуру:");

            title.setBorder(new EmptyBorder(0, 10, 20, 10));
            title.setFont(new Font("Arial", Font.PLAIN, 20));

            GridBagConstraints constraints = new GridBagConstraints();

            constraints.fill = GridBagConstraints.BOTH;
            constraints.gridwidth = 2;
            constraints.gridx = 0;
            constraints.gridy = 0;
            panel.add(title, constraints);

            JTextField fromTextField = new JFormattedTextField();

            fromTextField.setPreferredSize(new Dimension(150, 50));
            fromTextField.setFont(new Font("Arial", Font.PLAIN, 22));
            fromTextField.setMargin(new Insets(0, 10, 0, 10));

            JLabel toLabel = new JLabel();

            toLabel.setPreferredSize(new Dimension(150, 50));
            toLabel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 1),
                    new EmptyBorder(0, 10, 0, 10)));
            toLabel.setFont(new Font("Arial", Font.PLAIN, 22));

            constraints.gridwidth = 1;
            constraints.gridy = 1;
            constraints.insets.set(0, 7, 0, 7);
            panel.add(fromTextField, constraints);

            constraints.gridx = 1;
            panel.add(toLabel, constraints);

            JComboBox<TemperatureScale> fromComboBox = new JComboBox<>(converter.getTemperatureScales());
            JComboBox<TemperatureScale> toComboBox = new JComboBox<>(converter.getTemperatureScales());

            constraints.gridx = 0;
            constraints.gridy = 2;
            panel.add(fromComboBox, constraints);

            constraints.gridx = 1;
            panel.add(toComboBox, constraints);

            JButton button = new JButton("Конвертировать");
            button.addActionListener(e -> {
                try {
                    double temperature = Double.parseDouble(fromTextField.getText());
                    TemperatureScale fromScale = fromComboBox.getItemAt(fromComboBox.getSelectedIndex());
                    TemperatureScale toScale = toComboBox.getItemAt(toComboBox.getSelectedIndex());

                    toLabel.setText(Double.toString(converter.convert(temperature, fromScale, toScale)));
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(frame, "Введите число", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                }
            });

            button.setPreferredSize(new Dimension(200, 50));

            constraints.insets.set(10, 0, 0, 0);
            constraints.gridwidth = 2;
            constraints.gridy = 3;
            constraints.gridx = 0;
            panel.add(button, constraints);

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}