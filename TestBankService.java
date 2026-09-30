import service.impl.BankServiceImpl;
import exceptions.ValidationException;

public class TestBankService {
    public static void main(String[] args) {
        BankServiceImpl service = new BankServiceImpl();
        try {
            service.openAccount("Alice", "bad-email", "CURRENT");
            System.out.println("UNEXPECTED: invalid email and account type were accepted");
            System.exit(1);
        } catch (ValidationException e) {
            System.out.println("Validation rejected invalid account input: " + e.getMessage());
        }
    }
}
