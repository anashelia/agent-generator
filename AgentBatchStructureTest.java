import check.*;
import static check.Use.*;

import module org.junit.jupiter;

@BeforeAll
public static void init() {
    // usedLang = Lang.EN; // uncomment to enforce the message language
    Use.withTypeArg("T")
       .theClass("agentgenerator.registry.AgentBatch")
       .that(hasUsualModifiers())
       .info("""
           A generic container for a batch of elements.
           The type parameter {@code T} lets you create a type-safe batch for any
           element type, e.g. {@code AgentBatch<Agent>} or {@code AgentBatch<SpecializedAgent>},
           without needing casts when reading elements back out.
           The {@link #add(Object[])} method uses varargs, so callers can supply
           any number of elements in one call.
       """);
}

@Test
public void fieldAgents() {
    it.hasField("agents: List of T")
      .thatIs(INSTANCE_LEVEL, FULLY_IMPLEMENTED, NOT_MODIFIABLE, VISIBLE_TO_NONE)
      .thatHasNo(GETTER, SETTER)
      .info("Internal list holding the batched elements.");
}

@Test
public void constructor() {
    it.hasConstructor(withNoParams())
      .thatIs(VISIBLE_TO_ALL);
}

@Test
public void methodAdd() {
    it.hasMethod("add", withParams("newAgents: array of T"))
      .thatIs(INSTANCE_LEVEL, FULLY_IMPLEMENTED, NOT_MODIFIABLE, VISIBLE_TO_ALL)
      .thatReturnsNothing()
      .info("""
          Adds one or more elements to this batch in a single call.
          Uses {@code @SafeVarargs} because the method does not expose the varargs array.
      """);
}

@Test
public void methodGetAll() {
    it.hasMethod("getAll", withNoParams())
      .that(hasUsualModifiers())
      .thatReturns("List of T", "An unmodifiable live view of the batch contents.")
      .info("""
          Returns an unmodifiable view of all elements currently in the batch.
          The returned list reflects any future additions, but callers cannot mutate it.
      """);
}

@Test
public void methodSize() {
    it.hasMethod("size", withNoParams())
      .that(hasUsualModifiers())
      .thatReturns("int", "The number of elements currently in this batch.");
}

@Test
public void text() {
    it.has(TEXTUAL_REPRESENTATION);
}

void main() {}


