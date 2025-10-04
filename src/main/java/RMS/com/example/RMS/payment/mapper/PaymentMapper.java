package RMS.com.example.RMS.payment.mapper;

import RMS.com.example.RMS.payment.domain.PaymentEntity;
import RMS.com.example.RMS.payment.web.PaymentRequest;
import RMS.com.example.RMS.payment.web.PaymentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentEntity toPayment(PaymentRequest request);

    PaymentResponse toPaymentResponse(PaymentEntity payment);
}
