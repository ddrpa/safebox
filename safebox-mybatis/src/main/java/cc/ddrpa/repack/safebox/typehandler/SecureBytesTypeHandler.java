package cc.ddrpa.repack.safebox.typehandler;

import cc.ddrpa.repack.safebox.type.SecureBytes;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(SecureBytes.class)
@MappedJdbcTypes({JdbcType.BINARY, JdbcType.VARBINARY, JdbcType.LONGVARBINARY})
public final class SecureBytesTypeHandler extends AbstractSecureTypeHandler<SecureBytes> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, SecureBytes parameter, JdbcType jdbcType) throws SQLException {
        ps.setBytes(i, parameter.get(getSecureAccess()));
    }

    @Override
    public SecureBytes getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (rs.getBytes(columnName) == null) {
            return null;
        }
        return new SecureBytes(rs.getBytes(columnName));
    }

    @Override
    public SecureBytes getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (rs.getBytes(columnIndex) == null) {
            return null;
        }
        return new SecureBytes(rs.getBytes(columnIndex));
    }

    @Override
    public SecureBytes getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (cs.getBytes(columnIndex) == null) {
            return null;
        }
        return new SecureBytes(cs.getBytes(columnIndex));
    }
}