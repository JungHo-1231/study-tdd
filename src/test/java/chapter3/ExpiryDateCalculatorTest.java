package chapter3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ExpiryDateCalculatorTest {

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨(){
        assertExpiryDate(PayData.builder()
                .billingData(LocalDate.of(2019,3,1))
                .payAmount(10_000)
                .build() , LocalDate.of(2019,4,1));
    }

    @Test
    @DisplayName("납부일과 한달 뒤 일자가 같지 않음")
    void 납부일과한달뒤일자가같지않음()throws Exception{
        assertExpiryDate(
                PayData.builder()
                        .billingData(LocalDate.of(2019,1,31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019,2,28)
        );
    }

    @Test
    @DisplayName("첫 납부일과 만료일 일지가 다를때 만원 납부")
    void 첫납부일과만효일일지가다를때만원납부(){
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingData(LocalDate.of(2019, 2, 28))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2019,3,31));

        PayData payDate2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 30))
                .billingData(LocalDate.of(2019, 2, 28))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payDate2, LocalDate.of(2019, 3, 30));

    }
    @Test
    @DisplayName("이만원 이상 납부하면 비례해서 만료일 계산")
    void 이만원이상납부하면비례해서만료일계산()throws Exception{
        //given
        assertExpiryDate(PayData.builder()
                .billingData(LocalDate.of(2019,3,1))
                .payAmount(20_000)
                .build(),
                LocalDate.of(2019,5,1)
                );
        //when
        //then

        assertExpiryDate(PayData.builder()
                .billingData(LocalDate.of(2019,3,1))
                .payAmount(30_000)
                .build()
                ,
                LocalDate.of(2019,6,1)
        );
    }

    @Test
    @DisplayName("첫 납부일과 만료일 일지가 다를 때 이만원 이상 납부")
    void 첫납부일과만료일일자가다를때이만원이상납부()throws Exception{
        //given
         assertExpiryDate(
                 PayData.builder()
                         .firstBillingDate(LocalDate.of(2019,1,31))
                         .billingData(LocalDate.of(2019,2,28))
                         .payAmount(20_000)
                         .build()
                 ,
                 LocalDate.of(2019,4,30)
         );

        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019,3,31))
                        .billingData(LocalDate.of(2019,4,30))
                        .payAmount(30_000)
                        .build()
                ,
                LocalDate.of(2019,7,31)
        );
    }


    @Test
    @DisplayName("십만원을 납부하면 1년 제공")
    void 십만원납부하면1년제공()throws Exception{
        //given
        assertExpiryDate(
                PayData.builder()
                .billingData(LocalDate.of(2019,1,28))
                .payAmount(100_000)
                .build(),
                LocalDate.of(2020,1,28)
                );
    }

    @Test
    @DisplayName("13만원을 납부하면 1년 3개월 뒤가 만료일이 되어야 한다.")
    void 십삼만월을납부하면1년3개월뒤가만료일이되어야한다()throws Exception{
        assertExpiryDate(
                PayData.builder()
                        .billingData(LocalDate.of(2019,1,28))
                        .payAmount(130_000)
                        .build()
                ,
                LocalDate.of(2020, 4, 28)
        );
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate){
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }


}