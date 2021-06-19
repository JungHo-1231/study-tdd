package secondReading.chapter03;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayDate payDate) {

        int payAmount = payDate.getPayAmount();
        int addedMonths = payAmount / 10_000;

        if (payDate.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payDate, addedMonths);

        } else {
            return payDate.getBillingDate().plusMonths(addedMonths);
        }
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayDate payDate, int addedMonths) {

        LocalDate candidateExp = payDate.getBillingDate().plusMonths(addedMonths);

        final int dayOfFirstBilling = payDate.getFirstBillingDate().getDayOfMonth();

        if (dayOfFirstBilling != candidateExp.getDayOfMonth()) {

            final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();

            /** 후보 만료일이 포함된 달의 마지막 날  < 첫 납부일의 일자 */
            if (dayLenOfCandiMon < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }

            return candidateExp.withDayOfMonth(dayOfFirstBilling);

        } else {
            return candidateExp;
        }
    }
}
