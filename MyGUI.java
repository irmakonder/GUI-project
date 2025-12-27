import javax.swing.*;
import java.awt.*;

public class MyGUI extends JFrame {
    private JTextField nameField;
    private JTextField ageField;
    private JTextField cityField;
    private JLabel outputLabel;

    public MyGUI() {
        initializeUI();
    }
    private void initializeUI() {
        setTitle("My Personal GUI");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Create main panel with padding
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Input fields
        nameField = createInputField("Name:", mainPanel, gbc, 0);
        ageField = createInputField("Age:", mainPanel, gbc, 1);
        cityField = createInputField("City:", mainPanel, gbc, 2);

        // Submit button
        JButton submitButton = new JButton("Submit");
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(submitButton, gbc);

        // Output label
        outputLabel = new JLabel("");
        outputLabel.setForeground(new Color(0, 102, 204));
        gbc.gridy = 4;
        gbc.gridx=0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;  // Allow horizontal expansion
        gbc.insets = new Insets(10, 5, 5, 5);  // Add more vertical padding
        mainPanel.add(outputLabel, gbc);

        outputLabel.setBorder(
                BorderFactory.createEmptyBorder(10, 0, 0, 0)
        );

        // Add action listener
        submitButton.addActionListener((_)-> validateAndSubmit());

        add(mainPanel);
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);

        // Add a clear button
        JButton clearButton = new JButton("Clear");
        gbc.gridy = 3;
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        mainPanel.add(clearButton, gbc);

        // Add a combobox for cities
        String[] commonCities = {"Istanbul", "Ankara", "Izmir","Aydın", "Other"};
        JComboBox<String> cityCombo = new JComboBox<>(commonCities);
        gbc.gridx = 2;
        gbc.gridy = 2;
        mainPanel.add(cityCombo, gbc);


        submitButton.addActionListener(_-> validateAndSubmit());
        clearButton.addActionListener(_-> clearFields());
        cityCombo.addActionListener(e -> {
            Object selected =cityCombo.getSelectedItem();
            if("0ther".equals(selected)){
                cityField.setEnabled(true);
            }else{
                if(selected != null) cityField.setText(selected.toString());
                cityField.setEnabled(false);
            }
        });
        //for adding and printing or main panel
        add(mainPanel);
        pack();                 // 🔥 asıl olay burada
        setLocationRelativeTo(null);
        setVisible(true);

    }


    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        cityField.setText("");
        outputLabel.setText("");
    }



    private JTextField createInputField(String labelText, JPanel panel, GridBagConstraints gbc, int row) {
        JLabel label = new JLabel(labelText);
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0.2;
        panel.add(label, gbc);

        JTextField field = new JTextField(20);
        gbc.gridx = 1;
        gbc.weightx = 0.8;
        panel.add(field, gbc);

        return field;
    }

    private void validateAndSubmit() {
        String name = nameField.getText().trim();
        String age = ageField.getText().trim();
        String city = cityField.getText().trim();

        // Input validation
        if (name.isEmpty() || age.isEmpty() || city.isEmpty()) {
            showError("Please fill in all fields");
            return;
        }

        try {
            int ageValue = Integer.parseInt(age);
            if (ageValue < 0 || ageValue > 150) {
                showError("Please enter a valid age");
                return;
            }

            outputLabel.setForeground(new Color(0, 102, 204));
            outputLabel.setText(String.format("Hello %s, you are %d years old and you live in %s",
                    name, ageValue, city));

        } catch (NumberFormatException e) {
            showError("Please enter a valid number for age");
        }
    }

    private void showError(String message) {
        outputLabel.setForeground(Color.RED);
        outputLabel.setText(message);
    }

    public static void main(String[] args) {
        // Run GUI in Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new MyGUI());
    }
}