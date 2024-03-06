public class ParkingLotTest {
    public static void main(String[] args) {
        int numberOfFloors = 4;
        int slotsPerFloor = 6;
        ParkingLot parkingLot = new ParkingLot("PR1234", numberOfFloors, slotsPerFloor);

        String car1 = parkingLot.parkVehicle("car", "ABC123", "Red");
        String car2 = parkingLot.parkVehicle("car", "ZTE654", "Grey");

        String bike1 = parkingLot.parkVehicle("bike", "BKE125", "Black");
        String bike2 = parkingLot.parkVehicle("bike", "JHV941", "Orange");

        String truck1 = parkingLot.parkVehicle("truck", "CDP541", "Blue");
        String truck2 = parkingLot.parkVehicle("truck", "SDK984", "Brown");

        parkingLot.getNoOfOpenSlots("car");
        parkingLot.getNoOfOpenSlots("bike");
        parkingLot.getNoOfOpenSlots("truck");

        parkingLot.unPark(truck2);
        parkingLot.unPark(car1);
        parkingLot.unPark(bike1);

        parkingLot.getNoOfOpenSlots("car");
        parkingLot.getNoOfOpenSlots("bike");
        parkingLot.getNoOfOpenSlots("truck");
    }
}
