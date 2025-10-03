package by.gago.exporter.property;

import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component()
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AuthProperty {
    private String authToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJlNmM0NDk5Ny1iMzkxLTRmZTgtYjNkZi01NzgxZGQ3NWYxNjgiLCJzdWIiOiJBVVRIIiwiaWF0IjoxNzU5NDkxMDM2LCJleHAiOjE3NTk0OTgyMzZ9.L7WPYJCcKHChu4O-R4sDGtOWt1X1RVyshuCyg2Y9lxs";
}
