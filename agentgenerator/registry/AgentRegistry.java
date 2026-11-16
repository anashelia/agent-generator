package agentgenerator.registry;

import module java.base;
import agentgenerator.Agent;
import agentgenerator.AgentType;
import java.util.NoSuchElementException;

public class AgentRegistry {
    private final Map<String, Agent> agents;

    private final Set<AgentType> typesPresent;
    public Set<AgentType> getTypesPresent() {
        return this.typesPresent;
    }

    public AgentRegistry() {
        this.agents = new HashMap<String, Agent>();
        this.typesPresent = new HashSet<AgentType>();
    }

    public void register(Agent agent) {
        if(this.agents.containsKey(agent.getName())) {
            throw new IllegalArgumentException();
        }

        this.agents.put(agent.getName(), agent);
        this.typesPresent.add(agent.getType());
    }

    public Agent lookup(String name) {
        if(!this.agents.containsKey(name)) {
            throw new NoSuchElementException();
        }

        return this.agents.get(name);
    }

    public List<Agent> getByType(AgentType type) {
        List<Agent> result = new ArrayList<>();
        for(Agent a : agents.values()) {
            if(a.getType() == type) {
                result.add(a);
            }
        }
        return result;
    }

    public int size() {
        return agents.size();
    }

}