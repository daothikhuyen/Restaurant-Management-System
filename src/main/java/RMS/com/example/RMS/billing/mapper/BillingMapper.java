package RMS.com.example.RMS.billing.mapper;

import RMS.com.example.RMS.billing.domain.model.BillingEntity;
import RMS.com.example.RMS.billing.web.BillingRequest;
import RMS.com.example.RMS.billing.web.BillingResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillingMapper {
    BillingEntity toBilling(BillingRequest request);
    BillingResponse toBillingResponse(BillingEntity billing);
}
