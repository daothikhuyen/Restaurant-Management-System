package RMS.com.example.RMS.customer.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {
    @NotBlank(message = "First name must not be blank")
    @Size(max = 50, message = "First name can have at most 50 characters")
    private String firstName;

    @Size(max = 50, message = "Middle name can have at most 50 characters")
    private String middleName;

    @NotBlank(message = "Last name must not be blank")
    @Size(max = 50, message = "Last name can have at most 50 characters")
    private String lastName;

    @NotBlank(message = "Phone number must not be blank")
    @Pattern(regexp = "^(0|\\+84)(\\d{9})$", message = "Invalid phone number format")
    private String phone;

    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email can have at most 100 characters")
    private String email;

    @Size(max = 255, message = "Address can have at most 255 characters")
    private String address;

    @Pattern(regexp = "^[MF]$", message = "Gender must be either M or F")
    private String gender; // use String for easier validation

    @NotBlank(message = "CreatedBy must not be blank")
    private String createdBy;

    private String updatedBy;
}

