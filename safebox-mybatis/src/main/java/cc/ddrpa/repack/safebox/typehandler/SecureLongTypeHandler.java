package cc.ddrpa.repack.safebox.typehandler;

import cc.ddrpa.repack.safebox.type.SecureLong;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(SecureLong.class)
@MappedJdbcTypes(JdbcType.BIGINT)
public final class SecureLongTypeHandler extends AbstractSecureTypeHandler<SecureLong> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, SecureLong parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, parameter.get(getSecureAccess()));
    }

    @Override
    public SecureLong getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (rs.wasNull()) {
            return null;
        }
        return new SecureLong(rs.getLong(columnName));
    }

    @Override
    public SecureLong getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (rs.wasNull()) {
            return null;
        }
        return new SecureLong(rs.getLong(columnIndex));
    }

    @Override
    public SecureLong getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (cs.wasNull()) {
            return null;
        }
        return new SecureLong(cs.getLong(columnIndex));
    }
}