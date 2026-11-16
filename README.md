AGENT GENERATOR SIMULATION
==========================

A Java-based simulation of a swarm of autonomous agents. It features a 
dynamic registry system, generational spawning mechanics, and a flexible 
capability framework built on the Strategy pattern.

The project includes custom structural and functional test suites provided 
by the university, ensuring strict adherence to object-oriented design 
principles, encapsulation, and generics.


OVERVIEW
--------

This system models how a swarm of agents is generated, evolves over 
generations, and is managed within a centralized registry. 

Agents have a name, a role (type), a generation number, and a list of 
capabilities. The system allows agents to "spawn" child agents, 
incrementing their generation. The registry enforces unique names and 
provides quick lookups and type-filtering. 

The project also supports exporting and importing the registry to/from a 
plain text file.


FEATURES
--------

  * Generational Spawning: Create a generation of agents and spawn the 
    next generation, automatically incrementing names and generation counters.
  * Capability Framework: Agents can be equipped with capabilities (like 
    Scan or Build) that transform input strings.
  * Registry Management: A type-safe registry that prevents duplicate 
    names, tracks present AgentTypes, and retrieves agents by type.
  * Generics: Utilizes a custom generic container (AgentBatch<T>) to 
    group elements safely without needing casts.
  * File I/O: Save and load the state of the registry to/from a CSV-like 
    text file.
  * Automated Testing: Includes both structural tests (to verify class 
    structure) and functional tests (to verify logic).


PROJECT STRUCTURE
-----------------

  Source Code (src/):
    - agentgenerator/Agent.java             : The base agent class.
    - agentgenerator/AgentType.java         : Enum of agent roles.
    - agentgenerator/SpecializedAgent.java  : Agent with a primary capability.
    - agentgenerator/AgentTypeHelper.java   : Utility for agent descriptions.
    
    - agentgenerator/capability/            : Capability interface and classes.
    
    - agentgenerator/registry/              : AgentRegistry and AgentBatch.
    
    - agentgenerator/spawner/               : AgentSpawner for generational logic.

  Test Code (test/):
    - agentgenerator/AgentTest.java         : Core functional tests.
    - agentgenerator/AgentTypeHelperTest.java: Functional tests for the helper.
    
    - *StructureTest.java                   : University-provided structural 
                                              tests (Agent, AgentBatch, etc.).
    - AgentGeneratorTestSuite.java          : The main test suite runner.

  Grading / Build Files:
    - check.cmd                             : Script to run the structural 
                                              and functional tests.
    - CheckThat.txt                         : Instructions for the test runner.


HOW TO COMPILE AND RUN TESTS
----------------------------

This project was created using Apache NetBeans. 

To run the tests using the provided script:
1. Open a terminal (or Windows PowerShell) in the root directory.
2. Ensure you have Java 25+ installed.
3. Run the following command:

   .\check.cmd AgentGeneratorTestSuite.java AgentGeneratorTestSuite

(If you are on Linux/Mac, you may need to run `chmod +x check.cmd` first).

To run the project normally in NetBeans:
1. Open the project in NetBeans.
2. Right-click the project and select "Test".


LICENSE
-------

This program is provided as-is for educational and demonstration
purposes. Use and modify it freely.