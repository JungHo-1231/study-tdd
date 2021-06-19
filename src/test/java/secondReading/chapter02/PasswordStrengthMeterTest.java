package secondReading.chapter02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordStrengthMeterTest {

    PasswordStrengthMeter meter = new PasswordStrengthMeter();

    @Test
    void 암호가_모든_조건을_만족하면_암호_강도는_강함() {
        String password = "ab12!@AB";
        PasswordStrength expected = PasswordStrength.STRONG;
        assertStrength(password,expected);

        String password1 = "abc1!ADD";
        PasswordStrength expected2 = PasswordStrength.STRONG;
        assertStrength(password1, expected2);
    }

    @Test
    void 길이가_8글자_이상은_충족하지_않고_다른_두_조건은_충족하는_경우() {
        String password = "ab12!@A";
        PasswordStrength expected = PasswordStrength.NORMAL;
        assertStrength(password, expected);
    }

    @Test
    void 숫자를_포함하지_않고_나머지_조건은_충족하는_경우() {
        String password = "ab!ABqwer";
        PasswordStrength expected = PasswordStrength.NORMAL;
        assertStrength(password,expected);
    }

    @Test
    void 값이_없는_경우(){
        assertStrength(null, PasswordStrength.INVALID);
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    void 대문자를_포함하지_않고_나머지_조건을_충족하는_경우(){
        String password = "ab12!@df";
        assertStrength(password, PasswordStrength.NORMAL);
    }

    @Test
    void 길이가_8글자_이상인_조건만_충족하는_경우(){
        String password = "abdefghi";
        assertStrength(password, PasswordStrength.WEAK);
    }

    @Test
    void 숫자_조건만_충족하는_경우(){
        String password = "12345";
        assertStrength(password, PasswordStrength.WEAK);
    }

    @Test
    void 대문자_포함_조건만_충족하는_경우(){
        String password = "ABZEF";
        assertStrength(password, PasswordStrength.WEAK);
    }

    private void assertStrength(String password, PasswordStrength expStr){
        PasswordStrength meter = this.meter.meter(password);
        assertEquals(expStr, meter);
    }

}
