import java.math.BigDecimal;

public class LaundryService extends HotelService {
    private int numberOfItems;
    private BigDecimal pricePerItem;

    public LaundryService(String serviceId, String description, BigDecimal baseCost, int numberOfItems, BigDecimal pricePerItem) {
        super(serviceId, description, baseCost);
        this.numberOfItems = numberOfItems;
        this.pricePerItem = pricePerItem;
    }

    @Override
    public BigDecimal calculateFinalCost() {
        // Convert the integer 'numberOfItems' into a BigDecimal so we can do math with it
        BigDecimal itemsDecimal = BigDecimal.valueOf(this.numberOfItems);
        BigDecimal clothingCost = this.pricePerItem.multiply(itemsDecimal);

        return this.baseCost.add(clothingCost);
    }

    @Override
    public String toString() {
        return String.format("LaundryService || ID: %s, Desc: %s, Base: $%s, Items: %d, Cost/Item: $%s, Final Cost: $%s ||",
                serviceId, description, baseCost, numberOfItems, pricePerItem, calculateFinalCost());
    }
}