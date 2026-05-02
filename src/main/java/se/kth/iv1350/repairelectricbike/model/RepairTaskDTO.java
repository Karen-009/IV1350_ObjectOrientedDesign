package se.kth.iv1350.repairelectricbike.model;

/**
 * Contains all information about one repair task.
 * This is an immutable data transfer object.
 */
public final class RepairTaskDTO {
    private final String name;
    private final String description;
    private final Amount cost;
    private final RepairTaskState state;

    /**
     * Creates a new instance. Matches the constructor shown
     *
     * @param name        The name of the repair task.
     * @param description A description of the repair work.
     * @param cost        The cost of this repair task.
     * @param state       The current state of this repair task.
     */
    public RepairTaskDTO(String name, String description,
                          Amount cost, RepairTaskState state) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.state = state;
    }

    /**
     * @return The name of this repair task.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The description of this repair task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The cost of this repair task.
     */
    public Amount getCost() {
        return cost;
    }

    /**
     * @return The current state of this repair task.
     */
    public RepairTaskState getState() {
        return state;
    }
}
