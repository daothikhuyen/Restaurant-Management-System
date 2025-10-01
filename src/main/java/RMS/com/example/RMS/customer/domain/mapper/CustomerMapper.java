package RMS.com.example.RMS.customer.domain.mapper;

import RMS.com.example.RMS.customer.domain.model.CustomerEntity;
import RMS.com.example.RMS.customer.web.request.CustomerRequest;
import RMS.com.example.RMS.customer.web.response.CustomerResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity toCustomer(CustomerRequest request);


    CustomerResponse toCustomerResponse(CustomerEntity customer);
}
