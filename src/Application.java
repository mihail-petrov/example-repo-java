import java.util.Random;
import java.util.Scanner;

public class Application {

    public static Scanner inputScanner      = new Scanner(System.in);
    public static Random randomGenerator    = new Random();
    public static boolean isRunning         = true;

    public static int availableHitCount     = 4;

    /**
     * @author Mihail Petrov
     * This process contains algorithum for robot movement
     */
    public static void processMove() {

        final int OBJECT_WALL   = 1;
        final int OBJECT_CHAIR  = 2;
        final int NO_OBJECT     = 3;
        final int PROCESS_EXIT  = 4;
        boolean isRunning       = true;

        while(isRunning) {

            System.out.println("Какво има на пътя ми ? ");
            System.out.println("1. Стена ");
            System.out.println("2. Стол ");
            System.out.println("3. Чисто е ");
            System.out.println("4. Изход от процедурата ");

            int objectId = inputScanner.nextInt();

            switch (objectId) {

                case OBJECT_WALL    -> System.out.println("Go Sideway");
                case OBJECT_CHAIR   -> System.out.println("Jump");
                case NO_OBJECT      -> System.out.println("Go Forward");
                case PROCESS_EXIT   -> isRunning = false;
                default             -> System.out.println("*** Repeat ***");
            }
        }

        System.out.println("Робота спря да се движи");
    }

    public static boolean isTargetLocked() {

        int randomNumber        = randomGenerator.nextInt(5000);
        return (randomNumber % 2 == 0);
    }

    public static boolean isBatteryCharged() {
        return (availableHitCount != 0);
    }

    public static boolean isBatteryEmpty() {
        return !isBatteryCharged();
    }

    public static boolean isHitSafe() {

        int targetChanceId = (randomGenerator.nextInt(10) + 1);
        return (targetChanceId != 5);
    }

    public static boolean isHitProcessable() {

        boolean isTargetLocked      = isTargetLocked();
        boolean isBatteryCharged    = isBatteryCharged();
        boolean isHitSafe           = isHitSafe();

        return  isTargetLocked   &&
                isBatteryCharged &&
                isHitSafe;
    }

    public static void processFight() {

        if(isBatteryEmpty()) {
            processCharge();
        }

        if(isHitProcessable()) {
            System.out.println("Удара е нанесен успешно");
            availableHitCount--;
        }
    }

    public static void processCharge() {
        availableHitCount = 4;
    }

    public static void processExit() {
        isRunning = false;
    }

    public static void main(String[] args) {

        // Едноредов коментар
        /* многоредов коментар */

        final int PROCESS_MOVE  = 1;
        final int PROCESS_FIGHT = 2;
        final int PROCESS_EXIT  = 3;

        while (isRunning) {
            System.out.println("1. Движение");
            System.out.println("2. Бой");
            System.out.println("3. Изключване на робота");

            int processCode = inputScanner.nextInt();

            switch (processCode) {
                case PROCESS_MOVE   -> processMove();
                case PROCESS_FIGHT  -> processFight();
                case PROCESS_EXIT   -> processExit();
            }
        }

        System.out.println("Робота е изключен");
    }
}