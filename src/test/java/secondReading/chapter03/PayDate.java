package secondReading.chapter03;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PayDate {
    private LocalDate firstBillingDate;
    private LocalDate billingDate;
    private int payAmount;
}
