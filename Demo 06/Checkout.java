import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Checkout {
    private static Map<String, Tool> tools = new HashMap<>();

    static {
        tools.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl", 1.49, false, true));
        tools.put("LADW", new Tool("LADW", "Ladder", "Werner", 1.99, true, false));
        tools.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, false, false));
        tools.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, false, false));
    }

    public static RentalAgreement checkout(String toolCode, int rentalDays, double discountPercent, LocalDate checkoutDate) throws IllegalArgumentException {
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater.");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100.");
        }
        Tool tool = tools.get(toolCode);
        if (tool == null) {
            throw new IllegalArgumentException("Invalid tool code.");
        }
        return new RentalAgreement(tool, rentalDays, checkoutDate, discountPercent);
    }
}
