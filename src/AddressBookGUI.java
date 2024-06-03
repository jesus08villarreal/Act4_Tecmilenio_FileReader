import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class AddressBookGUI {
    private final AddressBook addressBook;
    private JFrame frame;
    private JTextArea textArea;
    private JTextField numberField;
    private JTextField nameField;
    private JTextField deleteField;
    private static final String FILE_NAME = "db/contacts.csv";

    public AddressBookGUI() {
        addressBook = new AddressBook();
        addressBook.load(FILE_NAME);
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Address Book");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel numberLabel = new JLabel("Número:");
        numberField = new JTextField();
        JLabel nameLabel = new JLabel("Nombre:");
        nameField = new JTextField();
        JButton createButton = getjButton();

        JLabel deleteLabel = new JLabel("Número a eliminar:");
        deleteField = new JTextField();
        JButton deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(_ -> {
            String number = deleteField.getText();
            if (!number.isEmpty()) {
                addressBook.delete(number);
                updateTextArea();
                deleteField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Ingresa un número a eliminar para seguir.");
            }
        });

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(_ -> {
            addressBook.save(FILE_NAME);
            JOptionPane.showMessageDialog(frame, "Contactos guardados.");
        });

        panel.add(numberLabel);
        panel.add(numberField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(createButton);
        panel.add(new JLabel());
        panel.add(deleteLabel);
        panel.add(deleteField);
        panel.add(deleteButton);
        panel.add(new JLabel());
        panel.add(saveButton);

        frame.add(panel, BorderLayout.SOUTH);

        updateTextArea();

        frame.setVisible(true);
    }

    private JButton getjButton() {
        JButton createButton = new JButton("Crear");
        createButton.addActionListener(_ -> {
            String number = numberField.getText();
            String name = nameField.getText();
            if (!number.isEmpty() && !name.isEmpty()) {
                addressBook.create(number, name);
                updateTextArea();
                numberField.setText("");
                nameField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor ingresa los 2 campos.");
            }
        });
        return createButton;
    }

    private void updateTextArea() {
        StringBuilder builder = new StringBuilder();
        builder.append("Contactos:\n");
        for (Map.Entry<String, String> entry : addressBook.getContacts().entrySet()) {
            builder.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }
        textArea.setText(builder.toString());
    }

}
