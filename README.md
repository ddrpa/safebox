# Safebox - 内部字段包装器

一个系统中可能存在某些属性是仅供内部使用的。

举个不太恰当的例子，获取符合条件 A 的用户列表中返回了 ID 为 `1234` 的用户，获取符合条件 B 的用户列表也返回了 ID 为 `1234` 的用户，那么一个第三方就可以根据这两个互不相关的功能推断出存在一个 ID 为 `1234` 的用户既符合条件 A 又符合条件 B 的事实，而这个信息可能是系统原本不打算提供的。

为了避免这个问题，通常需要在各个公开接口中构造和返回专门的、做好字段删减的 `Response`，`Result` 或是 `VO` 对象。但在一些短平快项目中，或是由于代码生成工具完成了大部分工作，或是由于不熟悉项目的开发人员忽略了这种要求，导致某些功能直接返回 `Entity` 对象（或是通过 BeanUtils 工具类 copy 了不该透露的属性）。

本项目参考 [tink-crypto/tink-java - GitHub](https://github.com/tink-crypto/tink-java) 中的 `com.google.crypto.tink.util.SecretBytes` 实现，提供了专门的包装类处理这种问题。通过不提供默认的 getter 方法避免属性被序列化或被拷贝，并提供了相应的 TypeHandler 类帮助实现数据库的读写。

## 使用方法

见本项目 `showcase/safebox-playground/src/test/java/cc/ddrpa/playground/safeboxplayground` 目录下的单元测试代码。

在项目中引用 `cc.ddrpa.repack.safebox:safebox-core`，使用 `SecureLong`，`SecureBytes` 和 `SecureString` 类型替换原有的属性类型。

```java
class Client {
  private SecureLong secretInnerId;
  // ……
  public Client setSecretInnerId(Long secretInnerId) {
    this.secretInnerId = new SecureLong(secretInnerId);
    return this;
  }  
```

使用 `get(SecureAccess)` 方法可以获取原始值。

```java
var acutalId = client.getSecretInnerId().get(SecureAccess.gain());
```

如果使用 Jackson 等 JSON 序列化工具序列化该对象，会抛出异常。如果使用 BeanUtils 等工具复制该对象，Secure 类型不会被拷贝。

### 读写数据库

如果使用 Mybatis-plus，需要在项目中添加 `cc.ddrpa.repack.safebox:safebox-mybatis` 依赖，然后为类型注册 TypeHandler。一种简单的方法是在项目的配置文件中添加：

```yaml
mybatis-plus:
  type-handlers-package: cc.ddrpa.repack.safebox.typehandler
```

可以正常使用 Mybatis-plus 的 `lambdaQuery` 读写数据库。

```java
var clientFromDB = clientMapper.selectList(
    Wrappers.<Client>lambdaQuery()
            .eq(Client::getId, new SecureLong(acutalId)));
assertTrue(clientFromDB.get(0).getId().equals(acutalId));
// or
var clientById = clientMapper.selectById(acutalId).getId();
assertTure(clientById.equals(acutalId));
```
