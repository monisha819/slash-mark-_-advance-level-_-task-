import java.util.Random;

public class ObstacleAvoidanceAI {

    static final int GRID_WIDTH = 5;
    static final int GRID_HEIGHT = 5;

    static int robotX = 2;
    static int robotY = 4;

    static boolean[][] grid = new boolean[GRID_HEIGHT][GRID_WIDTH];

    public static void generateObstacles() {
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            int x = rand.nextInt(GRID_WIDTH);
            int y = rand.nextInt(GRID_HEIGHT - 1); // Avoid robot's row
            grid[y][x] = true;
        }
    }

    public static void displayGrid() {
        System.out.println("\nGrid View:");
        for (int y = 0; y < GRID_HEIGHT; y++) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                if (robotX == x && robotY == y) {
                    System.out.print(" R ");
                } else if (grid[y][x]) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }

    public static String decideMove() {
        if (robotY > 0 && !grid[robotY - 1][robotX]) {
            robotY--;
            return "Move Forward";
        } else if (robotX > 0 && !grid[robotY][robotX - 1]) {
            robotX--;
            return "Turn Left";
        } else if (robotX < GRID_WIDTH - 1 && !grid[robotY][robotX + 1]) {
            robotX++;
            return "Turn Right";
        } else {
            return "Stop - Blocked";
        }
    }

    public static void main(String[] args) {
        generateObstacles();

        for (int step = 0; step < 10; step++) {
            displayGrid();
            String action = decideMove();
            System.out.println("AI Decision: " + action);

            try {
                Thread.sleep(1000); // Pause for readability
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (action.equals("Stop - Blocked")) break;
        }

        System.out.println("\nFinal Robot Position: (" + robotX + ", " + robotY + ")");
    }
}
