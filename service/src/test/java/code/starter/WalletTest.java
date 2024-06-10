package code.starter;

import code.starter.web.wallet.controller.dto.WalletRegisterRequest;
import code.starter.web.wallet.entity.Wallet;
import code.starter.web.wallet.entity.repository.WalletRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Slf4j
public class WalletTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WalletRepository walletRepository;

    @Test
    public void createWalletSuccess() {
        Wallet wallet = new Wallet();

        try {
            WalletRegisterRequest request =
                    new WalletRegisterRequest("walletID", "DEPOSIT",1000 );

            mockMvc.perform(post("/api/v1/wallet/")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk());

            wallet = walletRepository.findWalletByUUID("walletID").get();

            assertEquals(wallet.getAmount(), request.getAmount());
            assertEquals(wallet.getUUID(), request.getValletId());


        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            log.info("finally");
            walletRepository .deleteAll();

        }
    }

    @Test
    public void createWalletSuccess2() {
        Wallet wallet = new Wallet("walletID", 1000);

        walletRepository.save(wallet);
        try {
            WalletRegisterRequest request =
                    new WalletRegisterRequest("walletID", "DEPOSIT",1000 );

            mockMvc.perform(post("/api/v1/wallet/")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk());

            wallet = walletRepository.findWalletByUUID("walletID").get();

            assertEquals((Object)wallet.getAmount(), (Object)2000);
            assertEquals(wallet.getUUID(), request.getValletId());


        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            log.info("finally");
            walletRepository .deleteAll();

        }
    }


    @Test
    public void createWalletSuccess3() {
        Wallet wallet = new Wallet();

        try {
            WalletRegisterRequest request =
                    new WalletRegisterRequest("walletID", "DEPOSIT",1000 );

            mockMvc.perform(post("/api/v1/wallet/")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk());

            String pathParams = "?WALLET_UUID=walletID";

            Assertions.assertEquals(mockMvc.perform(get("/api/v1/wallets/" + pathParams)
            ).andReturn().getResponse().getContentAsString().contains("1000"), false);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            log.info("finally");
            walletRepository .deleteAll();

        }
    }
}
