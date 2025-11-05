package PharmacyApp;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AppClass {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private DefaultTableModel medModel;
    private JTable medTable;
    private Medicine[] meds;

    public void show() {
        // Создание окна
        frame = new JFrame("ПК Администратора аптеки");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);


        tabbedPane = new JTabbedPane();

        //  Вкладка Лекарства
        JPanel medsPanel = new JPanel(new BorderLayout());
        String[] medColumns = {"Название", "Цена"};
        Object[][] medData = {
            {"Парацетамол", "50 ₽"},
            {"Ибупрофен", "80 ₽"}
        };

        // Модель и таблица
        medModel = new DefaultTableModel(medData, medColumns);
        medTable = new JTable(medModel);

        // Верхние кнопки
        JButton medAdd = new JButton("Добавить");
        JButton medRedact = new JButton("Редактировать");
        JButton medDelete = new JButton("Удалить");

        JPanel medButtons = new JPanel();
        medButtons.add(medAdd);
        medButtons.add(medRedact);
        medButtons.add(medDelete);

        medsPanel.add(medButtons, BorderLayout.NORTH);
        medsPanel.add(new JScrollPane(medTable), BorderLayout.CENTER);


        medAdd.addActionListener(e -> openAddMedicineDialog());

        // Вкладка Болезни
        JPanel disPanel = new JPanel(new BorderLayout());
        String[] disColumns = {"Название", "Симптомы"};
        Object[][] disData = {
            {"Грипп", "Температура, кашель"},
            {"Мигрень", "Головная боль"}
        };

        JTable disTable = new JTable(new DefaultTableModel(disData, disColumns));
        JButton disAdd = new JButton("Добавить");
        JButton disRedact = new JButton("Редактировать");
        JButton disDelete = new JButton("Удалить");

        JPanel disButtons = new JPanel();
        disButtons.add(disAdd);
        disButtons.add(disRedact);
        disButtons.add(disDelete);
        disPanel.add(disButtons, BorderLayout.NORTH);
        disPanel.add(new JScrollPane(disTable), BorderLayout.CENTER);

        // Вкладка Поиск
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Поиск по названию:");
        JTextField searchField = new JTextField(15);
        JButton searchButton = new JButton("Найти");
        searchPanel.add(label);
        searchPanel.add(Box.createVerticalStrut(10)); // отступ
        searchPanel.add(searchField);
        searchPanel.add(Box.createVerticalStrut(10));
        searchPanel.add(searchButton);

        // вкладки
        tabbedPane.addTab("Лекарства", medsPanel);
        tabbedPane.addTab("Болезни", disPanel);
        tabbedPane.addTab("Поиск", searchPanel);

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }


    /** Диалог добавления нового лекарства */
    private void openAddMedicineDialog() {
        JDialog dialog = new JDialog(frame, "Добавить лекарство", true);
        dialog.setLayout(new GridLayout(3, 2, 10, 10));
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(frame);

        JLabel nameLabel = new JLabel("Название:");
        JTextField nameField = new JTextField();

        JLabel priceLabel = new JLabel("Цена:");
        JTextField priceField = new JTextField();

        JButton okButton = new JButton("Добавить");
        JButton cancelButton = new JButton("Отмена");

        dialog.add(nameLabel);
        dialog.add(nameField);
        dialog.add(priceLabel);
        dialog.add(priceField);
        dialog.add(okButton);
        dialog.add(cancelButton);

        okButton.addActionListener(e -> {
            try {
                // Проверяем корректность данных
                validateMedicineInput(nameField.getText(), priceField.getText());

                // Если ошибок нет — добавляем строку
                medModel.addRow(new Object[]{
                    nameField.getText().trim(),
                    priceField.getText().trim()
                });

                dialog.dispose();
            } catch (InvalidInputException ex) {
                // Если есть ошибка — показываем пользователю сообщение
                JOptionPane.showMessageDialog(dialog, ex.getMessage(),
                        "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    
    // Проверка корректности введённых данных 
    private void validateMedicineInput(String name, String price) throws InvalidInputException {
        if (name.trim().isEmpty()) {
            throw new InvalidInputException("Поле 'Название' не может быть пустым!");
        }
        
        if (!name.matches("[a-zA-Zа-яА-ЯёЁ\\s-]+")) {
            throw new InvalidInputException("Название должно содержать только буквы!");
        }
        
        if (price.trim().isEmpty()) {
            throw new InvalidInputException("Поле 'Цена' не может быть пустым!");
        }


        try {
            Double.parseDouble(price.replace("₽", "").trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Цена должна быть числом!");
        }
    }


    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test_persistence");
        EntityManager em = emf.createEntityManager();
        
        System.out.println("Database is successfully connected!");
        
        em.getTransaction().begin();
        
       // Medicine med = new Medicine();
       // med.setName("Ацикловир");
       // med.setQuantity(10);
       // med.setCost(50.0f);

       // em.persist(med);
       // em.getTransaction().commit();

       // System.out.println("New Medicine name is " + med.getName());
        Medicine med = em.find(Medicine.class, 1);
        System.out.println("Found! Medicine name is " + med.getName());
        
        SwingUtilities.invokeLater(() -> new AppClass().show());
    }
}
