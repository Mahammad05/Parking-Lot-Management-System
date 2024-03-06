public class Slot {
    
    private String type;
    private Vehicle vehicle;
    private String ticketId;

    public Slot (String type) {
        this.type = type;
        this.vehicle = null;
        this.ticketId = null;
    }

    public String getType () {
        return type;
    }

    public boolean isAvailable () {
        return vehicle == null;
    }

    public void parkVehicle (Vehicle vehicle, String ticketId) {
        this.vehicle = vehicle;
        this.ticketId = ticketId;
    }

    public void vacateSlot () {
        this.vehicle = null;
        this.ticketId = null;
    }

    public String getTicketId () {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean isSameType(String type) {
        return this.type.equals(type);
    }
}
