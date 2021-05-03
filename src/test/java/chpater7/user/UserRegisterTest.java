package chpater7.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class UserRegisterTest {
    private UserRegister userRegister;
    private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    private WeakPasswordChecker mockPasswordChecker = mock(WeakPasswordChecker.class);
    private EmailNotifier mockEmailNotifier = mock(EmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker, fakeRepository, spyEmailNotifier);
    }


    @Test
    @DisplayName("mock 객체로 테스트하는 약한 암호면 가입 실패")
    void weakPasswordMock()throws Exception{
        //given
        given(mockPasswordChecker.checkPasswordWeak("pw")).willReturn(true);
        //when
        assertThrows(
                WeakPasswordException.class, () -> {
                    userRegister.register("id", "pw", "email");
                });
    }

    @Test
    @DisplayName("약한 암호면 가입 실패")
    void weakPassword()throws Exception{
        //given
        stubWeakPasswordChecker.setWeak(true);
        //when
        assertThrows(
                WeakPasswordException.class, () -> {
                    userRegister.register("id", "pw", "email");
                });
    }

    @Test
    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    void dupIdExists()throws Exception{
        // 이미 같은 ID 존재하는 상황 만들기
        fakeRepository.save(new User("id", "pw1", "email@email.com"));

        assertThrows(DupIdException.class, ()-> {
            userRegister.register("id","pw2", "email");
        });
    }

    @Test
    @DisplayName("같은 ID가 없으면 가입 성공함")
    void 같은아이디가없으면가입성공함()throws Exception{
        //given
        userRegister.register("id", "pw", "email");
        //when
        User savedUser = fakeRepository.findById("id"); // 가입 결과 확인

        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());

        //then
    }

    @Test
    @DisplayName("가입하면 메일을 전송함")
    void 가입하면메일을전송함()throws Exception{
        userRegister.register("id", "pw", "email@email.com");
        assertTrue(spyEmailNotifier.isCalled());

        assertEquals(
                "email@email.com",
                spyEmailNotifier.getEmail()
        );
    }

    @Test
    @DisplayName("회원 가입시 암호 검사 수행함")
    void checkPassword()throws Exception{
        userRegister.register("id","password", "email");
        then(mockPasswordChecker)
                .should()
                .checkPasswordWeak(anyString());
    }

//    @Test
//    @DisplayName("가입하면 메일을 전송함")
//    void whenRegisterThenSendMail()throws Exception {
//        //given
//        userRegister.register("id","pw", "email@email.com");
//        //when
//        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
//        then(mockEmailNotifier).should().sendRegister(captor.capture());
//
//        String realEmail = captor.getValue();
//        System.out.println("realEmail = " + realEmail);
//        //then
//        assertEquals("email@email.com", realEmail);
//    }
}
