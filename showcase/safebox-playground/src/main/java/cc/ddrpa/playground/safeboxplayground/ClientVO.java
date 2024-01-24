package cc.ddrpa.playground.safeboxplayground;

import lombok.Data;

@Data
public class ClientVO {
    private Long id;
    private byte[] purchasePassword;
    private String name;
}