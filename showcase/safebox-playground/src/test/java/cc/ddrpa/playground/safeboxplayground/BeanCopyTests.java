package cc.ddrpa.playground.safeboxplayground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import static org.junit.jupiter.api.Assertions.*;

public class BeanCopyTests {
    @Test
    void should_be_null_when_copy() {
        Client source = new Client()
                .setId(123L)
                .setPurchasePassword("123456".getBytes())
                .setName("John Doe");
        ClientVO target = new ClientVO();
        BeanUtils.copyProperties(source, target);
        assertNull(target.getId());
        assertNull(target.getPurchasePassword());
        assertEquals(source.getName(), target.getName());
    }
}