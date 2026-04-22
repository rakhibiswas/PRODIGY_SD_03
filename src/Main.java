//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.*;
import java.util.*;

class Contact implements Serializable {
    String name;
    String phone;
    String email;

    Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String toString() {
        return "Name: " + name + ", Phone: " + phone + ", Email: " + email;
    }
}

public class Main{

    static final String FILE_NAME = "contacts.dat";

    // Load contacts from file
    static List<Contact> loadContacts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Contact>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // Save contacts to file
    static void saveContacts(List<Contact> contacts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            System.out.println("Error saving contacts.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Contact> contacts = loadContacts();

        while (true) {
            System.out.println("\n--- Contact Management System ---");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Edit Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    contacts.add(new Contact(name, phone, email));
                    saveContacts(contacts);
                    System.out.println("Contact Added!");
                    break;

                case 2:
                    if (contacts.isEmpty()) {
                        System.out.println("No contacts found.");
                    } else {
                        for (int i = 0; i < contacts.size(); i++) {
                            System.out.println((i + 1) + ". " + contacts.get(i));
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter contact number to edit: ");
                    int editIndex = sc.nextInt() - 1;
                    sc.nextLine();

                    if (editIndex >= 0 && editIndex < contacts.size()) {
                        System.out.print("Enter new Name: ");
                        contacts.get(editIndex).name = sc.nextLine();
                        System.out.print("Enter new Phone: ");
                        contacts.get(editIndex).phone = sc.nextLine();
                        System.out.print("Enter new Email: ");
                        contacts.get(editIndex).email = sc.nextLine();

                        saveContacts(contacts);
                        System.out.println("Contact Updated!");
                    } else {
                        System.out.println("Invalid contact number.");
                    }
                    break;

                case 4:
                    System.out.print("Enter contact number to delete: ");
                    int deleteIndex = sc.nextInt() - 1;

                    if (deleteIndex >= 0 && deleteIndex < contacts.size()) {
                        contacts.remove(deleteIndex);
                        saveContacts(contacts);
                        System.out.println("Contact Deleted!");
                    } else {
                        System.out.println("Invalid contact number.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}




