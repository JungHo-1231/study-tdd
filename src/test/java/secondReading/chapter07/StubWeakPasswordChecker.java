package secondReading.chapter07;

public class StubWeakPasswordChecker implements WeakPasswordChecker {
    private boolean weak;

    public void setWeak(boolean b) {
        weak = b;
    }

    @Override
    public boolean checkPasswordWeak(String pw) {
        return weak;
    }
}
