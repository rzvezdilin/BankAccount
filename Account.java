class Account {
    private double balance = 100;

    public double addAmount(double amount) {
        balance += amount;
        return balance;
    }

    public boolean withdrawAmount(double amount) {
        boolean withdraw = false;
        if((balance - amount) >= 0) {
            balance -= amount;
            withdraw = true;
        }
        return withdraw;
    }

    public void setBalance(double amount) {
        balance = amount;
    }

    public double getBalance() {
        return balance;
    }
}