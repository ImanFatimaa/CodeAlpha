package codealphainternshiptasknumber1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bankingsystem {
    private static double balance = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Banking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to the Banking System!");
        welcomeLabel.setBounds(50, 10, 300, 25);
        panel.add(welcomeLabel);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(50, 50, 300, 25);
        panel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(50, 90, 300, 25);
        panel.add(withdrawButton);

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setBounds(50, 130, 300, 25);
        panel.add(checkBalanceButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(50, 170, 300, 25);
        panel.add(exitButton);

        JLabel statusLabel = new JLabel("");
        statusLabel.setBounds(50, 210, 300, 25);
        panel.add(statusLabel);

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog("Enter the amount to deposit:");
                try {
                    double amount = Double.parseDouble(amountStr);
                    deposit(amount, statusLabel);
                } catch (NumberFormatException ex) {
                    statusLabel.setText("Invalid amount. Please enter a valid number.");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog("Enter the amount to withdraw:");
                try {
                    double amount = Double.parseDouble(amountStr);
                    withdraw(amount, statusLabel);
                } catch (NumberFormatException ex) {
                    statusLabel.setText("Invalid amount. Please enter a valid number.");
                }
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBalance(statusLabel);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel, "Thank you for using the Banking System");
                System.exit(0);
            }
        });
    }

    private static void deposit(double amount, JLabel statusLabel) {
        if (amount > 0) {
            balance += amount;
            statusLabel.setText("Successfully deposited $" + amount);
        } else {
            statusLabel.setText("Invalid amount. Deposit failed.");
        }
    }

    private static void withdraw(double amount, JLabel statusLabel) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            statusLabel.setText("Successfully withdrew $" + amount);
        } else if (amount > balance) {
            statusLabel.setText("Insufficient balance. Withdrawal failed.");
        } else {
            statusLabel.setText("Invalid amount. Withdrawal failed.");
        }
    }

    private static void checkBalance(JLabel statusLabel) {
        statusLabel.setText("Your current balance is $" + balance);
    }
}
