package code.starter.web.wallet.entity.repository;

import code.starter.web.wallet.entity.Wallet;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional <Wallet> findWalletByUUID(String UUID);
}
