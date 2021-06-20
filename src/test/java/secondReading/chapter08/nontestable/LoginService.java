package secondReading.chapter08.nontestable;

import secondReading.chapter08.auth.AuthUtil;
import secondReading.chapter08.auth.Customer;
import secondReading.chapter08.auth.CustomerRepository;
import secondReading.chapter08.auth.LoginResult;

public class LoginService {
    private String authKey = "somekey";
    private CustomerRepository customerRepo;

    public LoginService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public LoginResult login(String id, String pw) {
        int resp = 0;
        boolean authorized = AuthUtil.authorize(authKey);
        if (authorized) {
            resp = AuthUtil.authenticate(id, pw);
        } else {
            resp = -1;
        }
        if (resp == -1) return LoginResult.badAuthKey();

        if (resp == 1) {
            Customer c = customerRepo.findOne(id);
            return LoginResult.authenticated(c);
        } else {
            return LoginResult.fail(resp);
        }
    }

}