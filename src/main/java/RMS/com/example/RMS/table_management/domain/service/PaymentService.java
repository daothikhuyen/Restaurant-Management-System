package RMS.com.example.RMS.table_management.domain.service;

import RMS.com.example.RMS.common.expection.AppException;
import RMS.com.example.RMS.common.expection.ErrorCode;
import RMS.com.example.RMS.table_management.domain.mapper.PaymentMapper;
import RMS.com.example.RMS.table_management.domain.model.PaymentEntity;
import RMS.com.example.RMS.table_management.domain.model.PaymentStatus;
import RMS.com.example.RMS.table_management.domain.repository.PaymentsRepository;
import RMS.com.example.RMS.table_management.web.request.PaymentRequest;
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
}
