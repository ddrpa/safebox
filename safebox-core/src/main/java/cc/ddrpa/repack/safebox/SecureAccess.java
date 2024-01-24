package cc.ddrpa.repack.safebox;

public final class SecureAccess {
  private SecureAccess() {}

  public static SecureAccess gain() {
    return new SecureAccess();
  }
}
