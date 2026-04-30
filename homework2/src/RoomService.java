import java.math.BigDecimal;

public class RoomService extends HotelService {

    private BigDecimal deliveryFee;

    public RoomService(String serviceId, String description, BigDecimal baseCost, BigDecimal deliveryFee) {

        super(serviceId, description, baseCost);
        this.deliveryFee = deliveryFee;
    }

    @Override
    public BigDecimal calculateFinalCost() {
        return this.baseCost.add(this.deliveryFee);
    }

    @Override
    public String toString() {
        return String.format("RoomService  || ID: %s, Desc: %s, Base: $%s, Delivery Fee: $%s, Final Cost: $%s ||",
                serviceId, description, baseCost, deliveryFee, calculateFinalCost());
    }
}