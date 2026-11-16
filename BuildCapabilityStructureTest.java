import check.*;
import static check.Use.*;

import module org.junit.jupiter;

@BeforeAll
public static void init() {
    // usedLang = Lang.EN; // uncomment to enforce the message language
    Use.theClass("agentgenerator.capability.BuildCapability", withInterface("agentgenerator.capability.Capability"))
       .that(hasUsualModifiers())
       .info("""
           A Capability implementation that simulates building.
           Prefixes any input with {@code [BUILD] }.
       """);
}

@Test
public void constructor() {
    it.hasConstructor(withNoParams())
      .thatIs(VISIBLE_TO_ALL);
}

@Test
public void methodGetName() {
    it.hasMethod("getName", withNoParams())
      .that(hasUsualModifiers())
      .thatReturns("String", "The string {@code 'build'}.");
}

@Test
public void methodExecute() {
    it.hasMethod("execute", withParams("input: String"))
      .that(hasUsualModifiers())
      .thatReturns("String", "The input string prefixed with {@code '[BUILD] '}.");
}

@Test
public void methodDescribe() {
    it.hasMethod("describe", withNoParams())
      .that(hasUsualModifiers())
      .thatReturns("String", "Formatted description, e.g. {@code Capability[build]}.");
}

void main() {}


