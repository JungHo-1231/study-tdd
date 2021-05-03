package chapter8.auth;

public interface CustomerRepository {
    Customer findOne(String id);
}
