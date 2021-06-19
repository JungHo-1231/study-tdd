package secondReading.chapter02;

public class PasswordStrengthMeter {


    public PasswordStrength meter(String password) {

        if (password == null || password == "") return PasswordStrength.INVALID;

        int metCount = getMetCriteriaCounts(password);

        if (metCount <= 1) return PasswordStrength.WEAK;
        if (metCount == 2) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private int getMetCriteriaCounts(String password) {
        int metCount  = 0;

        boolean lengthEnough = password.length() >= 8;
        if (lengthEnough) metCount++;


        boolean containsUpp = meetContainingUppercaseCriteria(password);
        if (containsUpp) metCount++;


        boolean containsNums = meetContainingNumberCriteria(password);
        if (containsNums) metCount++;

        return metCount;

    }

    private boolean meetContainingUppercaseCriteria(String password) {
        boolean containsUpp = false;
        char[] chars = password.toCharArray();
        for (char aChar : chars) {
            if (Character.isUpperCase(aChar)) {
                containsUpp = true;
                break;
            }
        }
        return containsUpp;
    }

    private boolean meetContainingNumberCriteria(String password) {
        boolean containsNums = false;
        char[] chars = password.toCharArray();
        for (char aChar : chars) {
            if (aChar >= '0' && aChar <= '9') {
                containsNums = true;
                break;
            }
        }
        return containsNums;
    }

}
