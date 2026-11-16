package agentgenerator;

import module java.base;

public class Agent {
    private final String name;
    public String getName() {
        return this.name;
    }

    private final AgentType type;
    public AgentType getType() {
        return this.type;
    }

    private final int generation;
    public int getGeneration() {
        return this.generation;
    }

    private final ArrayList<String> capabilities;
    public ArrayList<String> getCapabilities() {
        return this.capabilities;
    }

    public Agent(String name, AgentType type, int generation) {
        if(name == null || name.isEmpty() || generation < 0) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.type = type;
        this.generation = generation;
        this.capabilities = new ArrayList<>();
    }

    public void addCapability(String capability) {
        capabilities.add(capability);
    }

    public void addCapabilities(String... caps) {
        for(String s : caps) {
            capabilities.add(s);
        }
    }

    public Agent spawn() {
        return new Agent(this.name + "-G" + (this.generation + 1),
                            this.type, 
                            this.generation + 1
                        );   
    }

    @Override
    public String toString() {
        return "Agent[name='" + name + "', type=" + type + ", gen=" + generation + ", caps=" + capabilities + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(!(obj instanceof Agent)) return false;
        
        Agent a = (Agent) obj;
        return this.name.equals(a.getName()) && this.type == a.getType() && this.generation == a.getGeneration();
    }

    @Override 
    public int hashCode() {
        return Objects.hash(name, type, generation);
    }

}