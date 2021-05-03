package chapter7.autodebit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static chapter7.autodebit.CardValidity.THEFT;
import static chapter7.autodebit.CardValidity.VALID;
import static org.junit.jupiter.api.Assertions.*;

public class AutoDebitRegisterTest {
    private AutoDebitRegister register;

    @BeforeEach
    void setUp() {
        CardNumberValidator validator = new CardNumberValidator();
        JpaAutoDebitInfoRepository repository = new JpaAutoDebitInfoRepository();
        register = new AutoDebitRegister(validator, repository);
    }

    @Test
    void validCard(){
        // 업체에서 받은 테스트용 유요한 카드번호 사용
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.register(req);
        assertEquals(VALID, result.getValidity());
    }

    @Test
    void theftCard(){
        // 업체에서 받은 도난 테스트용 카드 번호
        AutoDebitReq req = new AutoDebitReq("user1", "12345567890123456");
        RegisterResult result = this.register.register(req);
        assertEquals(THEFT, result.getValidity());
    }
}
