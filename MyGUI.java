import javax.swing.*;

public class MyGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("my personal GUI");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Ad Label & TextField
        JLabel nameLabel = new JLabel("name:");
        nameLabel.setBounds(20, 20, 100, 25);
        JTextField nameField = new JTextField();
        nameField.setBounds(100, 20, 250, 25);

        // Yaş Label & TextField
        JLabel ageLabel = new JLabel("age:");
        ageLabel.setBounds(20, 60, 100, 25);
        JTextField ageField = new JTextField();
        ageField.setBounds(100, 60, 250, 25);

        // Şehir Label & TextField
        JLabel cityLabel = new JLabel("city:");
        cityLabel.setBounds(20, 100, 100, 25);
        JTextField cityField = new JTextField();
        cityField.setBounds(100, 100, 250, 25);

        // Göster Label
        JLabel outputLabel = new JLabel("");
        outputLabel.setBounds(20, 180, 350, 25);

        // Buton
        JButton submitButton = new JButton("click me");
        submitButton.setBounds(140, 140, 100, 30);

        // Buton Olayı
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String age = ageField.getText();
            String city = cityField.getText();

            outputLabel.setText("hello " + name + ", you are " + age + " years old and you live in " + city);
        });

        // Bileşenleri ekleme
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(ageLabel);
        frame.add(ageField);
        frame.add(cityLabel);
        frame.add(cityField);
        frame.add(submitButton);
        frame.add(outputLabel);

        frame.setVisible(true);
    }
}
