package agentgenerator.spawner;

import agentgenerator.AgentType;
import agentgenerator.Agent;
import module java.base;

public class AgentSpawner{
    public AgentSpawner() {}

    public static Agent[] createGeneration(AgentType type, int count) {
        Agent[] agents = new Agent[count];

        for(int i = 0; i < count; i++) {
            agents[i] = new Agent("Agent-" + i, type, 0);
        }

        return agents;
    }

    public static List<Agent> spawnNextGeneration(Agent[] parents) {
        List<Agent> result = new ArrayList<>();
        for(Agent p : parents) {
            result.add(p.spawn());
        }

        return result;
    }
}