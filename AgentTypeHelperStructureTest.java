import check.*;
import static check.Use.*;

import module org.junit.jupiter;

@BeforeAll
public static void init() {
    // usedLang = Lang.EN; // uncomment to enforce the message language
    Use.theClass("agentgenerator.AgentTypeHelper")
       .that(hasUsualModifiers())
       .info("""
           Utility class for working with AgentType constants.
           Not intended to be instantiated.
       """);
}

@Test
public void methodGetDescription() {
    it.hasMethod("getDescription", withParams("type: agentgenerator.AgentType"))
      .thatIs(USABLE_WITHOUT_INSTANCE, FULLY_IMPLEMENTED, MODIFIABLE, VISIBLE_TO_ALL)
      .thatReturns("String", "A short description string for the given type.")
      .info("""
          Returns a human-readable description of the given agent type.
          Uses a switch expression — no constructor or field needed in the enum.
      """)
      .testWith(testCase("agentTypeDescriptions"), "Verify that each constant returns the expected description string.");
}

void main() {}
