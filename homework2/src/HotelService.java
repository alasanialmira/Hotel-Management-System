import java.math.BigDecimal;

public abstract class HotelService implements Chargeable {

    protected String serviceId;
    protected String description;
    protected BigDecimal baseCost;

    public HotelService(String serviceId, String description, BigDecimal baseCost) {
        this.serviceId = serviceId;
        this.description = description;
        this.baseCost = baseCost;
    }

    public String getServiceId() { return serviceId; }
    public String getDescription() { return description; }
    public BigDecimal getBaseCost() { return baseCost; }

    public abstract BigDecimal calculateFinalCost();


    @Override
    public BigDecimal getCost() {
        return calculateFinalCost();
    }
}