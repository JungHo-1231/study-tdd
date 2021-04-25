package chapter2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordStrengthMeterTest {
    PasswordStrengthMeter meter=new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr){
        PasswordStrength result = this.meter.meter(password);
        assertEquals(expStr, result);
    }

    @Test
    void meetsAllCriteria_then_String ()throws Exception{
        String password = "ab12!@AB";
        assertStrength(password, PasswordStrength.STRONG);

        String password2 = "abc1!Add";
        assertStrength(password2, PasswordStrength.STRONG);
    }

    @Test
    void meetsOtherCriteria_except_for_Length_then_Normal() throws Exception{
        String password = "ab12!@A";
        assertStrength(password, PasswordStrength.NORMAL);

        String password2 = "Ab12!c";
        assertStrength(password2, PasswordStrength.NORMAL);
    }


    @Test
    void meetsOtherCriteria_except_for_Length_Then_Normal()throws Exception{
        String password = "ab!@ABqwer";
        assertStrength(password, PasswordStrength.NORMAL);
    }

    @Test
    void nullInput_Then_Invalid(){
        assertStrength(null,PasswordStrength.INVALID);
    }

    @Test
    void emptyInput_Then_Invalid(){
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    void meetsOtherCriteria_except_for_Uppercase_then_Normal(){
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @Test
    void meetsOnlyLengthCriteria_Then_Weak(){
        assertStrength("abdefghi", PasswordStrength.WEAK);
    }

    @Test
    void meetsOnlyUpperCriteria_Then_Weak(){
        assertStrength("ABZEF", PasswordStrength.WEAK);
    }

    @Test
    void meetsNoCriteria_then_Weak(){
        assertStrength("abc", PasswordStrength.WEAK);
    }


}
