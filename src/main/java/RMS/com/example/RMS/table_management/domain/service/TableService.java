package RMS.com.example.RMS.table_management.domain.service;

import RMS.com.example.RMS.common.expection.AppException;
import RMS.com.example.RMS.common.expection.ErrorCode;
import RMS.com.example.RMS.table_management.domain.mapper.TableMapper;
import RMS.com.example.RMS.table_management.domain.model.TableEntity;
import RMS.com.example.RMS.table_management.domain.model.TableStatus;
import RMS.com.example.RMS.table_management.domain.repository.TableRepository;
import RMS.com.example.RMS.table_management.web.request.TableRequest;
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
public class TableService {

    TableMapper tableMapper;
    TableRepository tableRepository;

    public String createTable(TableRequest request){

        if (tableRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.DATA_EXISTED);
        }

        try {
            TableEntity table = tableMapper.toTable(request);
            table.setStatus(TableStatus.AVAILABLE);
            table.setCreatedDate(LocalDateTime.now());
            table.setUpdatedDate(LocalDateTime.now());
            table.setUpdatedBy(request.getUpdatedBy());

            tableRepository.save(table);

            return "Data saved successfully";
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }
}
