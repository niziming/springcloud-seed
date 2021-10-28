import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DemoApplicationTests.class)
class DemoApplicationTests {

    @Test
    void contextLoads() {
        AbilityEntity ability = new AbilityEntity();
        ability.setId(1L);
        System.out.println(JSONObject.toJSON(ability));
        ability.setName("");//只要不是null, 都不会被忽略
        System.out.println(JSONObject.toJSON(ability));

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Setter
    @Getter
    public class AbilityEntity{
        private Long id;
        private String name;
    }

}