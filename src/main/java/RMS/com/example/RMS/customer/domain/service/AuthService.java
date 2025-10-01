package RMS.com.example.RMS.customer.domain.service;

import RMS.com.example.RMS.common.expection.AppException;
import RMS.com.example.RMS.common.expection.ErrorCode;
import RMS.com.example.RMS.customer.domain.model.CustomerEntity;
import RMS.com.example.RMS.customer.domain.repository.CustomerRepository;
import RMS.com.example.RMS.customer.domain.mapper.AuthMapper;
import RMS.com.example.RMS.customer.domain.model.AuthEntity;
import RMS.com.example.RMS.customer.domain.repository.AuthRepository;
import RMS.com.example.RMS.customer.web.request.AuthRequest;
import RMS.com.example.RMS.customer.web.response.AuthResponse;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthService {

    AuthMapper authMapper;
    AuthRepository userRepository;
    CustomerRepository customerRepository;
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY ;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @Transactional
    public String createUser(AuthRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        try {
            AuthEntity user = authMapper.toAuth(request);
            prepareCustomerDefaults(user);
            user = userRepository.save(user);

            CustomerEntity customer = CustomerEntity.builder()
                    .user(user)
                    .createdBy(user.getUsername())
                    .updatedBy(user.getUsername())
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();

            customerRepository.save(customer);

            return "Account created successfully";
        } catch (Exception e) {
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }
    }

    public AuthResponse login(AuthRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        var token = generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private void prepareCustomerDefaults(AuthEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivated(0);
        user.setActivationKey(UUID.randomUUID().toString());
        user.setResetKey(null);
        user.setTargetKey(null);
        user.setCreateAt(LocalDateTime.now());
        user.setUpdateAt(LocalDateTime.now());
    }

    private String generateToken(AuthEntity user){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("example.backend")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope","ROLE_USER")
                .build();

        JWSObject jwsObject = new JWSObject(header, new Payload(jwtClaimsSet.toJSONObject()));

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }
}
