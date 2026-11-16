import check.*;
import static check.Use.*;

import module org.junit.jupiter;

@BeforeAll
public static void init() {
    // usedLang = Lang.EN; // uncomment to enforce the message language
    Use.theClass("agentgenerator.SpecializedAgent", withParent("agentgenerator.Agent"))
       .that(hasUsualModifiers())
       .info("""
           Extends Agent with a primary Capability that defines the agent's main skill.
           Overrides {@link #spawn()} with a covariant return type so that spawning a
           SpecializedAgent yields another SpecializedAgent with the same primary capability.
       """);
}

@Test
public void fieldPrimaryCapability() {
    it.hasField("primaryCapability: agentgenerator.capability.Capability")
      .thatIs(INSTANCE_LEVEL, FULLY_IMPLEMENTED, NOT_MODIFIABLE, VISIBLE_TO_NONE)
      .thatHas(GETTER)
      .thatHasNo(SETTER)
      .info("The primary capability that characterises this agent's specialty.");
}

@Test
public void constructor() {
    it.hasConstructor(withParams("name: String", "type: AgentType", "generation: int", "primaryCapability: agentgenerator.capability.Capability"))
      .thatIs(VISIBLE_TO_ALL)
      .thatThrows("IllegalArgumentException", "if {@code name} is null or empty, or {@code generation} is negative (delegated to super).")
      .info("Creates a specialised agent with the given name, type, generation, and primary capability.");
}

@Test
public void text() {
    it.has(TEXTUAL_REPRESENTATION)
      .info("""
          Returns a string representation in the format:
          {@code SpecializedAgent[name='X', type=TYPE, gen=G, caps=[...], primary=Capability[scan]]}
      """);
}

@Test
public void methodSpawn() {
    it.hasMethod("spawn", withNoParams())
      .that(hasUsualModifiers())
      .thatReturns("SpecializedAgent", "A new SpecializedAgent representing the next generation.")
      .info("""
          Creates and returns a new SpecializedAgent child.
          The child's name is {@code this.name + "-G" + (generation + 1)},
          its type and primary capability are inherited,
          and its generation is incremented by 1.
          Covariant return type: callers that hold a SpecializedAgent reference
          receive a SpecializedAgent without a cast.
      """);
}

void main() {}


