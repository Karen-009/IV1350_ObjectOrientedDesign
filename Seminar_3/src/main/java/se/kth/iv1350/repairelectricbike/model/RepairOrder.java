package se.kth.iv1350.repairelectricbike.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import se.kth.iv1350.repairelectricbike.integration.RepairOrderDTO;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderState;

/**
 * Represent a repair order for an electric bike. 
 */
public class RepairOrder {
    private String id;
    private String date;
    private String phoneNumber;
    private String customersProblemDescription;
    private String estimatedCompletionDate;
    private RepairOrderState state;
    private Bike bike;

    private List<DiagnosticTaskDTO> diagnosticResults = new ArrayList<>();
    private List<RepairTaskDTO> repairTasks = new ArrayList<>();

    /**
    * Creates a new instance representing a new repair order.
    * The state is set to <code>NEWLY_CREATED</code> and a unique ID is generated.
    * 
    * @param problemDesc Description of the problem.
    * @param phoneNumber Customer phone number.
    * @param bike The bike in the repair order.
    */

    public RepairOrder(String problemDesc, String phoneNumber, Bike bike) {
        this.id = UUID.randomUUID().toString();
        this.date = LocalDate.now().toString();
        this.phoneNumber = phoneNumber;
        this.customersProblemDescription = problemDesc;
        this.estimatedCompletionDate = "To Be Announced";
        this.state = RepairOrderState.NEWLY_CREATED;
        this.bike = bike;
    }

    /** 
     * Creates repair order from a DTO. 
     * 
     * @param dto The DTO containing repair order information
    */
    public RepairOrder(RepairOrderDTO dto) {
        this.id = dto.getId();
        this.date = dto.getDate();
        this.customersProblemDescription = dto.getCustomersProblemDescription();
        this.estimatedCompletionDate = dto.getEstimatedCompletionDate();
        this.state = dto.getState();
    }

    /**
     * @return The bike.
     */
    public Bike getBike() {
        return bike;
    }

    /**
     * @return The unique repair order ID.
     */
    public String getId() {
        return id;
    }

    /**
     * @return The customer's phone number associated with this repair order.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Accepts the repair order.
     */
    public void acceptRepairOrder() {
        this.state = RepairOrderState.ACCEPTED;
    }
    
    /**
     * Rejects repair order.
     */
    public void rejectRepairOrder() {
        this.state = RepairOrderState.REJECTED;
    }

    /**
     * Adds the diagnostic result to this repair order.
     *  
     * @param diagTaskResult The diagnostic result to add to this repair order.
    */
    public void addDiagnosticResult(DiagnosticTaskDTO diagTaskResult) {
        diagnosticResults.add(diagTaskResult);
        this.state = RepairOrderState.READY_FOR_APPROVAL;
    }

    /**
     * Adds a repair task to the repair order.
     * 
     * @param repairTask The repair task to add.
     */
    public void addRepairTask(RepairTaskDTO repairTask) {
        repairTasks.add(repairTask);
    }

    /**
     * Updates the internal state of this entity using data from a DTO.
     * 
     * @param dto The data transfer object containing new information.
     */
    public void updateState(RepairOrderDTO dto) {
        this.state = dto.getState();
    }

    /**
     * Creates a DTO representing the current state.
     * 
     * @return A read-only snapshot of this repair order.
     */
    public RepairOrderDTO getRepairOrderDTO() {
        return new RepairOrderDTO(
                this.id,
                this.date,
                this.customersProblemDescription,
                this.estimatedCompletionDate,
                this.state);
    }
}