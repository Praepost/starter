package code.starter.web.wallet.controller;

import code.starter.web.wallet.controller.dto.WalletRegisterRequest;
import lombok.AllArgsConstructor;

import code.starter.web.wallet.controller.dto.SuccessResponse;
import code.starter.web.wallet.entity.Wallet;
import code.starter.web.wallet.entity.repository.WalletRepository;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class WalletController implements IWalletController{
    private final WalletRepository repository;

    @Override
    public SuccessResponse wallet(WalletRegisterRequest request) {
        Wallet wallet;
        switch (request.getOperationType()){
            case ("DEPOSIT"):
                wallet = repository.findWalletByUUID(request.getValletId()).orElse(wallet = new Wallet("UID", 0));
                wallet.setAmount(wallet.getAmount() + request.getAmount());
                wallet.setUUID(request.getValletId());
                repository.save(wallet);
                return new SuccessResponse("Операция провдена успешно");

            case ("WITHDRAW"):
                wallet = repository.findWalletByUUID(request.getValletId()).get();
                if (wallet.getAmount() - request.getAmount()<0){
                    return new SuccessResponse("Не корректный размер операции");
                }
                wallet.setAmount(wallet.getAmount() - request.getAmount());
                return new SuccessResponse("Операция провдена успешно");

            default:
                return new SuccessResponse("Не корректный operationType");
        }
    }

    @Override
    public SuccessResponse wallets(String walletUUID) {
        Wallet wallet = repository.findWalletByUUID(walletUUID).get();

        if (wallet.equals(null)){
            return new SuccessResponse("Некорретный UUID");
        }

        return new SuccessResponse(wallet.getAmount().toString());
    }

}