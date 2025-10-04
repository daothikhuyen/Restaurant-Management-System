package RMS.com.example.RMS.customer.mapper;

import RMS.com.example.RMS.customer.domain.CustomerEntity;
import RMS.com.example.RMS.customer.web.CustomerRequest;
import RMS.com.example.RMS.customer.web.CustomerResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity toCustomer(CustomerRequest request);


    CustomerResponse toCustomerResponse(CustomerEntity customer);
}
