import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BankAccount {
    Account account = new Account();;
    JFrame frame;
    JPanel panel;
    JLabel balanceLabel;
    JTextField amount;
    JButton addButton;
    JButton withdrawButton;
    JTextArea message;
    double balance;
    boolean withdraw;

    public BankAccount() {
        frame = new JFrame();
        panel = new JPanel();
        balanceLabel = new JLabel();
        amount = new JTextField();
        addButton = new JButton("Добавить");
        withdrawButton = new JButton("Снять");
        message = new JTextArea();
    }

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.start();
    }

    public void start() {
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setLayout(null);
        panel.setBounds(5,5,230,300);
        panel.setBackground(Color.gray);

        balanceLabel = createBalanceLabel();
        amount = createAmount();
        addButton = createAddButton();
        addButton.addActionListener(new AddButtonListener());
        withdrawButton = createWithdrawButton();
        withdrawButton.addActionListener(new WithdrawButtonListener());
        message = createMessage();

        JScrollPane scroller = new JScrollPane(message);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroller.setBounds(5,170, 220, 120);
        scroller.getViewport().setBackground(Color.red);
        scroller.getViewport().add(message);

        panel.add(balanceLabel);
        panel.add(amount);
        panel.add(addButton);
        panel.add(withdrawButton);
        panel.add(scroller);

        frame.add(panel);
        frame.setSize(240, 340);
        frame.setVisible(true);
    }

    class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String input = amount.getText();
            double value = Double.parseDouble(input);
            account.addAmount(value);
            balance = account.getBalance();
            balanceLabel.setText("Баланс: " + balance + "$");
            message.append("На ваш счет поступило: " + input + "$\n");
            amount.setText("");
        }
    }

    class WithdrawButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String input = amount.getText();
            Double value = Double.parseDouble(input);
            withdraw = account.withdrawAmount(value);
            if(withdraw == true) {
                balance = account.getBalance();
                balanceLabel.setText("Баланс: " + balance + "$");
                message.append("Вы сняли со счета: " + input + "$\n");
                amount.setText("");
            } else {
                message.append("\nОшибка:\n");
                message.append("Недостаточно средств.\n");
                message.append("Проверьте баланс.\n\n");
                amount.setText("");
            }

        }
    }

    public JLabel createBalanceLabel() {
        balanceLabel.setText("Баланс: " + account.getBalance() + "$");
        balanceLabel.setBounds(10,10, 100, 30);
        return balanceLabel;
    }

    public JTextField createAmount() {
        amount.setBounds(65, 50, 100, 30);
        return amount;
    }

    public JButton createAddButton() {
        addButton.setBounds(65, 90, 100, 30);
        return addButton;
    }

    public JButton createWithdrawButton() {
        withdrawButton.setBounds(65, 130, 100, 30);
        return withdrawButton;
    }

    public JTextArea createMessage() {
        message.setBounds(5,170, 220, 120);
        return message;
    }
}