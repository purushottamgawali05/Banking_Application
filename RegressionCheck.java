import service.impl.BankServiceImpl;
import exceptions.ValidationException;

public class RegressionCheck {
    public static void main(String[] args) {
        BankServiceImpl service = new BankServiceImpl();

        try {
            service.openAccount("Alice", "bad-email", "CURRENT");
            throw new AssertionError("Invalid email should have been rejected");
        } catch (ValidationException e) {
            System.out.println("PASS: invalid email rejected");
        }

        try {
            service.openAccount("Bob", "bob@example.com", "LOAN");
            throw new AssertionError("Invalid account type should have been rejected");
        } catch (ValidationException e) {
            System.out.println("PASS: invalid account type rejected");
        }

        String acc = service.openAccount("Charlie", "charlie@example.com", "saving");
        service.deposit(acc, 250.0, "salary");
        service.withdraw(acc, 50.0, "rent");
        System.out.println("PASS: valid account flow works, balance=" + service.listAccounts().get(0).getBalance());
    }
}
