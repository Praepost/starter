package code.starter.web.wallet.controller;

import code.starter.web.wallet.controller.dto.WalletRegisterRequest;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import code.starter.web.exception.dto.MessagesResponse;
import code.starter.web.exception.dto.SuccessfulResponse;
import code.starter.web.wallet.controller.dto.SuccessResponse;

@Validated
public interface IWalletController {
        @Transactional
        @PostMapping("/api/v1/wallet/")
        @ApiResponses({
                @ApiResponse(code = 200, response = SuccessfulResponse.class, message =
                        "Успешный поплнение кошелька: "),
                @ApiResponse(code = 400,  response = MessagesResponse.class, message =
                        "Ошибка при регистрации "),
        })
        SuccessResponse wallet(@Valid @RequestBody WalletRegisterRequest request);

        @Transactional
        @GetMapping("/api/v1/wallets/{WALLET_UUID}")
        @ApiResponses({
                @ApiResponse(code = 400,  response = MessagesResponse.class, message =
                        "Ошибка при регистрации "),
        })
        SuccessResponse wallets(@PathVariable(name = "WALLET_UUID") String walletUUID);
}
