import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {
    @Test
    void test1() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Checkout.checkout("JAKR", 5, 101, LocalDate.of(2015, 9, 3));
        });
        assertEquals("Discount percent must be between 0 and 100.", exception.getMessage());
    }

    @Test
    void test2() {
        RentalAgreement agreement = Checkout.checkout("LADW", 3, 10, LocalDate.of(2020, 7, 2));
        agreement.print();
    }

    @Test
    void test3() {
        RentalAgreement agreement = Checkout.checkout("CHNS", 5, 25, LocalDate.of(2015, 7, 2));
        agreement.print();
    }

    @Test
    void test4() {
        RentalAgreement agreement = Checkout.checkout("JAKD", 6, 0, LocalDate.of(2015, 9, 3));
        agreement.print();
    }

    @Test
    void test5() {
        RentalAgreement agreement = Checkout.checkout("JAKR", 9, 0, LocalDate.of(2015, 7, 2));
        agreement.print();
    }

    @Test
    void test6() {
        RentalAgreement agreement = Checkout.checkout("JAKR", 4, 50, LocalDate.of(2020, 7, 2));
        agreement.print();
    }
}
