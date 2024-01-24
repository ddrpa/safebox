package cc.ddrpa.playground.safeboxplayground;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@MybatisPlusTest
@Rollback(false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("local")
public class DBOperationsTests {
    private static final Logger logger = LoggerFactory.getLogger(DBOperationsTests.class);

    @Autowired
    private ClientMapper clientMapper;

    @Test
    void createAndSaveClient() {
        Long actualClientId = new Random().nextLong();
        Client client = new Client()
                .setId(actualClientId)
                .setPurchasePassword("123456".getBytes(StandardCharsets.UTF_8))
                .setName("John Doe");
        clientMapper.insert(client);
        assertTrue(clientMapper.selectById(actualClientId).getId().equals(actualClientId));
        var clientsFromDB = clientMapper.selectList(
                Wrappers.<Client>lambdaQuery()
                        .eq(Client::getId, actualClientId));
        assertTrue(clientsFromDB.get(0).getId().equals(actualClientId));
        assertTrue(clientMapper.selectById(actualClientId).getId().equals(actualClientId));
    }
}