package secondReading.chapter07;

import lombok.Getter;

@Getter
public class SpyEmailNotifier implements EmailNotifier {

    private boolean called;
    private String email;

    @Override
    public void sendRegisterEmail(String email) {
        this.email = email;

        called = true;
    }


}
