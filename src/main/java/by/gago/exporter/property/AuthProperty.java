package by.gago.exporter.property;

import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component()
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AuthProperty {
    private String authToken;
}
