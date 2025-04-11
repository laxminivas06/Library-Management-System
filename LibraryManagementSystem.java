import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class LibraryManagementSystem extends JFrame implements ActionListener {
    // Login components
    private JPanel loginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    // Library management components
    private JPanel libraryPanel;
    private JTextField bookIdField, bookNameField, authorField, linkField, coverPageField;
    private JTextArea outputArea, statsArea;
    private JButton addButton, deleteButton, viewButton, searchButton, statisticsButton;

    // Footer acknowledgment
    private JLabel footerLabel;

    // Data storage
    private ArrayList<String> books = new ArrayList<>();
    private String recentSearch = "";
    private String recentDelete = "";
    private int bookCount = 0;
    private int deleteCount = 0;

    public LibraryManagementSystem() {
        // Frame setup
        setTitle("Library Management System");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        // Initialize login and library panels
        initializeLoginPanel();
        add(loginPanel);

        initializeLibraryPanel();
        setVisible(true);
    }

    private void initializeLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(new Color(200, 230, 250)); // Light blue background

        int centerX = 450, centerY = 250;

        JLabel loginLabel = new JLabel("Library Management Login", SwingConstants.CENTER);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 28));
        loginLabel.setForeground(Color.DARK_GRAY);
        loginLabel.setBounds(centerX - 250, centerY - 150, 500, 40);
        loginPanel.add(loginLabel);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameField.setBounds(centerX - 200, centerY - 60, 400, 40);
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        loginPanel.add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordField.setBounds(centerX - 200, centerY, 400, 40);
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        loginPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setBackground(new Color(135, 206, 250)); // Light blue
        loginButton.setBounds(centerX - 100, centerY + 80, 200, 50);
        loginButton.addActionListener(this);
        loginPanel.add(loginButton);
    }

    private void initializeLibraryPanel() {
        libraryPanel = new JPanel(new BorderLayout());
        libraryPanel.setBackground(new Color(255, 250, 230)); // Creamy white

        // Header for input fields
        JPanel headerPanel = new JPanel(new GridLayout(7, 2, 10, 10)); // Adjust grid for additional field
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        headerPanel.setBackground(new Color(230, 240, 255)); // Light lavender

        bookIdField = createInputField("Book ID");
        bookNameField = createInputField("Book Name");
        authorField = createInputField("Author Name");
        linkField = createInputField("Online Link (Optional)");
        coverPageField = createInputField("Cover Page Link or Upload"); // New field

        headerPanel.add(new JLabel("Book ID:"));
        headerPanel.add(bookIdField);
        headerPanel.add(new JLabel("Book Name:"));
        headerPanel.add(bookNameField);
        headerPanel.add(new JLabel("Author Name:"));
        headerPanel.add(authorField);
        headerPanel.add(new JLabel("Online Link:"));
        headerPanel.add(linkField);
        headerPanel.add(new JLabel("Cover Page:")); // New label
        headerPanel.add(coverPageField); // New input field

        libraryPanel.add(headerPanel, BorderLayout.NORTH);

        // Footer section for buttons and acknowledgment
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(240, 248, 255)); // Alice blue

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(new Color(240, 248, 255)); // Alice blue

        addButton = createButton("Add 📚");
        deleteButton = createButton("Delete ❌");
        viewButton = createButton("View 📖");
        searchButton = createButton("Search 🔍");
        statisticsButton = createButton("Statistics 📊");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(statisticsButton);

        footerPanel.add(buttonPanel, BorderLayout.NORTH);

        footerLabel = new JLabel("Developed by: Laxmi Nivas Morishetty | Roll No: 23N81A62B0 | CSE-CS 2B | Dec 2024| JAVA MINI PROJECT | UNDER THE GUIDANCE OF MR R.ANBARASU Ass.Prof|", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
        footerPanel.add(footerLabel, BorderLayout.SOUTH);

        libraryPanel.add(footerPanel, BorderLayout.SOUTH);

        // Output area
        outputArea = new JTextArea(8, 30);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 16));
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createTitledBorder("Output"));
        JScrollPane outputScrollPane = new JScrollPane(outputArea);

        libraryPanel.add(outputScrollPane, BorderLayout.CENTER);

        // Stats area
        statsArea = new JTextArea(4, 30);
        statsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        statsArea.setEditable(false);
        statsArea.setBorder(BorderFactory.createTitledBorder("Statistics"));
        JScrollPane statsScrollPane = new JScrollPane(statsArea);

        libraryPanel.add(statsScrollPane, BorderLayout.EAST);
    }

    private JTextField createInputField(String placeholder) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setBorder(BorderFactory.createTitledBorder(placeholder));
        return textField;
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(135, 206, 250)); // Light blue
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(70, 130, 180)); // Steel blue
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(135, 206, 250)); // Light blue
            }
        });

        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("Laxmi") && password.equals("9059")) {
                getContentPane().removeAll();
                getContentPane().add(libraryPanel);
                revalidate();
                repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login credentials. Try again!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == addButton) {
            String bookDetails = "ID: " + bookIdField.getText() + ", Name: " + bookNameField.getText() +
                    ", Author: " + authorField.getText() + ", Link: " + linkField.getText() +
                    ", Cover Page: " + coverPageField.getText(); // Include cover page
            books.add(bookDetails);
            bookCount++;
            updateStats();
            outputArea.append("Book Added: " + bookDetails + "\n");
        } else if (e.getSource() == deleteButton) {
            String bookId = bookIdField.getText();
            boolean deleted = false;
            for (String book : books) {
                if (book.contains("ID: " + bookId)) {
                    books.remove(book);
                    deleted = true;
                    deleteCount++;
                    recentDelete = book;
                    updateStats();
                    outputArea.append("Book Deleted: " + book + "\n");
                    break;
                }
            }
            if (!deleted) {
                JOptionPane.showMessageDialog(this, "Book ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == viewButton) {
            outputArea.setText("");
            for (String book : books) {
                outputArea.append(book + "\n");
            }
        } else if (e.getSource() == searchButton) {
            String searchQuery = JOptionPane.showInputDialog("Enter Book ID to Search:");
            recentSearch = searchQuery;
            outputArea.append("Searching for Book ID: " + searchQuery + "\n");
            boolean found = false;
            for (String book : books) {
                if (book.contains("ID: " + searchQuery)) {
                    outputArea.append("Found: " + book + "\n");
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "Book not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == statisticsButton) {
            updateStats();
        }
    }

    private void updateStats() {
        statsArea.setText("");
        statsArea.append("Total Books: " + bookCount + "\n");
        statsArea.append("Deleted Books: " + deleteCount + "\n");
        statsArea.append("Recent Search: " + recentSearch + "\n");
        statsArea.append("Recent Delete: " + recentDelete + "\n");
    }

    public static void main(String[] args) {
        new LibraryManagementSystem();
    }
}