package section4.exercise

class AccountDemo {
    static void main(String[] args) {
        Account checking = new Account(type:"Checking")
        checking.depositMoney(100.00)
        Account savings = new Account(type:"Savings")
        savings.depositMoney(50.00)

        BigDecimal total = checking + savings
        println total

        BigDecimal multiplyTotal = checking * savings
        println multiplyTotal
    }
}
