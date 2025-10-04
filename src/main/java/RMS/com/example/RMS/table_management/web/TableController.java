package RMS.com.example.RMS.table_management.web;


import RMS.com.example.RMS.common.ApiResponse;
import RMS.com.example.RMS.table_management.domain.TableService;
import jakarta.persistence.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/table_management")
@CrossOrigin
@Slf4j
public class TableController {

    @Autowired
    TableService service;

    @GetMapping("/getAll")
    public ApiResponse<List<TableResponse>> getAll(){

        return ApiResponse.<List<TableResponse>>builder()
                .result(service.getAll())
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<String> createTable(@RequestBody TableRequest request){

        return ApiResponse.<String>builder()
                .message(service.createTable(request))
                .build();
    }

    @PostMapping("/update/{tableId}")
    public ApiResponse<TableResponse> updateTable(@PathVariable Long tableId, @RequestBody TableRequest request){

        return ApiResponse.<TableResponse>builder()
                .result(service.updateTable(tableId,request))
                .build();
    }

    @PostMapping("/delete/{tableId}")
    public ApiResponse<String> deleteTable(@PathVariable Long tableId){

        return ApiResponse.<String>builder()
                .message(service.deleteTable(tableId))
                .build();
    }
}
