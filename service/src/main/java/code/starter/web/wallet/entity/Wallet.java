package code.starter.web.wallet.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String UUID;
    @NonNull
    private Integer amount;
}
