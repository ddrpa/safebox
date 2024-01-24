package cc.ddrpa.repack.safebox.type;

import cc.ddrpa.repack.safebox.SecureAccess;

import java.util.Arrays;

/**
 * 存储字节数组
 */
public final class SecureBytes {
    private final byte[] value;

    public SecureBytes(byte[] value) {
        if (value == null) {
            throw new NullPointerException("Bytes must not be null");
        }
        this.value = value.clone();
    }

    public int size() {
        return this.value.length;
    }

    public byte[] get() throws IllegalAccessException {
        throw new IllegalAccessException("Access to secret bytes directly is not allowed");
    }

    public byte[] get(SecureAccess access) {
        if (access == null) {
            throw new NullPointerException("SecretAccess required");
        } else {
            return value.clone();
        }
    }

    public boolean equals(byte[] candidate) {
        if (candidate == null) {
            return false;
        }
        return Arrays.equals(this.value, candidate);
    }

    public boolean equals(SecureBytes candidate) {
        if (candidate == null) {
            return false;
        }
        return Arrays.equals(this.value, candidate.value);
    }
}
