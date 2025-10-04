package RMS.com.example.RMS.payment.domain;

import RMS.com.example.RMS.common.expection.AppException;
import RMS.com.example.RMS.common.expection.ErrorCode;
import RMS.com.example.RMS.customer.domain.CustomerEntity;
import RMS.com.example.RMS.payment.mapper.PaymentMapper;
import RMS.com.example.RMS.payment.web.PaymentRequest;
import RMS.com.example.RMS.payment.web.PaymentResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PaymentService {

    PaymentMapper paymentMapper;
    PaymentsRepository paymentRepository;

    public String createPayment(PaymentRequest request) {
        if(paymentRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.DATA_EXISTED);
        }

        try {
            PaymentEntity payment = paymentMapper.toPayment(request);
            payment.setStatus(PaymentStatus.ACTIVE);
            payment.setCreatedDate(LocalDateTime.now());
            payment.setUpdatedDate(LocalDateTime.now());
            paymentRepository.save(payment);

            return "Data saved successfully";
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public PaymentResponse updatePayment(Long paymentId, PaymentRequest request) {
        PaymentEntity payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        try {
            payment.setName(request.getName());
            payment.setUpdatedBy(request.getUpdatedBy());
            payment.setUpdatedDate(LocalDateTime.now());
            paymentRepository.save(payment);

            return paymentMapper.toPaymentResponse(payment);
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public String deletePayment(Long paymentId) {
        PaymentEntity payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        try {
            payment.setStatus(PaymentStatus.INACTIVE);
            paymentRepository.save(payment);

            return "Data deletion successful";
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }
}
