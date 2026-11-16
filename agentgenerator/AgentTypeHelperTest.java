package agentgenerator;

import static org.junit.jupiter.api.Assertions.*;
import module org.junit.jupiter;

public class AgentTypeHelperTest {
    @Test
    void agentTypeDescriptions() {
        AgentTypeHelper a1 = new AgentTypeHelper();
        String result = a1.getDescription(AgentType.EXPLORER);
        assertEquals("EXPLORER", result);
    }
}