package se.kth.iv1350.repairelectricbike.model;

/**
 * Contains all information about one diagnostic task result.
 * This is an immutable data transfer object.
 */
public class DiagnosticTaskDTO {
    private final String name;
    private final String description;
    private final Amount cost;
    private final String result;

    /**
     * Creates a new instance.
     * 
     * @param name        The name of the diagnostic task.
     * @param description A description of what was checked.
     * @param cost        The cost of performing this diagnostic.
     * @param result      The result or finding of the diagnostic.
     */
    public DiagnosticTaskDTO(String name, String description, Amount cost, String result) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.result = result;
    }

    /**
     * @return The name of the diagnostic task.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The description of the diagnostic task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The cost of this diagnostic task.
     */
    public Amount getCost() {
        return cost;
    }

    /**
     * @return The result of this diagnostic task.
     */
    public String getResult() {
        return result;
    }
}