package kitchenpos.domain;

import java.math.BigDecimal;
import java.util.List;

public class Products {

    private final List<Product> values;

    public Products(final List<Product> values) {
        this.values = values;
    }

    public BigDecimal calculateSum(final List<Integer> counts) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int index = 0; index < values.size(); index++) {
            sum = sum.add(values.get(index).getPrice()
                    .multiply(BigDecimal.valueOf(counts.get(index))));
        }
        return sum;
    }
}