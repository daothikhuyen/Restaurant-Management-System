package RMS.com.example.RMS.table_management.web.controller;


import RMS.com.example.RMS.common.ApiResponse;
import RMS.com.example.RMS.table_management.domain.TableService;
import RMS.com.example.RMS.table_management.web.request.TableRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/table_management")
@CrossOrigin
@Slf4j
public class TableController {

    @Autowired
    TableService service;

    @PostMapping("/create_table")
    public ApiResponse<String> createTable(@RequestBody TableRequest request){

        return ApiResponse.<String>builder()
                .message(service.createTable(request))
                .build();
    }
}
