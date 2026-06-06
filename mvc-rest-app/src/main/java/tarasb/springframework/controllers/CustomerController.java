package tarasb.springframework.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
//import tarasb.springframework.api.v1.oldDTO.CustomerDTO;
//import tarasb.springframework.api.v1.oldDTO.CustomerListDTO;
//import tarasb.springframework.api.v1.oldDTO.CustomerDTO;
//import tarasb.springframework.api.v1.oldDTO.CustomerListDTO;
import tarasb.springframework.api.v1.model.CustomerDTO;
import tarasb.springframework.api.v1.model.CustomerListDTO;
import tarasb.springframework.services.CustomerService;

@Tag(name = "Customers", description = "Customer API")
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL="/api/v1/customers";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    //@RestController is a @Controller with @ResponseBody. Without the response body the controller
    //return a view name (ex a thymeleaf template name)
    //Legacy code used with @Controller
//    @GetMapping({"", "/"})
//    public ResponseEntity<CustomerListDTO> getAllCustomers() {
//        return new ResponseEntity<>(
//                new CustomerListDTO(customerService.getAllCustomers()),
//                HttpStatus.OK);
//    }

    @Operation(summary = "Get a list of all customers")
    @GetMapping({"", "/"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getAllCustomers() {
        CustomerListDTO customerListDTO = new CustomerListDTO();
        customerListDTO.getCustomers().addAll(customerService.getAllCustomers());
        return customerListDTO;
    }

    @Operation(summary = "Get customer by ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @Operation(summary = "Create a new customer")
    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createNewCustomer(customerDTO);
    }

    @Operation(summary = "Update an existing customer")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDTO customerDTO
    ) {
        return customerService.saveCustomerByDTO(id, customerDTO);
    }

    @Operation(summary = "Partially update an existing customer")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDTO customerDTO
    ) {
        return customerService.patchCustomer(id, customerDTO);
    }

    @Operation(summary = "Delete customer by ID")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomerById(id);
    }
}
