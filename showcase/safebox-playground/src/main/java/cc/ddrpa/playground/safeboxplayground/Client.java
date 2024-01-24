package cc.ddrpa.playground.safeboxplayground;

import cc.ddrpa.repack.safebox.type.SecureBytes;
import cc.ddrpa.repack.safebox.type.SecureLong;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "tbl_client", autoResultMap = true)
public class Client implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 这是一个秘密的内部 ID
     * 避免泄露到外部，使第三方能够通过 ID 搜集归类用户信息
     */
//    @TableField(value = "id", typeHandler = SecureLongTypeHandler.class)
    @TableId
    private SecureLong id;
    /**
     * 这是客户的支付密码（的编码结果）
     * 当然不能泄漏
     */
//    @TableField(value = "purchase_password", typeHandler = SecureBytesTypeHandler.class)
    private SecureBytes purchasePassword;
    /**
     * 这是客户的姓名
     * 公开信息
     */
    @TableField("name")
    private String name;

    public Client setId(SecureLong id) {
        this.id = id;
        return this;
    }

    public Client setId(Long id) {
        this.id = new SecureLong(id);
        return this;
    }

    public Client setPurchasePassword(SecureBytes purchasePassword) {
        this.purchasePassword = purchasePassword;
        return this;
    }

    public Client setPurchasePassword(byte[] purchasePassword) {
        this.purchasePassword = new SecureBytes(purchasePassword);
        return this;
    }
}