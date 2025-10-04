package RMS.com.example.RMS.billing.domain.service;

import RMS.com.example.RMS.billing.domain.model.BillingEntity;
import RMS.com.example.RMS.billing.domain.BillingRepository;
import RMS.com.example.RMS.billing.domain.model.BillingStatus;
import RMS.com.example.RMS.billing.mapper.BillingMapper;
import RMS.com.example.RMS.billing.web.BillingResponse;
import RMS.com.example.RMS.common.expection.AppException;
import RMS.com.example.RMS.common.expection.ErrorCode;
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
public class BillingService implements IBillingService {

    BillingMapper billingMapper;
    BillingRepository billingRepository;

    @Override
    public BillingResponse createBillingFromOrder(Long orderId, Long tableId, Long userId, double totalAmount, String notes) {
        if(billingRepository.existsByOrderId(orderId)){
            throw new AppException(ErrorCode.DATA_EXISTED);
        }

        try {
            BillingEntity billing =  BillingEntity.builder()
                    .orderId(orderId)
                    .tableId(tableId)
                    .customerId(userId)
                    .totalAmount(totalAmount)
                    .notes(notes)
                    .status(BillingStatus.PAID)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();
            billing = billingRepository.saveAndFlush(billing);

            return billingMapper.toBillingResponse(billing);
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    @Override
    public BillingResponse updateBilling(Long orderId, Long tableId, Long userId, double totalAmount, String notes) {
        BillingEntity billing = billingRepository.findByOrderId(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.DATA_NOT_FOUND));

        try {
            billing.setTableId(tableId);
            billing.setTotalAmount(totalAmount);
            billing.setUpdatedDate(LocalDateTime.now());
            billing.setNotes(notes);
            billingRepository.save(billing);

            return billingMapper.toBillingResponse(billing);
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public BillingResponse updateStatusBilling(Long billingId, String status){
        BillingEntity billing = billingRepository.findById(billingId)
                .orElseThrow(() -> new AppException(ErrorCode.DATA_NOT_FOUND));

        try{
            BillingStatus billingStatus = BillingStatus.valueOf(status.toUpperCase());
            billing.setStatus(billingStatus);
            billing.setUpdatedDate(LocalDateTime.now());
            billingRepository.save(billing);

            return billingMapper.toBillingResponse(billing);
        }catch (IllegalArgumentException e) {
            throw new AppException(ErrorCode.INVALID_INPUT);
        }
        catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public String deleteBilling(Long billingId) {
        BillingEntity billing = billingRepository.findById(billingId)
                .orElseThrow(() -> new AppException(ErrorCode.DATA_NOT_FOUND));

        try {
            billing.setStatus(BillingStatus.CANCELLED);
            billingRepository.save(billing);

            return "Data deletion successful";
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }
}
