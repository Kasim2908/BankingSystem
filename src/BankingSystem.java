import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

class Account {
    String name, gender, dob, phone, email, accountNumber;
    double balance;
    ArrayList<String> transactionHistory; // Store transaction history

    public Account(String name, String gender, String dob, String phone, String email, String accountNumber) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.balance = 0.0;
        this.accountNumber = accountNumber;
        this.transactionHistory = new ArrayList<>();
    }

    // Add a transaction to the history
    public void addTransaction(String transactionDetails) {
        transactionHistory.add(transactionDetails);
    }

    // Get transaction history as a formatted string
    public String getTransactionHistory() {
        StringBuilder history = new StringBuilder();
        for (String transaction : transactionHistory) {
            history.append(transaction).append("\n");
        }
        return history.toString();
    }
}

public class BankingSystem extends Frame implements ActionListener {

    Label title, nameLabel, genderLabel, dobLabel, phoneLabel, emailLabel, infoLabel, totalAccountsLabel;
    TextField nameField, phoneField, emailField, amountField;
    Choice dayChoice, monthChoice, yearChoice;
    CheckboxGroup genderGroup;
    Checkbox male, female, other;
    Button openAccountBtn, depositBtn, withdrawBtn, balanceBtn, accountInfoBtn;
    TextArea outputArea;

    ArrayList<Account> accounts = new ArrayList<>();
    Account currentAccount = null;

    BankingSystem() {
        setTitle("Java AWT Banking System");
        setLayout(null);
        setSize(600, 600);

        title = new Label("Banking System");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(200, 30, 200, 30);
        add(title);

        int y = 80;

        nameLabel = new Label("Name:");
        nameLabel.setBounds(30, y, 100, 20);
        add(nameLabel);

        nameField = new TextField();
        nameField.setBounds(140, y, 200, 20);
        add(nameField);

        y += 30;

        genderLabel = new Label("Gender:");
        genderLabel.setBounds(30, y, 100, 20);
        add(genderLabel);

        genderGroup = new CheckboxGroup();
        male = new Checkbox("Male", genderGroup, true);
        female = new Checkbox("Female", genderGroup, false);
        other = new Checkbox("Other", genderGroup, false);
        male.setBounds(140, y, 60, 20);
        female.setBounds(210, y, 70, 20);
        other.setBounds(290, y, 60, 20);
        add(male);
        add(female);
        add(other);

        y += 30;

        dobLabel = new Label("Date of Birth:");
        dobLabel.setBounds(30, y, 100, 20);
        add(dobLabel);

        dayChoice = new Choice();
        for (int i = 1; i <= 31; i++) dayChoice.add(String.valueOf(i));
        dayChoice.setBounds(140, y, 50, 20);
        add(dayChoice);

        monthChoice = new Choice();
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (String m : months) monthChoice.add(m);
        monthChoice.setBounds(200, y, 60, 20);
        add(monthChoice);

        yearChoice = new Choice();
        for (int i = 1980; i <= 2025; i++) yearChoice.add(String.valueOf(i));
        yearChoice.setBounds(270, y, 70, 20);
        add(yearChoice);

        y += 30;

        phoneLabel = new Label("Phone:");
        phoneLabel.setBounds(30, y, 100, 20);
        add(phoneLabel);

        phoneField = new TextField();
        phoneField.setBounds(140, y, 200, 20);
        phoneField.addKeyListener(new KeyAdapter() {
            // Restrict phone number to 10 digits
            public void keyTyped(KeyEvent e) {
                String text = phoneField.getText();
                if (text.length() >= 10) {
                    e.consume(); // Prevent more input
                }
            }
        });
        add(phoneField);

        y += 30;

        emailLabel = new Label("Email:");
        emailLabel.setBounds(30, y, 100, 20);
        add(emailLabel);

        emailField = new TextField();
        emailField.setBounds(140, y, 200, 20);
        add(emailField);

        y += 40;

        openAccountBtn = new Button("Open New Account");
        openAccountBtn.setBounds(30, y, 150, 30);
        openAccountBtn.addActionListener(this);
        add(openAccountBtn);

        depositBtn = new Button("Deposit");
        depositBtn.setBounds(190, y, 80, 30);
        depositBtn.addActionListener(this);
        add(depositBtn);

        withdrawBtn = new Button("Withdraw");
        withdrawBtn.setBounds(280, y, 80, 30);
        withdrawBtn.addActionListener(this);
        add(withdrawBtn);

        balanceBtn = new Button("Check Balance");
        balanceBtn.setBounds(370, y, 100, 30);
        balanceBtn.addActionListener(this);
        add(balanceBtn);

        accountInfoBtn = new Button("Account Info");
        accountInfoBtn.setBounds(480, y, 100, 30);
        accountInfoBtn.addActionListener(this);
        add(accountInfoBtn);

        y += 50;

        amountField = new TextField();
        amountField.setBounds(30, y, 200, 25);
        amountField.setText("Enter amount here");
        add(amountField);

        totalAccountsLabel = new Label("Total Accounts: 0");
        totalAccountsLabel.setBounds(250, y, 200, 25);
        add(totalAccountsLabel);

        y += 40;

        outputArea = new TextArea();
        outputArea.setBounds(30, y, 540, 200);
        add(outputArea);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Open New Account")) {
            String name = nameField.getText().trim();
            String gender = genderGroup.getSelectedCheckbox().getLabel();
            String dob = dayChoice.getSelectedItem() + "-" +
                    monthChoice.getSelectedItem() + "-" +
                    yearChoice.getSelectedItem();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();

            // Phone validation (only 10 digits allowed)
            if (phone.length() != 10 || !phone.matches("\\d{10}")) {
                outputArea.setText("Phone number must be exactly 10 digits.");
                return;
            }

            // Account number generation (12 digits)
            String accountNumber = generateAccountNumber();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                outputArea.setText("Please fill in all the details.");
                return;
            }

            // Create and add new account
            Account acc = new Account(name, gender, dob, phone, email, accountNumber);
            accounts.add(acc);
            currentAccount = acc;
            totalAccountsLabel.setText("Total Accounts: " + accounts.size());
            acc.addTransaction("Account opened with Account Number: " + accountNumber);
            outputArea.setText("New account opened for: " + name + "\nAccount Number: " + accountNumber);
        } else if (command.equals("Deposit")) {
            if (currentAccount == null) {
                outputArea.setText("No account selected.");
                return;
            }
            try {
                double amt = Double.parseDouble(amountField.getText());
                currentAccount.balance += amt;
                currentAccount.addTransaction("Deposited: ₹" + amt);
                outputArea.setText("Deposited: ₹" + amt + "\nNew Balance: ₹" + currentAccount.balance);
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid amount.");
            }
        } else if (command.equals("Withdraw")) {
            if (currentAccount == null) {
                outputArea.setText("No account selected.");
                return;
            }
            try {
                double amt = Double.parseDouble(amountField.getText());
                if (amt > currentAccount.balance) {
                    outputArea.setText("Insufficient balance.");
                } else {
                    currentAccount.balance -= amt;
                    currentAccount.addTransaction("Withdrawn: ₹" + amt);
                    outputArea.setText("Withdrawn: ₹" + amt + "\nRemaining Balance: ₹" + currentAccount.balance);
                }
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid amount.");
            }
        } else if (command.equals("Check Balance")) {
            if (currentAccount == null) {
                outputArea.setText("No account selected.");
            } else {
                outputArea.setText("Current Balance: ₹" + currentAccount.balance);
            }
        } else if (command.equals("Account Info")) {
            if (currentAccount == null) {
                outputArea.setText("No account selected.");
            } else {
                outputArea.setText(
                        "Name: " + currentAccount.name +
                                "\nGender: " + currentAccount.gender +
                                "\nDOB: " + currentAccount.dob +
                                "\nPhone: " + currentAccount.phone +
                                "\nEmail: " + currentAccount.email +
                                "\nAccount Number: " + currentAccount.accountNumber +
                                "\nBalance: ₹" + currentAccount.balance +
                                "\n\nTransaction History:\n" + currentAccount.getTransactionHistory()
                );
            }
        }
    }

    // Method to generate a random 12-digit account number
    public String generateAccountNumber() {
        Random rand = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            accountNumber.append(rand.nextInt(10));
        }
        return accountNumber.toString();
    }

    public static void main(String[] args) {
        new BankingSystem();
    }
}
