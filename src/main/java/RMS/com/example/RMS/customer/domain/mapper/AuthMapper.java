package RMS.com.example.RMS.customer.domain.mapper;

import RMS.com.example.RMS.customer.domain.model.AuthEntity;
import RMS.com.example.RMS.customer.web.request.AuthRequest;
import RMS.com.example.RMS.customer.web.response.AuthResponse;
import RMS.com.example.RMS.customer.web.response.CustomerResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    AuthEntity toAuth(AuthRequest request);

    AuthResponse toAuthResponse(AuthEntity customer);

}
