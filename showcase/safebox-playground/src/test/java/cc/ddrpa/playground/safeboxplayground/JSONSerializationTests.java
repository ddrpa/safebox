package cc.ddrpa.playground.safeboxplayground;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class JSONSerializationTests {
    @Test
    void should_throw_InvalidDefinitionException_when_serialize() throws JsonProcessingException {
        Client client = new Client()
                .setId(123L)
                .setPurchasePassword("123456".getBytes())
                .setName("John Doe");
        Exception exception = assertThrows(InvalidDefinitionException.class, () -> new ObjectMapper().writeValueAsString(client));
        var json = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS).writeValueAsString(client);
        System.out.println(json);
    }
}