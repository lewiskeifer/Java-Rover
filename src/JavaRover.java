import java.util.*;
import java.util.Scanner;

public class JavaRover {

    static private Integer MAX_Y;
    static private Integer MAX_X;

    public class Coordinate {
        int x;
        int y;

        public Coordinate() {
            this.x = 0;
            this.y = 0;
        }
    }

    public enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    public enum Lateral {
        LEFT,
        RIGHT
    }

    public class Rover {

        private Direction direction;
        private Coordinate coordinate;


        public Rover() {
            this.direction = Direction.NORTH;
            this.coordinate = new Coordinate();
        }

        public void Rotate(Lateral lateral) {
            Integer currentDirection = this.direction.ordinal();
            if (lateral == Lateral.LEFT) {
                currentDirection--;
            }
            else if (lateral == Lateral.RIGHT) {
                currentDirection++;
            }
            currentDirection = (((this.direction % 4) + 4) % 4);;
            this.direction = Direction.values()[currentDirection];
        }

        public void Move(Integer distance) {
            int newY = this.coordinate.y;
            int newX = this.coordinate.x;
            switch (this.direction) {
                case NORTH:
                    newY += distance;
                    if (newY >= MAX_Y) {
                        return;
                    }
                    this.coordinate.y = newY;
                    break;
                case EAST:
                    newX += distance;
                    if (newX >= MAX_X) {
                        return;
                    }
                    this.coordinate.x = newX;
                    break;
                case SOUTH:
                    newY -= distance;
                    if (newY <= 0) {
                        return;
                    }
                    this.coordinate.y = newY;
                    break;
                case WEST:
                    newX -= distance;
                    if (newX <= 0) {
                        return;
                    }
                    this.coordinate.x = newX;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        JavaRover app = new JavaRover();
        app.run(args);
    }

    public void run(String[] args) {
        //TODO ensure user arguments are validated and legal
        MAX_Y = Integer.parseInt(args[0]);
        MAX_X = Integer.parseInt(args[1]);

        Rover rover = new Rover();

        Scanner scanner = new Scanner(System.in);
        String command = "";

        while (!command.equals("q")) {
            System.out.print("Enter your cmd (q to quit):");
            command = scanner.nextLine();
            switch (command) {
                case "m":
                    rover.Move(1);
                    break;
                case "r":
                    rover.Rotate(Lateral.RIGHT);
                    break;
            }
        }

        scanner.close();
    }

}