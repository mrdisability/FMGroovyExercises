package section4.exercise

class Account {
    BigDecimal balance = 0;
    String type;

    void depositMoney(BigDecimal amount) {
        this.balance += amount;
    }

    void withdrawMoney(BigDecimal amount) {
        this.balance -= amount;
    }

    BigDecimal plus(Account account) {
        this.balance + account.balance;
    }

    BigDecimal multiply(Account account) {
        this.balance * account.balance;
    }
}
