package RMS.com.example.RMS.table_management.domain.mapper;

import RMS.com.example.RMS.table_management.domain.model.PaymentEntity;
import RMS.com.example.RMS.table_management.web.request.PaymentRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentEntity toPayment(PaymentRequest request);
}
