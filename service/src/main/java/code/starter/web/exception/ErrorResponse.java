package code.starter.web.exception;

import code.starter.web.exception.dto.Response;
import code.starter.web.exception.dto.ResponseStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorResponse implements Response {
    @ApiModelProperty(notes = "Contains information about error", required = true)
    private final Error error;

    public ErrorResponse(String code, String description, String message) {
        this.error = new Error(code, description, message);
    }

    @Override
    @ApiModelProperty(allowableValues = "FAIL", notes = "Status operation", required = true)
    public ResponseStatus getStatus() {
        return ResponseStatus.FAIL;
    }
}