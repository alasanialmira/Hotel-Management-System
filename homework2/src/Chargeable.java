import java.math.BigDecimal;

public interface Chargeable {

    //Standard behavior for any entity that costs money
    BigDecimal getCost();
}