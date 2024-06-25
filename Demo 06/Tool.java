public class Tool {
    private String code;
    private String type;
    private String brand;
    private double dailyCharge;
    private boolean chargeOnWeekends;
    private boolean chargeOnHolidays;

    public Tool(String code, String type, String brand, double dailyCharge, boolean chargeOnWeekends, boolean chargeOnHolidays) {
        this.code = code;
        this.type = type;
        this.brand = brand;
        this.dailyCharge = dailyCharge;
        this.chargeOnWeekends = chargeOnWeekends;
        this.chargeOnHolidays = chargeOnHolidays;
    }
    public String getCode() { return code; }
    public String getType() { return type; }
    public String getBrand() { return brand; }
    public double getDailyCharge() { return dailyCharge; }
    public boolean isChargeOnWeekends() { return chargeOnWeekends; }
    public boolean isChargeOnHolidays() { return chargeOnHolidays; }
}
