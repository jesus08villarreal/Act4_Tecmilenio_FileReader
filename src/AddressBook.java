import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AddressBook {
    private final Map<String, String> contacts;

    public AddressBook() {
        contacts = new HashMap<>();
    }

    public void load(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] contact = line.split(",");
                if (contact.length == 2) {
                    contacts.put(contact[0], contact[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void list() {
        System.out.println("Contactos:");
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public void create(String number, String name) {
        contacts.put(number, name);
    }

    public void delete(String number) {
        contacts.remove(number);
    }

    public Map<String, String> getContacts() {
        return contacts;
    }
}
