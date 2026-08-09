import java.util.Scanner;

// Main vehicle interface
interface Vehicle {
    String getMake();
    String getModel();
    int getYear();
}

// Car-specific interface
interface CarVehicle {
    void setNumberOfDoors(int numberOfDoors);
    int getNumberOfDoors();

    void setFuelType(String fuelType);
    String getFuelType();
}

// Motorcycle-specific interface
interface MotorVehicle {
    void setNumberOfWheels(int numberOfWheels);
    int getNumberOfWheels();

    void setMotorcycleType(String motorcycleType);
    String getMotorcycleType();
}

// Truck-specific interface
interface TruckVehicle {
    void setCargoCapacity(double cargoCapacity);
    double getCargoCapacity();

    void setTransmissionType(String transmissionType);
    String getTransmissionType();
}

// Car class implements Vehicle and CarVehicle
class Car implements Vehicle, CarVehicle {
    private String make;
    private String model;
    private int year;
    private int numberOfDoors;
    private String fuelType;

    public Car(String make, String model, int year, int numberOfDoors, String fuelType) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void displayDetails() {
        System.out.println("\n===== Car Details =====");
        System.out.println("Make: " + getMake());
        System.out.println("Model: " + getModel());
        System.out.println("Year: " + getYear());
        System.out.println("Number of Doors: " + getNumberOfDoors());
        System.out.println("Fuel Type: " + getFuelType());
    }
}

// Motorcycle class implements Vehicle and MotorVehicle
class Motorcycle implements Vehicle, MotorVehicle {
    private String make;
    private String model;
    private int year;
    private int numberOfWheels;
    private String motorcycleType;

    public Motorcycle(String make, String model, int year, int numberOfWheels, String motorcycleType) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.numberOfWheels = numberOfWheels;
        this.motorcycleType = motorcycleType;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public void setNumberOfWheels(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public void setMotorcycleType(String motorcycleType) {
        this.motorcycleType = motorcycleType;
    }

    public String getMotorcycleType() {
        return motorcycleType;
    }

    public void displayDetails() {
        System.out.println("\n===== Motorcycle Details =====");
        System.out.println("Make: " + getMake());
        System.out.println("Model: " + getModel());
        System.out.println("Year: " + getYear());
        System.out.println("Number of Wheels: " + getNumberOfWheels());
        System.out.println("Motorcycle Type: " + getMotorcycleType());
    }
}

// Truck class implements Vehicle and TruckVehicle
class Truck implements Vehicle, TruckVehicle {
    private String make;
    private String model;
    private int year;
    private double cargoCapacity;
    private String transmissionType;

    public Truck(String make, String model, int year, double cargoCapacity, String transmissionType) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.cargoCapacity = cargoCapacity;
        this.transmissionType = transmissionType;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void displayDetails() {
        System.out.println("\n===== Truck Details =====");
        System.out.println("Make: " + getMake());
        System.out.println("Model: " + getModel());
        System.out.println("Year: " + getYear());
        System.out.println("Cargo Capacity: " + getCargoCapacity() + " tons");
        System.out.println("Transmission Type: " + getTransmissionType());
    }
}

// Main class
public class VehicleInformationSystem {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== Vehicle Information System =====");
            System.out.println("1. Create Car");
            System.out.println("2. Create Motorcycle");
            System.out.println("3. Create Truck");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            if (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number from 1 to 4.");
                input.nextLine();
                continue;
            }

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    createCar();
                    break;
                case 2:
                    createMotorcycle();
                    break;
                case 3:
                    createTruck();
                    break;
                case 4:
                    System.out.println("Exiting program. Thank you.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void createCar() {
        System.out.print("Enter car make: ");
        String make = input.nextLine();

        System.out.print("Enter car model: ");
        String model = input.nextLine();

        int year = readPositiveInteger("Enter year of manufacture: ");
        int doors = readPositiveInteger("Enter number of doors: ");

        System.out.print("Enter fuel type (petrol, diesel, or electric): ");
        String fuelType = input.nextLine();

        Car car = new Car(make, model, year, doors, fuelType);
        car.displayDetails();
    }

    private static void createMotorcycle() {
        System.out.print("Enter motorcycle make: ");
        String make = input.nextLine();

        System.out.print("Enter motorcycle model: ");
        String model = input.nextLine();

        int year = readPositiveInteger("Enter year of manufacture: ");
        int wheels = readPositiveInteger("Enter number of wheels: ");

        System.out.print("Enter motorcycle type (sport, cruiser, or off-road): ");
        String motorcycleType = input.nextLine();

        Motorcycle motorcycle = new Motorcycle(make, model, year, wheels, motorcycleType);
        motorcycle.displayDetails();
    }

    private static void createTruck() {
        System.out.print("Enter truck make: ");
        String make = input.nextLine();

        System.out.print("Enter truck model: ");
        String model = input.nextLine();

        int year = readPositiveInteger("Enter year of manufacture: ");
        double cargoCapacity = readPositiveDouble("Enter cargo capacity in tons: ");

        System.out.print("Enter transmission type (manual or automatic): ");
        String transmissionType = input.nextLine();

        Truck truck = new Truck(make, model, year, cargoCapacity, transmissionType);
        truck.displayDetails();
    }

    private static int readPositiveInteger(String message) {
        while (true) {
            System.out.print(message);

            if (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                input.nextLine();
                continue;
            }

            int number = input.nextInt();
            input.nextLine();

            if (number <= 0) {
                System.out.println("Invalid input. Number must be greater than 0.");
                continue;
            }

            return number;
        }
    }

    private static double readPositiveDouble(String message) {
        while (true) {
            System.out.print(message);

            if (!input.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
                input.nextLine();
                continue;
            }

            double number = input.nextDouble();
            input.nextLine();

            if (number <= 0) {
                System.out.println("Invalid input. Number must be greater than 0.");
                continue;
            }

            return number;
        }
    }
}