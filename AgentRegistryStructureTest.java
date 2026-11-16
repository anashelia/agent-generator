import check.*;
import static check.Use.*;

import module org.junit.jupiter;

@BeforeAll
public static void init() {
    // usedLang = Lang.EN; // uncomment to enforce the message language
    Use.theClass("agentgenerator.registry.AgentRegistry")
       .that(hasUsualModifiers())
       .info("""
           A registry that maps agent names to Agent instances.
           Maintains a secondary set of all AgentTypes currently present
           for quick type-membership queries.
           Supports serialisation to/from a plain text file (CSV-like: name,TYPE,generation).
       """);
}

@Test
public void fieldAgents() {
    it.hasField("agents: Map of String to agentgenerator.Agent")
      .thatIs(INSTANCE_LEVEL, FULLY_IMPLEMENTED, NOT_MODIFIABLE, VISIBLE_TO_NONE)
      .thatHasNo(GETTER, SETTER)
      .info("Primary store mapping unique agent names to their Agent instances.");
}

@Test
public void fieldTypesPresent() {
    it.hasField("typesPresent: Set of agentgenerator.AgentType")
      .thatIs(INSTANCE_LEVEL, FULLY_IMPLEMENTED, NOT_MODIFIABLE, VISIBLE_TO_NONE)
      .thatHas(GETTER)
      .thatHasNo(SETTER)
      .info("""
          Set of AgentTypes that have at least one registered representative.
          Updated on every {@link #register(Agent)} call.
      """);
}

@Test
public void constructor() {
    it.hasConstructor(withNoParams())
      .thatIs(VISIBLE_TO_ALL);
}

@Test
public void methodRegister() {
    it.hasMethod("register", withParams("agent: agentgenerator.Agent"))
      .that(hasUsualModifiers())
      .thatReturnsNothing()
      .thatThrows("IllegalArgumentException", "if an agent with the same name is already registered.")
      .info("""
          Registers the given agent in this registry.
          The agent's name must be unique — duplicate names are not allowed.
      """);
}

@Test
public void methodLookup() {
    it.hasMethod("lookup", withParams("name: String"))
      .that(hasUsualModifiers())
      .thatReturns("agentgenerator.Agent", "The Agent registered under {@code name}.")
      .thatThrows("NoSuchElementException", "if no agent with the given name is registered.")
      .info("Looks up an agent by its unique name.");
}

@Test
public void methodGetByType() {
    it.hasMethod("getByType", withParams("type: agentgenerator.AgentType"))
      .that(hasUsualModifiers())
      .thatReturns("List of agentgenerator.Agent", "A new, mutable list of agents of the requested type (may be empty).")
      .info("""
          Returns a new list containing all agents whose type matches {@code type}.
          The returned list is independent of the registry — callers cannot affect
          the registry's internal state through it.
      """);
}

@Test
public void methodSize() {
    it.hasMethod("size", withNoParams())
      .that(hasUsualModifiers())
      .thatReturns("int", "The total number of agents currently in the registry.");
}

@Test
public void methodSaveToFile() {
    it.hasMethod("saveToFile", withParams("filename: String"))
      .that(hasUsualModifiers())
      .thatReturnsNothing()
      .thatThrows("IOException", "if the file cannot be created or written.")
      .info("""
          Writes one line per agent to the named file in the format {@code name,TYPE,generation}.
          Uses {@link PrintWriter} wrapping {@link java.io.FileWriter}.
      """);
}

@Test
public void methodLoadFromFile() {
    it.hasMethod("loadFromFile", withParams("filename: String"))
      .thatIs(USABLE_WITHOUT_INSTANCE, FULLY_IMPLEMENTED, MODIFIABLE, VISIBLE_TO_ALL)
      .thatReturns("AgentRegistry", "A new AgentRegistry populated with the agents read from the file.")
      .thatThrows("IOException", "if the file cannot be read.")
      .info("""
          Reconstructs an AgentRegistry from a file produced by {@link #saveToFile(String)}.
          Uses {@link Scanner} (not {@code BufferedReader}) to read lines.
          Each line must be in the format {@code name,TYPE,generation}.
      """);
}

void main() {}


