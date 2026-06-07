package tarasb.springframework.controllers;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tarasb.springframework.api.v1.model.CustomerDTO;
import tarasb.springframework.repositories.CustomerRepository;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerIT extends AbstractRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CustomerRepository customerRepository;

    private String customerUrl;

    @BeforeEach
    void setUp() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Fred");
        customer.setLastName("Flintstone");

        String response = mockMvc.perform(post(CustomerController.BASE_URL + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        customerUrl = JsonPath.read(response, "$.customer_url");
    }

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }


    @Test
    void listCustomers() throws Exception {
        mockMvc.perform(get(CustomerController.BASE_URL + "/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers").isArray())
                .andExpect(jsonPath("$.customers").isNotEmpty());
    }

    @Test
    void getCustomerById() throws Exception {
        mockMvc.perform(get(customerUrl)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first_name", equalTo("Fred")))
                .andExpect(jsonPath("$.last_name", equalTo("Flintstone")))
                .andExpect(jsonPath("$.customer_url", equalTo(customerUrl)));
    }

    @Test
    void createNewCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Fred");
        customer.setLastName("Flintstone");

        mockMvc.perform(post(CustomerController.BASE_URL + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.first_name", equalTo("Fred")))
                .andExpect(jsonPath("$.last_name", equalTo("Flintstone")))
                .andExpect(jsonPath("$.customer_url").exists());
    }

    @Test
    void patchCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Barney");

        mockMvc.perform(patch(customerUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first_name", equalTo("Barney")))
                .andExpect(jsonPath("$.last_name", equalTo("Flintstone")))
                .andExpect(jsonPath("$.customer_url", equalTo(customerUrl)));
    }

    @Test
    void notFoundException() throws Exception {
        mockMvc.perform(get(CustomerController.BASE_URL + "/999999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
