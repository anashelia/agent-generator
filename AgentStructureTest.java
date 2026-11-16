import check.*;
import static check.Use.*;

import module org.junit.jupiter;

@BeforeAll
public static void init() {
    // usedLang = Lang.EN; // uncomment to enforce the message language
    Use.theClass("agentgenerator.Agent")
       .that(hasUsualModifiers())
       .info("""
           Represents a single agent in the swarm.
           An agent has a unique name, a role (AgentType), a generation counter,
           and a mutable list of capability tags.
           It can spawn child agents and must be properly encapsulated:
           the internal capabilities list must never leak to callers.
       """);
}

@Test
public void fieldName() {
    it.hasField("name: String")
      .thatIs(INSTANCE_LEVEL, FULLY_IMPLEMENTED, NOT_MODIFIABLE, VISIBLE_TO_NONE)
      .thatHas(GETTER)
      .thatHasNo(SETTER)
      .info("Unique identifier of the agent within a registry.");
}

@Test
public void fieldType() {
    it.hasField("type: AgentType")
      .thatIs(INSTANCE_LEVEL, FULLY_IMPLEMENTED, NOT_MODIFIABLE, VISIBLE_TO_NONE)
      .thatHas(GETTER)
      .thatHasNo(SETTER)
      .info("The role this agent fulfils in the swarm.");
}

@Test
public void fieldGeneration() {
    it.hasField("generation: int")
      .thatIs(INSTANCE_LEVEL, FULLY_IMPLEMENTED, NOT_MODIFIABLE, VISIBLE_TO_NONE)
      .thatHas(GETTER)
      .thatHasNo(SETTER)
      .info("""
          Generation counter: 0 for directly created agents,
          incremented by 1 each time spawn() is called.
      """);
}

@Test
public void fieldCapabilities() {
    it.hasField("capabilities: ArrayList of String")
      .thatIs(INSTANCE_LEVEL, FULLY_IMPLEMENTED, NOT_MODIFIABLE, VISIBLE_TO_NONE)
      .thatHas(GETTER)
      .thatHasNo(SETTER)
      .info("""
          Internal list of capability tags attached to this agent.
          Must remain encapsulated — callers receive only copies.
      """);
}

@Test
public void constructor() {
    it.hasConstructor(withArgsLikeFields("name", "type", "generation"))
      .thatIs(VISIBLE_TO_ALL)
      .thatThrows("IllegalArgumentException", "if {@code name} is null or empty, or {@code generation} is negative.")
      .info("""
          Creates an agent with the given name, type, and generation.
          The capabilities list starts empty.
      """)
      .testWith(testCase("constructorThrowsOnNullName"), "Verify that a null name is rejected.")
      .testWith(testCase("constructorThrowsOnEmptyName"), "Verify that an empty name is rejected.");
}

@Test
public void methodAddCapability() {
    it.hasMethod("addCapability", withParams("capability: String"))
      .that(hasUsualModifiers())
      .thatReturnsNothing()
      .info("Appends a single capability tag to the internal list.");
}

@Test
public void methodAddCapabilities() {
    it.hasMethod("addCapabilities", withParams("caps: array of String"))
      .that(hasUsualModifiers())
      .thatReturnsNothing()
      .info("""
          Varargs convenience method: appends one or more capability tags in a single call.
          Equivalent to calling {@link #addCapability(String)} repeatedly.
      """)
      .testWith(testCase("addCapabilitiesVarargs"), "Verify that multiple tags can be added in one call.");
}

@Test
public void methodSpawn() {
    it.hasMethod("spawn", withNoParams())
      .that(hasUsualModifiers())
      .thatReturns("Agent", "A new Agent representing the next generation.")
      .info("""
          Creates and returns a new child agent.
          The child's name is {@code this.name + "-G" + (generation + 1)},
          its type is inherited, its generation is incremented by 1,
          and it starts with an empty capabilities list.
      """)
      .testWith(testCase("spawnCreatesCorrectChild"), "Verify that the child has the correct name, type, generation, and empty capabilities.");
}

@Test
public void text() {
    it.has(TEXTUAL_REPRESENTATION)
      .info("""
          Returns a string representation in the format:
          {@code Agent[name='X', type=TYPE, gen=G, caps=[...]]}
      """);
}

@Test
public void eq() {
    it.has(EQUALITY_CHECK)
      .info("""
          Two agents are considered equal if and only if their names are equal.
          Names serve as unique identifiers within a registry.
      """);
}

void main() {}


