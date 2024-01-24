package cc.ddrpa.repack.safebox.typehandler;

import cc.ddrpa.repack.safebox.SecureAccess;
import org.apache.ibatis.type.BaseTypeHandler;

public abstract class AbstractSecureTypeHandler<T> extends BaseTypeHandler<T> {
    private static SecureAccess SECURE_ACCESS;

    public static void setSecureAccess(SecureAccess secureAccess) {
        SECURE_ACCESS = secureAccess;
    }

    protected static SecureAccess getSecureAccess() {
        if (null == SECURE_ACCESS) {
            SECURE_ACCESS = SecureAccess.gain();
        }
        return SECURE_ACCESS;
    }
}