package RMS.com.example.RMS.customer.web.controller;

import RMS.com.example.RMS.common.ApiResponse;
import RMS.com.example.RMS.customer.domain.service.AuthService;
import RMS.com.example.RMS.customer.web.request.AuthRequest;
import RMS.com.example.RMS.customer.web.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ApiResponse<String> createUser(@RequestBody AuthRequest request) throws Exception {
        ApiResponse<String> response = null;

        response = ApiResponse.<String>builder()
                .result(service.createUser(request))
                .build();
        return  response;
    }

    @PostMapping("/log-in")
    public ApiResponse<AuthResponse> authenticate(@RequestBody AuthRequest request) throws Exception {

        var result = service.login(request);

        return ApiResponse.<AuthResponse>builder()
                .result(result)
                .build();
    }
}
