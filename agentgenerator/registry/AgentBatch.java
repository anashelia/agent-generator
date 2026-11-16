package agentgenerator.registry;

import module java.base;

public class AgentBatch<T> {
    private final List<T> agents;

    public AgentBatch() {
        this.agents = new ArrayList<>();
    }

    @SafeVarargs
    public final void add(T... newAgents) {
        if (newAgents != null) {
            for (T a : newAgents) {
                if (a != null) {
                    this.agents.add(a);
                }
            }
        }
    }

    public List<T> getAll() {
        return Collections.unmodifiableList(agents);
    }

    public int size() {
        return agents.size();
    }

    @Override
    public String toString() {
        return "AgentBatch{" +
               "agents=" + agents +
               ", size=" + size() +
               '}'; 
    }

}