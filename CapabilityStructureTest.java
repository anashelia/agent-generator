import check.*;
import static check.Use.*;

import module org.junit.jupiter;

@BeforeAll
public static void init() {
    // usedLang = Lang.EN; // uncomment to enforce the message language
    Use.theInterface("agentgenerator.capability.Capability")
       .thatIs(INSTANCE_LEVEL, NOT_IMPLEMENTED, MODIFIABLE, VISIBLE_TO_ALL)
       .info("""
           Defines the contract for any agent capability.
           A capability has a name, can execute an action on a string input,
           and can describe itself.
       """);
}

@Test
public void methodGetName() {
    it.hasMethod("getName", withNoParams())
      .that(hasUsualModifiers())
      .thatReturns("String", "The short identifier of this capability, e.g. {@code 'scan'} or {@code 'build'}.");
}

@Test
public void methodExecute() {
    it.hasMethod("execute", withParams("input: String"))
      .that(hasUsualModifiers())
      .thatReturns("String", "The transformed or annotated output string.")
      .info("Applies this capability to the given input and returns the result.");
}

@Test
public void methodDescribe() {
    it.hasMethod("describe", withNoParams())
      .that(hasUsualModifiers())
      .thatReturns("String", "Formatted description, e.g. {@code Capability[scan]}.");
}

void main() {}


