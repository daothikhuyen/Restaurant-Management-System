package RMS.com.example.RMS.table_management.domain;

import RMS.com.example.RMS.common.expection.AppException;
import RMS.com.example.RMS.common.expection.ErrorCode;
import RMS.com.example.RMS.table_management.mapper.TableMapper;
import RMS.com.example.RMS.table_management.domain.model.TableEntity;
import RMS.com.example.RMS.table_management.domain.model.TableStatus;
import RMS.com.example.RMS.table_management.web.TableRequest;
import RMS.com.example.RMS.table_management.web.TableResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TableService {

    TableMapper tableMapper;
    TableRepository tableRepository;

    public List<TableResponse> getAll() {
        try {
            List<TableEntity> tables = tableRepository.findAll();
            return tables.stream().map(tableMapper::toTableResponse).toList();
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

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

    public TableResponse updateTable(Long tableId, TableRequest request) {
        TableEntity table = tableRepository.findById(tableId)
                .orElseThrow(() -> new AppException(ErrorCode.DATA_NOT_FOUND));

        try {
            table.setName(request.getName());
            table.setArea(request.getArea());
            table.setCapacity(request.getCapacity());
            table.setUpdatedBy(request.getUpdatedBy());
            table.setUpdatedDate(LocalDateTime.now());

            return tableMapper.toTableResponse(table);
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public String deleteTable(Long tableId) {
        TableEntity table = tableRepository.findById(tableId)
                .orElseThrow(() -> new AppException(ErrorCode.DATA_NOT_FOUND));

        try {
            table.setDeleted(true);
            tableRepository.save(table);

            return "Data deletion successful";
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }
}
