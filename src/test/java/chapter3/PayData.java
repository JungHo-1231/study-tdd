package chapter3;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayData {
    private LocalDate firstBillingDate;
    private LocalDate billingData;
    private int payAmount;
}
