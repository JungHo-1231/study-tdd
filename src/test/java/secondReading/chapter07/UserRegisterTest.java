package secondReading.chapter07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

public class UserRegisterTest {
    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker= Mockito.mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);


    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker
                , fakeRepository
                , mockEmailNotifier
        );
    }


    @Test
    @DisplayName("약한 암호면 가입 실패")
    void weakPassword() throws Exception{
        given(mockPasswordChecker.checkPasswordWeak("pw")).willReturn(true);

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

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        //when
        then(mockEmailNotifier)
                .should()
                .sendRegisterEmail(captor.capture());

        String realEmail = captor.getValue();

        assertEquals("email@email.com", realEmail);


        //then
//        assertTrue(spyEmailNotifier.isCalled());
//        assertEquals(email, spyEmailNotifier.getEmail());


    }

    @Test
    @DisplayName("회원 가입시 암호 검사를 수행함")
    void checkPassword() throws Exception{
        //given
        userRegister.register("id", "pw", "email");

        //when

        //then
        then(mockPasswordChecker)
                .should()
                .checkPasswordWeak(anyString());
    }


}
