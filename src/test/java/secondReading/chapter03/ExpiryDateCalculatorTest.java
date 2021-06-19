package secondReading.chapter03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 유료 구독 서비스
 *
 * - 서비스를 사용하려면 매달 1만 원을 선불로 납부한다.
 *      납부일 기준으로 한 달 뒤가 서비스 만료일이 된다.
 * - 2개월 이상 요금을 납부할 수 있다.
 * - 10만 원을 납부하면 서비스를 1년 제공한다.
 */
public class ExpiryDateCalculatorTest {


    @Test
    void 만원_납부하면_한달_뒤_만료일이_됨() throws Exception{

        int payAmount = 10_000;
        LocalDate billingDate = LocalDate.of(2019, 3, 1);
        LocalDate expectedExpiryDate = LocalDate.of(2019,4,1);

        PayDate payDate = PayDate.builder()
                .billingDate(billingDate)
                .payAmount(payAmount)
                .build();

        assertExpiryDate(payDate, expectedExpiryDate);


        int payAmount2 = 10_000;
        LocalDate billingDate2 = LocalDate.of(2019, 5, 5);
        LocalDate expectedExpiryDate2 = LocalDate.of(2019, 6, 5);

        PayDate payDate2 = PayDate.builder()
                .billingDate(billingDate2)
                .payAmount(payAmount2)
                .build();

        assertExpiryDate(payDate2,expectedExpiryDate2);
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() throws Exception{
        LocalDate billingDate = LocalDate.of(2019, 1, 31);
        int payAmount = 10_000;
        LocalDate expectedExpiryDate = LocalDate.of(2019, 2, 28);

        PayDate payDate = PayDate.builder()
                .billingDate(billingDate)
                .payAmount(payAmount)
                .build();

        assertExpiryDate(payDate, expectedExpiryDate);
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부() throws Exception{
        LocalDate firstBillingDate = LocalDate.of(2019, 01, 31);
        int payAmount = 10_000;
        LocalDate billingDate = LocalDate.of(2019, 02, 28);
        LocalDate expectedExpiryDate = LocalDate.of(2019, 03, 31);


        PayDate payDate = PayDate.builder()
                .firstBillingDate(firstBillingDate)
                .billingDate(billingDate)
                .payAmount(payAmount)
                .build();

        assertExpiryDate(payDate, expectedExpiryDate);


        PayDate payDate2 = PayDate.builder()
                .firstBillingDate(LocalDate.of(2019, 5, 31))
                .billingDate(LocalDate.of(2019, 6, 30))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payDate2, LocalDate.of(2019,7,31));
    }

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산() throws Exception{
        LocalDate billingDate = LocalDate.of(2019, 3, 1);
        int payAmount = 20_000;
        LocalDate expectedExpiryDate = LocalDate.of(2019, 5, 1);

        PayDate payDate = PayDate.builder()
                .billingDate(billingDate)
                .payAmount(payAmount)
                .build();

        assertExpiryDate(payDate, expectedExpiryDate);


        PayDate payDate2 = PayDate.builder()
                .billingDate(LocalDate.of(2019, 3,1))
                .payAmount(30_000)
                .build();

        assertExpiryDate(payDate2, LocalDate.of(2019,6,1));
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() throws Exception{
        PayDate payDate = PayDate.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(20_000)
                .build();

        LocalDate expectedExpiryDate = LocalDate.of(2019, 4, 30);

        assertExpiryDate(payDate, expectedExpiryDate);
    }

    @Test
    void 십만원_납부하면_1년_제공() throws Exception{
        LocalDate billingDate = LocalDate.of(2019, 1, 28);
        int payAmount = 100_000;
        LocalDate expectedExpiryDate = LocalDate.of(2020, 1, 28);

        PayDate payDate = PayDate.builder()
                .billingDate(billingDate)
                .payAmount(payAmount)
                .build();

        assertExpiryDate(payDate, expectedExpiryDate);
    }


    private void assertExpiryDate(PayDate payDate, LocalDate expectedExpiryDate){

        ExpiryDateCalculator cal = new ExpiryDateCalculator();

        LocalDate expiryDate = cal.calculateExpiryDate(payDate);
        assertEquals(expiryDate, expectedExpiryDate);
    }




}
