import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SpaTreatment extends HotelService implements Bookable {
    private BigDecimal tipPercentage;
    private List<LocalDate> bookedAppointments;

    public SpaTreatment(String serviceId, String description, BigDecimal baseCost, BigDecimal tipPercentage) {
        super(serviceId, description, baseCost);
        this.tipPercentage = tipPercentage;
        this.bookedAppointments = new ArrayList<>();
    }

    @Override
    public BigDecimal calculateFinalCost() {
        BigDecimal tipAmount = this.baseCost.multiply(this.tipPercentage);
        return this.baseCost.add(tipAmount);
    }

    @Override
    public boolean isBooked(LocalDate start, LocalDate end) {
        for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
            if (bookedAppointments.contains(date)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void markAsBooked(LocalDate start, LocalDate end) {
        for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
            bookedAppointments.add(date);
        }
    }

    @Override
    public String toString() {
        BigDecimal displayTip = tipPercentage.multiply(new BigDecimal("100"));
        return String.format("SpaTreatment || ID: %s, Desc: %s, Base: $%s, Tip: %s%%, Final Cost: $%s ||",
                serviceId, description, baseCost, displayTip, calculateFinalCost());
    }
}