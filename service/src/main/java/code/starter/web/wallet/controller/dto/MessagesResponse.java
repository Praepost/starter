package code.starter.web.wallet.controller.dto;

import lombok.*;
import lombok.experimental.Accessors;

import code.starter.web.exception.dto.SuccessfulResponse;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Accessors(chain = true)
public class MessagesResponse extends SuccessfulResponse {
    @NonNull
    private String message;
}
