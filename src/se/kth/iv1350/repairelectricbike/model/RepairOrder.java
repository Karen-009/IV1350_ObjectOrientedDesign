package se.kth.iv1350.repairelectricbike.model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderDTO;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderState;

/**
 * Handles all calls to the data storage for repair orders.
 */
public class RepairOrder {
    private String phoneNumber;
    private String id;
    private String date;
    private String customersProblemDescription;
    private String estimatedCompletionDate;
    private RepairOrderState state;
    private Bike bike;

    private List<DiagnosticTaskDTO> diagnosticResults = new ArrayList<>();
    private List<RepairTaskDTO> repairTasks = new ArrayList<>();

    public RepairOrder(String problemDesc, String phoneNumber, Bike bike) {

    }

    public RepairOrder(RepairOrderDTO dto) {

    }

    /**
     * Accepts this repair order. The state is changed to
     * Accepted and the registry is updated to reflect the new state.
     *
     * @param registry The registry used to persist the
     *                 updated repair order.
     */
    public void acceptRepairOrder(RepairOrderRegistry registry) {
        this.state = RepairOrderState.ACCEPTED;
        registry.updateRepairOrder(this.getRepairOrderDTO());
    }

    /**
     * Rejects this repair order. The state is changed to
     * Rejected and the registry is updated to reflect the new state.
     *
     * @param registry The registry used to persist the
     *                 updated repair order.
     */
    public void rejectRepairOrder(RepairOrderRegistry registry) {
        this.state = RepairOrderState.REJECTED;
        registry.updateRepairOrder(this.getRepairOrderDTO());
    }

    public void addDiagnosticResult(DiagnosticTaskDTO diagTaskResult) {
        diagnosticResults.add(diagTaskResult);
        this.state = RepairOrderState.READY_FOR_APPROVAL;
    }

    /**
     * Updates the internal state of this entity using data from a DTO.
     * * @param dto The data transfer object containing new information.
     */
    public void updateState(RepairOrderDTO dto) {
        this.state = dto.getState();
    }

    /**
     * Creates a DTO representing the current state.
     * This allows the Registry and View to see the data without
     * being able to modify the actual RepairOrder object.
     * * @return A read-only snapshot of this repair order.
     */
    public RepairOrderDTO toDTO() {
        new RepairOrderDTO(
                this.id,
                this.phoneNumber,
                this.date,
                this.customersProblemDescription,
                this.estimatedCompletionDate,
                this.state);
    }
}