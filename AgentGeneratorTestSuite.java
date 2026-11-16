import org.junit.jupiter.api.Tag;
import org.junit.platform.suite.api.*;

@SelectClasses({
	AgentGeneratorTestSuite.Part1StructuralTests.class,
	AgentGeneratorTestSuite.Part1FunctionalTests.class,
	AgentGeneratorTestSuite.Part2StructuralTests.class,
	AgentGeneratorTestSuite.Part2FunctionalTests.class,
})
@Suite(failIfNoTests=false) public class AgentGeneratorTestSuite {
	@SelectClasses({
        AgentTypeStructureTest.class,
        AgentStructureTest.class,

        AgentSpawnerStructureTest.class,
        AgentBatchStructureTest.class,
    })
    @Suite(failIfNoTests=false) public static class Part1StructuralTests {}

    @SelectClasses({
        agentgenerator.AgentTest.class,
    })
    @Suite(failIfNoTests=false) public static class Part1FunctionalTests {}

	@SelectClasses({
        CapabilityStructureTest.class,
        BuildCapabilityStructureTest.class,
        ScanCapabilityStructureTest.class,

        SpecializedAgentStructureTest.class,

        AgentRegistryStructureTest.class,
    })
    @Suite(failIfNoTests=false) public static class Part2StructuralTests {}

    @SelectClasses({
        agentgenerator.AgentTest.class,
    })
    @Suite(failIfNoTests=false) public static class Part2FunctionalTests {}
}
