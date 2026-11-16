package agentgenerator;

import agentgenerator.capability.Capability;

public class SpecializedAgent extends Agent {
    private final Capability primaryCapability;
    
    public Capability getPrimaryCapability() {
        return primaryCapability;
    }
    
    public SpecializedAgent(String name, AgentType type, int generation, Capability primaryCapability) {
        super(name, type, generation);
        this.primaryCapability = primaryCapability;
    }
    
    @Override
    public SpecializedAgent spawn() {
        return new SpecializedAgent(
            getName() + "-G" + (getGeneration() + 1),
            getType(),
            getGeneration() + 1,
            primaryCapability
        );
    }
    
    @Override
    public String toString() {
        return "SpecializedAgent[name='" + getName() + "', type=" + getType() + 
               ", gen=" + getGeneration() + ", caps=" + getCapabilities() + 
               ", primary=" + primaryCapability.describe() + "]";
    }
}