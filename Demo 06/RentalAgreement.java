import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

public class RentalAgreement {
    private Tool tool;
    private int rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private double discountPercent;
    private int chargeDays;
    private double preDiscountCharge;
    private double discountAmount;
    private double finalCharge;

    public RentalAgreement(Tool tool, int rentalDays, LocalDate checkoutDate, double discountPercent) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(rentalDays);
        this.discountPercent = discountPercent;
        calculateCharges();
    }

    private void calculateCharges() {
        chargeDays = calculateChargeDays(checkoutDate, dueDate);
        preDiscountCharge = chargeDays * tool.getDailyCharge();
        discountAmount = preDiscountCharge * (discountPercent / 100);
        finalCharge = preDiscountCharge - discountAmount;
    }

    private int calculateChargeDays(LocalDate start, LocalDate end) {
        int chargeableDays = 0;
        for (LocalDate date = start.plusDays(1); !date.isAfter(end); date = date.plusDays(1)) {
            if (isChargeableDay(date)) {
                chargeableDays++;
            }
        }
        return chargeableDays;
    }

    private boolean isChargeableDay(LocalDate date) {
        if (!tool.isChargeOnWeekends() && (date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7)) {
            return false;
        }
        if (!tool.isChargeOnHolidays() && isHoliday(date)) {
            return false;
        }
        return true;
    }

    private boolean isHoliday(LocalDate date) {
        int year = date.getYear();
        LocalDate independenceDay = LocalDate.of(year, 7, 4);
        if (independenceDay.getDayOfWeek().getValue() == 6) {
            independenceDay = independenceDay.minusDays(1);
        } else if (independenceDay.getDayOfWeek().getValue() == 7) {
            independenceDay = independenceDay.plusDays(1);
        }
        LocalDate laborDay = LocalDate.of(year, 9, 1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        return date.equals(independenceDay) || date.equals(laborDay);
    }

    public void print() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        System.out.println("Tool code: " + tool.getCode());
        System.out.println("Tool type: " + tool.getType());
        System.out.println("Tool brand: " + tool.getBrand());
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Check out date: " + checkoutDate.format(dateFormatter));
        System.out.println("Due date: " + dueDate.format(dateFormatter));
        System.out.println("Daily rental charge: $" + String.format("%.2f", tool.getDailyCharge()));
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: $" + String.format("%.2f", preDiscountCharge));
        System.out.println("Discount percent: " + String.format("%.0f", discountPercent) + "%");
        System.out.println("Discount amount: $" + String.format("%.2f", discountAmount));
        System.out.println("Final charge: $" + String.format("%.2f", finalCharge));
    }
}
