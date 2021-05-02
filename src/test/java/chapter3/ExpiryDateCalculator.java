package chapter3;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {


//           .getDayOfMonth()  월의 몇 번째 일
//           .withDayOfMonth(int)  월의 일을 변경
    public LocalDate calculateExpiryDate(PayData payData){
        int addedMonths = getAddedMonths(payData.getPayAmount());

        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        } else {
            return payData.getBillingData().plusMonths(addedMonths);
        }
    }

    private int getAddedMonths(int payAmount) {
        int addedMonths = 0;
        if (payAmount >= 100_000) {
            int ratioPerHundredThousand = payAmount / 100_000;
            addedMonths = (12 * ratioPerHundredThousand)
                    + (payAmount - (100_000 * ratioPerHundredThousand)) / 10_000;
        } else {
            addedMonths = payAmount / 10_000;
        }
        return addedMonths;
    }
//  출처 : https://github.com/ohjoohyung/tdd-practice/commit/32930e1789f65858fe224e3abf48408ee47b0343

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {

        if (payData.getFirstBillingDate() != null){
            LocalDate candidateExp = payData.getBillingData().plusMonths(addedMonths);

            int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();

            if (isSameDayOfMonth(candidateExp, dayOfFirstBilling)){
                int dayLenOfCandiMon = lastDayOfMonth(candidateExp);
                if(dayLenOfCandiMon < YearMonth.from(payData.getFirstBillingDate()).lengthOfMonth()){
                    return candidateExp.withDayOfMonth(dayLenOfCandiMon);
                }
                return candidateExp.withDayOfMonth(dayOfFirstBilling);
            } else {
                return candidateExp;
            }
        } else {
            return payData.getBillingData().plusMonths(addedMonths);
        }

    }

    private int lastDayOfMonth(LocalDate candidateExp) {
        return YearMonth.from(candidateExp).lengthOfMonth();
    }

    private boolean isSameDayOfMonth(LocalDate candidateExp, int dayOfFirstBilling) {
        return dayOfFirstBilling != candidateExp.getDayOfMonth();
    }
}
