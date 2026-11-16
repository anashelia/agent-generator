import check.*;
import static check.Use.*;

import module org.junit.jupiter;

@BeforeAll
public static void init() {
    // usedLang = Lang.EN; // uncomment to enforce the message language
    Use.theClass("agentgenerator.spawner.AgentSpawner")
       .that(hasUsualModifiers())
       .info("""
           Utility class with static factory methods for creating and evolving agent generations.
           Not intended to be instantiated.
       """);
}

@Test
public void constructor() {
    it.hasConstructor(withNoParams())
      .thatIs(VISIBLE_TO_ALL);
}

@Test
public void methodCreateGeneration() {
    it.hasMethod("createGeneration", withParams("type: agentgenerator.AgentType", "count: int"))
      .thatIs(USABLE_WITHOUT_INSTANCE, FULLY_IMPLEMENTED, MODIFIABLE, VISIBLE_TO_ALL)
      .thatReturns("array of agentgenerator.Agent", "An array of {@code count} newly created Agent instances.")
      .info("""
          Creates the first generation of agents as a Java array.
          Agents are named {@code "Agent-0"}, {@code "Agent-1"}, …, {@code "Agent-(count−1)"},
          all at generation 0 with the given type.
      """);
}

@Test
public void methodSpawnNextGeneration() {
    it.hasMethod("spawnNextGeneration", withParams("parents: array of agentgenerator.Agent"))
      .thatIs(USABLE_WITHOUT_INSTANCE, FULLY_IMPLEMENTED, MODIFIABLE, VISIBLE_TO_ALL)
      .thatReturns("List of agentgenerator.Agent", "A new List containing one child agent per parent.")
      .info("""
          Accepts a Java array of parent agents and returns a List of their spawned children.
          Each child is produced by calling {@link Agent#spawn()} on the corresponding parent.
      """);
}

void main() {}


