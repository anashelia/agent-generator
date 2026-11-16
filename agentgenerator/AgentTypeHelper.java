package agentgenerator;

import agentgenerator.AgentType;

public class AgentTypeHelper {
    public static String getDescription(AgentType type) {
        switch(type) {
            case EXPLORER: return "EXPLORER";
            case BUILDER: return "BUILDER";
            case ANALYZER: return "ANALYZER";
            case COORDINATOR: return "COORDINATOR";
            default: return null;
        }
    }



}