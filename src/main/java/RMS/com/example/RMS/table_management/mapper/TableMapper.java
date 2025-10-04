package RMS.com.example.RMS.table_management.mapper;

import RMS.com.example.RMS.table_management.domain.model.TableEntity;
import RMS.com.example.RMS.table_management.web.TableRequest;
import RMS.com.example.RMS.table_management.web.TableResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TableMapper {
    TableEntity toTable(TableRequest request);

    TableResponse toTableResponse(TableEntity tableEntity);
}
