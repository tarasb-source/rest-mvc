package tarasb.springframework.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tarasb.springframework.domain.Category;
import tarasb.springframework.domain.Customer;
import tarasb.springframework.repositories.CategoryRepository;
import tarasb.springframework.repositories.CustomerRepository;

@Slf4j
@Component
@Profile("dev")
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (categoryRepository.count() == 0L){
            log.debug("Loading Categories");
            loadCategories();
        }

        if (customerRepository.count() == 0L) {
            log.debug("Loading Customers");
            loadCustomers();
        }

    }

    public void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data Loaded = " + categoryRepository.count());
    }

    public void loadCustomers() {
        Customer fred = new Customer();
        fred.setFirstName("Fred");
        fred.setLastName("Buckley");

        Customer kate = new Customer();
        kate.setFirstName("Kate");
        kate.setLastName("Smith");

        Customer chuck = new Customer();
        chuck.setFirstName("Chuck");
        chuck.setLastName("Bard");

        Customer renate = new Customer();
        renate.setFirstName("Renate");
        renate.setLastName("Reinsve");

        customerRepository.save(fred);
        customerRepository.save(kate);
        customerRepository.save(chuck);
        customerRepository.save(renate);
    }
}
