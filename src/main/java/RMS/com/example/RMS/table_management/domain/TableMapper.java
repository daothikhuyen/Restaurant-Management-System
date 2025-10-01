package RMS.com.example.RMS.table_management.domain;

import RMS.com.example.RMS.table_management.web.request.TableRequest;
import RMS.com.example.RMS.table_management.web.response.TableResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TableMapper {
    TableEntity toTable(TableRequest request);
    TableResponse totableResponse(TableEntity table);
}
