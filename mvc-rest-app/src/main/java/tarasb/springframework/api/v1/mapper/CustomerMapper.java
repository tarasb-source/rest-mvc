package tarasb.springframework.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tarasb.springframework.api.v1.model.CustomerDTO;
import tarasb.springframework.domain.Customer;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(
            target = "customerUrl",
            expression = "java(tarasb.springframework.controllers.CustomerController.BASE_URL + \"/\" + customer.getId())"
    )
    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
