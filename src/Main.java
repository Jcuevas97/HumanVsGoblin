import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameWorld gameWorld = new GameWorld(10);
        Human human = new Human(0, 0);
        gameWorld.setEntity(human);
        System.out.println(gameWorld);
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (true) {
            if (gameWorld.inCombat) {
                gameWorld.Combat(human);

            } else if (input.equals("exit")) {
                break;
            } else {
                System.out.println("Waiting for input:");
                input = scanner.nextLine();


                if (input.equalsIgnoreCase("w") || input.equalsIgnoreCase("a") ||
                        input.equalsIgnoreCase("s") || input.equalsIgnoreCase("d")) {
                    gameWorld.moveEntity(human, input);
                    System.out.println(gameWorld);
                }
            }
        }

        scanner.close();


    }
}
