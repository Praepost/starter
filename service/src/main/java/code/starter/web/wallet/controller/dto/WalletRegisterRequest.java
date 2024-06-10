package code.starter.web.wallet.controller.dto;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Accessors(chain = true)
public class WalletRegisterRequest {
    private String valletId;
    private String operationType;
    private Integer amount;
}
