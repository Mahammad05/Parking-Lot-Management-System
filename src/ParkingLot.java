import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private String parkingLotId;
    private List<List<Slot>> slots;

    public ParkingLot(String parkingLotId, int nfloors, int noOfSlotsPerFlr) {
        this.parkingLotId = parkingLotId;
        this.slots = new ArrayList<>();

        for (int floor = 0; floor < nfloors; floor++) {
            slots.add(new ArrayList<>());
            List<Slot> floorSlots = slots.get(floor);
            floorSlots.add(new Slot("truck"));
            floorSlots.add(new Slot("bike"));
            floorSlots.add(new Slot("bike"));

            for (int slotNum = 0; slotNum < noOfSlotsPerFlr; slotNum++) {
                floorSlots.add(new Slot("car"));
            }
        }
    }

    public String parkVehicle(String type, String registration, String color) {
        Vehicle vehicle = new Vehicle(type, registration, color);

        // Find an empyt slot
        Slot emptySlot = findEmptySlot(type);

        if (emptySlot != null) {
            // If there is an empty slot than park the car
            emptySlot.parkVehicle(vehicle, generateTicket(emptySlot));
            return generateTicket(emptySlot);
        }
        else {
            // If there is not an empty slot return the error message
            return "NO slot available for given type";
        }
    }

    private Slot findEmptySlot (String type) {
        // Find an empty slot for a car type
        for (List<Slot> floorSlots : slots) {
            for (Slot slot : floorSlots) {
                if (slot.isAvailable() && slot.getType().equals(type)) {
                    return slot;
                }
            }
        }
        // If there is not an empty slot return null
        return null;
    }

    public int findFloorIndex (Slot slot) {
        for (int floor = 0; floor < slots.size(); floor++) {
            if (slots.get(floor).contains(slot)) {
                return floor;
            }
        }
        return -1;
    }

    private String generateTicket (Slot slot) {
        // Create a ticketId and return it
        int floorNo = findFloorIndex(slot);
        int slotNo = slots.get(floorNo).indexOf(slot);

        return String.format("%s_%d_%d", parkingLotId, floorNo, slotNo);
    }

    public void unPark(String ticketId) {
        Slot parkedSlot = findParkedSlot(ticketId);

        if (parkedSlot != null) {
            // If the car is found, remove the car from the slot
            parkedSlot.vacateSlot();
            System.out.println("Vehicle successfully removed");;
        }
        else {
            // retunr error message if car is not found
            System.out.println("The vehicle with the specified ticket number was not found.");
        }
    }

    private Slot findParkedSlot (String ticketId) {
        for (List<Slot> floorSlots : slots) {
            for (Slot slot : floorSlots) {
                if (slot.getTicketId() != null && slot.getTicketId().equals(ticketId)) {
                    return slot;
                }
            }
        }
        return null;
    }

    public int getNoOfOpenSlots(String type) {
        int openSlotsCount = 0;

        // Find the number of available slots for a specific vehicle type
        for (List<Slot> flooSlots : slots) {
            for (Slot slot : flooSlots) {
                if (slot.isAvailable() && slot.getType().equals(type)) {
                    openSlotsCount++;
                }
            }
        }
        System.out.printf("There are %d empty %s slots in the vehicle Park.%n", openSlotsCount, type);
        return openSlotsCount;
    }

    public void displayOpenSlots(String type) {
        System.out.println("Empyt Slots:");

        // Print empty slots suitable for a specific vehicle type to the screen
        for (int floor =0 ; floor < slots.size(); floor++) {
            for (int slotNum = 0; slotNum < slots.get(floor).size(); slotNum++) {
                Slot slot = slots.get(floor).get(slotNum);

                if (slot.isAvailable() && slot.getType().equals(type)) {
                    System.out.printf("Floor %d, Slot %d%n", floor + 1, slotNum + 1);
                }
            }
        }
    }

    public void displayOccupiedSlots(String type) {
        System.out.println("occupied slots");

        // Print occupied slots suitable for a specific vehicle type to the screen
        for (int floor = 0; floor < slots.size(); floor++) {
            for (int slotNum = 0; slotNum < slots.get(floor).size(); slotNum++) {
                Slot slot = slots.get(floor).get(slotNum);

                if (!slot.isAvailable() && slot.getType().equals(type)) {
                    System.out.printf("Floor %d, Slot %d, Vehicle: %s, License Plate: %s%n", floor + 1, slotNum +1, slot.getVehicle().getType(), slot.getVehicle().getRegistration());
                }
            }
        }
    }
}
