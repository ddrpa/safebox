package cc.ddrpa.repack.safebox.type;

import cc.ddrpa.repack.safebox.SecureAccess;

/**
 * 存储长整型
 */
public final class SecureLong {
    private long value;

    public SecureLong(long value) {
        this.value = value;
    }

    public Long get() throws IllegalAccessException {
        throw new IllegalAccessException("Access to secret long directly is not allowed");
    }

    public Long get(SecureAccess access) {
        if (access == null) {
            throw new NullPointerException("SecretAccess required");
        } else {
            return value;
        }
    }

    public boolean equals(long candidate) {
        return value == candidate;
    }

    public boolean equals(SecureLong candidate) {
        if (candidate == null) {
            return false;
        }
        return value == candidate.value;
    }

    public boolean equals(Long candidate) {
        if (candidate == null) {
            return false;
        }
        return value == candidate;
    }
}