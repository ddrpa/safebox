package cc.ddrpa.repack.safebox.type;

import cc.ddrpa.repack.safebox.SecureAccess;

/**
 * 存储字符串
 */
public final class SecureString {
    private final String value;

    public SecureString(String value) {
        if (value == null) {
            throw new NullPointerException("String must not be null");
        }
        this.value = value;
    }

    public int length() {
        return this.value.length();
    }

    public String get() throws IllegalAccessException {
        throw new IllegalAccessException("Access to secret value directly is not allowed");
    }

    public String get(SecureAccess access) {
        if (access == null) {
            throw new NullPointerException("SecretAccess required");
        } else {
            return value;
        }
    }

    public boolean equals(String candidate) {
        if (candidate == null) {
            return false;
        }
        return value.equals(candidate);
    }

    public boolean equals(SecureString candidate) {
        if (candidate == null) {
            return false;
        }
        return value.equals(candidate.value);
    }

    public boolean equalsIgnoreCase(String candidate) {
        if (candidate == null) {
            return false;
        }
        return value.equalsIgnoreCase(candidate);
    }

    public boolean equalsIgnoreCase(SecureString candidate) {
        if (candidate == null) {
            return false;
        }
        return value.equalsIgnoreCase(candidate.value);
    }
}