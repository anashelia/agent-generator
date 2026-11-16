import check.*;
import static check.Use.*;

import module org.junit.jupiter;

@Test
public void elements() {
    Use.theEnum("agentgenerator.AgentType")
       .ofEnumElements("EXPLORER", "BUILDER", "ANALYZER", "COORDINATOR")
       .that(hasUsualModifiers())
       .info("""
           Enumerates the four possible roles an agent can hold in the swarm.
           No constructor, field, or method is needed.
       """);
}

void main() {}


