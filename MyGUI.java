import javax.swing.*;
import java.awt.*;

public class MyGUI extends JFrame {
    private JTextField nameField;
    private JTextField surnameField;
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
        surnameField = createInputField("Surname:", mainPanel, gbc, 1); 
        ageField = createInputField("Age:", mainPanel, gbc, 2);
        cityField = createInputField("City:", mainPanel, gbc, 3);
        
        // Submit button
        JButton submitButton = new JButton("Submit");
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(submitButton, gbc);
        
        // Output label
        outputLabel = new JLabel("");
        outputLabel.setForeground(new Color(0, 102, 204));
        gbc.gridy = 5;
        gbc.gridx=1;
        gbc.gridwidth = 4;
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
        gbc.gridy = 4;
        gbc.gridx = 3;
        gbc.gridwidth = 2;
        mainPanel.add(clearButton, gbc);
        
        // Add a combobox for cities
        String[] commonCities = {"İstanbul", "Ankara", "İzmir","Aydın", "Other"};
        JComboBox<String> cityCombo = new JComboBox<>(commonCities);
        gbc.gridx = 3;
        gbc.gridy = 3;
        mainPanel.add(cityCombo, gbc);

        //Add a combobox for ages
        String[] ageOptions = {"1","2","3","4","5","6","7","8","9","10",
                                "11","12","13","14","15","16","17","18",
                                "19","20","21","22","23","24","25",
                                "26","27","28","29","30"};
        JComboBox<String> ageCombo = new JComboBox<>(ageOptions);
        gbc.gridx = 3;
        gbc.gridy = 2;
        mainPanel.add(ageCombo, gbc);



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
        ageCombo.addActionListener(e -> {
            Object selected = ageCombo.getSelectedItem();
            if(selected != null) ageField.setText(selected.toString());
        });
       


        //for adding and printing or main panel
        add(mainPanel);
        pack();                 
        setLocationRelativeTo(null);
        setVisible(true);

    }


    private void clearFields() {
        nameField.setText("");
        surnameField.setText("");
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
    
    // validate and submit method:flow control
  private void validateAndSubmit() {
    String name = nameField.getText().trim();
    String surname= surnameField.getText().trim();
    String ageText = ageField.getText().trim();
    String city = cityField.getText().trim();

    if (isAnyFieldEmpty(name,surname, ageText, city)) {
        showError("Please fill in all fields.");
        return;
    }

    Integer age = parseAndValidateAge(ageText);
    if (age == null) {
        return;
    }

    showSuccessMessage(name,surname, age, city);
}
//space control method
    private boolean isAnyFieldEmpty(String name,String surname, String ageText, String city) {
        return name.isEmpty() || surname.isEmpty() || ageText.isEmpty() || city.isEmpty();
    }
    //age parse + validate method
    private Integer parseAndValidateAge(String ageText) {
    try {
        int age = Integer.parseInt(ageText);

        if (age < 0 || age > 150) {
            showError("Please enter a valid age");
            return null;
        }

        return age;

    } catch (NumberFormatException e) {
        showError("Please enter a valid number for age");
        return null;
    }
}
//show success
private void showSuccessMessage(String name,String surname, int age, String city) {
    outputLabel.setForeground(new Color(0, 102, 204));
    outputLabel.setText(
        "Hello " + name + " " + surname + ", you are " + age + " years old and you live in " + city
    );
}
//show errors next
    private void showError(String message) {
        outputLabel.setForeground(Color.RED);
        outputLabel.setText(message);
    }
    
    public static void main(String[] args) {
        // Run GUI in Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new MyGUI());
    }
}