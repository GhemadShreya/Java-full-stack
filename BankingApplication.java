package package1;

public class BankingApplication {

    public static void main(String[] args) {
        Bankuser myBank = new Bankuser(1000);

        Runnable depositTask = () -> myBank.deposit(500);
        Runnable withdrawTask = () -> myBank.withdraw(1000);
        Runnable checkBalanceTask = () -> myBank.checkBalance();

        Thread depositThread = new Thread(depositTask);
        Thread withdrawThread = new Thread(withdrawTask);
        Thread balanceThread = new Thread(checkBalanceTask);

        depositThread.start();
        withdrawThread.start();
        balanceThread.start();
        
            try {
            depositThread.join();
            withdrawThread.join();
            balanceThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Bankuser {
    private double balance;

    public Bankuser(double balance) {
        this.balance = balance;
    }

    public synchronized void deposit(double amount) {
        System.out.println(Thread.currentThread().getName() + " Depositing " + amount);
        this.balance += amount;
        System.out.println("Updated balance after Deposit: " + this.balance);
    }

    public synchronized void withdraw(double amount) {
        if (amount > this.balance) {
            System.out.println(Thread.currentThread().getName() + " Insufficient Balance... transaction failed");
        } else {
            System.out.println(Thread.currentThread().getName() + " Withdrawing Amount " + amount);
            this.balance -= amount;
            System.out.println("Updated balance after Withdraw: " + this.balance);
        }
    }

    public synchronized void checkBalance() {
        System.out.println(Thread.currentThread().getName() + " Checking balance: " + this.balance);
    }
}
