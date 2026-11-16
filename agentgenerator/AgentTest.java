package agentgenerator;

import static org.junit.jupiter.api.Assertions.*;
import module org.junit.jupiter;
import module java.base;
import agentgenerator.AgentType;


public class AgentTest {
    @Test
    void constructorThrowsOnNullName() {
        boolean threwException = false;
        try {
            new Agent(null, AgentType.EXPLORER, 5);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }
        assertTrue(threwException);
    }

    @Test
    void constructorThrowsOnEmptyName() {
        boolean threwException = false;
        try {
            new Agent("", AgentType.EXPLORER, 5);
        }catch (IllegalArgumentException e) {
            threwException = true;
        }
        assertTrue(threwException);
    }

    @Test
    void addCapabilitiesVarargs() {
        Agent a = new Agent("Ana",AgentType.EXPLORER, 5);

        a.addCapabilities("Hi", "There");
        assertEquals(2, a.getCapabilities().size());
    }

    @Test
    void spawnCreatesCorrectChild() {
        Agent a1 = new Agent("a", AgentType.EXPLORER, 5);
        Agent a2 = a1.spawn();

        assertEquals("a-G6", a2.getName());
        assertEquals(AgentType.EXPLORER, a2.getType());
        assertEquals(6, a2.getGeneration());
    }

}