package com.batchexample.bootbatch.cconfg;

import com.batchexample.bootbatch.model.Product;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CustomeItemProcessor implements ItemProcessor<Product , Product> {

    @Override
    public Product process(Product item) throws Exception {
        // Parse the discount and price fields
        BigDecimal discountPercentage = item.getDiscount();  // Assuming it's stored as a percentage, e.g., 10 for 10%
        BigDecimal originalPrice = item.getPrice();

        // Calculate the discount amount and the final price
        BigDecimal discountAmount = originalPrice.multiply(discountPercentage).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
        BigDecimal finalPrice = originalPrice.subtract(discountAmount);

        // Set the final price as the discounted price
        item.setDiscountedPrice(finalPrice);

        // Return the processed item
        return item;
    }
}
