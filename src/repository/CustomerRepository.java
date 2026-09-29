package repository;

import domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerRepository {
    private final Map<String, Customer> customerById = new HashMap<>();

    public ArrayList<Customer> findAll() {
        return new ArrayList<>(customerById.values());
    }

    public void save(Customer c) {
        customerById.put(c.getId(), c);
    }
}
