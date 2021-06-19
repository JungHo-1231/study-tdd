package secondReading.chapter07;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegisterTest {
    private UserRegister userRegister;
    private StubWeakPasswordChecker stubWeakPasswordChecker
            = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();


    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker
                , fakeRepository
                , spyEmailNotifier
        );
    }


    @Test
    @DisplayName("약한 암호면 가입 실패")
    void weakPassword() throws Exception{
        stubWeakPasswordChecker.setWeak(true);

        assertThrows(WeakPassWordException.class, ()-> {
            userRegister.register("id", "pw","email");
        });
    }

    @Test
    @DisplayName("이미 같은 아이디가 존재하면 가입 실패")
    void dupIdExists() throws Exception{
        //given
        fakeRepository.save(new User("id", "pw1", "email@email.com")) ;

        //when

        //then
        assertThrows(DupIdException.class, ()-> {
            userRegister.register("id", "pw2", "email");
        });
    }

    @Test
    @DisplayName("가입하면 메일을 전송함")
    void whenRegisterThenSendMail() throws Exception{
        //given
        String email = "email@email.com";

        userRegister.register("id", "pw", email);

        //when

        //then
        assertTrue(spyEmailNotifier.isCalled());
        assertEquals(email, spyEmailNotifier.getEmail());
    }


}
