import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameWorld {
    ArrayList<Land> lands = new ArrayList<>();
    int dimension;
    boolean inCombat = false;
    ArrayList <Entity> Combators = new ArrayList<>();
    GameWorld(int dimension) {
        this.dimension = dimension;
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                lands.add(new Land(j,i));
            }
        }
        addRandomGoblin();
    }

    public void addRandomGoblin() {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(dimension);
            y = random.nextInt(dimension);
        } while (lands.get(y * dimension + x).getEntity());
            Goblin goblin = new Goblin(x, y);
            setEntity(goblin);
    }
    public void setEntity(Entity entity){
        int x = entity.x;
        int y = entity.y;
        lands.get(y * dimension + x).setEntity(entity);
    }
    public void removeEntity(Entity entity){
        int x = entity.x;
        int y = entity.y;
        lands.get(y * dimension + x).removeEntity();
    }
    public void moveEntity(Entity entity, String direction){
        int currentX = entity.x;
        int currentY = entity.y;
        int nextX = currentX;
        int nextY = currentY;
        switch (direction.toLowerCase()) {
            case "w":
                nextY -= 1;
                break;
            case "a":
                nextX -= 1;
                break;
            case "s":
                nextY += 1;
                break;
            case "d":
                nextX += 1;
                break;
        }
        if (collision(dimension, nextX, nextY)){
            System.out.println("Reached the world border");
            return;
        }
        Land nextLand = lands.get(nextY * dimension + nextX);
        if (nextLand.getEntity() && nextLand.occupyingObject instanceof Goblin) {
            inCombat = true;
            System.out.println("Entered combat with a goblin!");
            Combators.add(entity);
            Combators.add(nextLand.occupyingObject);
            return;
        }

        removeEntity(entity);
        entity.move(direction);
        setEntity(entity);
        }
    public boolean isInCombat() {
        return inCombat;
    }
    public void Combat(Entity entity) {
        if (entity instanceof Human) {
            System.out.println("You are in combat with a goblin!");
            System.out.println("You have 3 options: attack, defend, or flee");
            Scanner scanner = new Scanner(System.in);
            System.out.println("What would you like to do?");
            String input = scanner.nextLine();
            switch (input.toLowerCase()) {
                case "attack":
                    entity.attack(Combators.get(1));
                    if (Combators.get(1).health <= 0) {
                        System.out.println("You have slain the goblin!");
                        removeEntity(Combators.get(1));
                        inCombat = false;
                        break;
                    } else {
                        Combators.get(1).attack(entity);
                        if (entity.health <= 0) {
                            System.out.println("You have been slain by the goblin!");
                            inCombat = false;
                            break;
                        }
                    }
                    break;
                case "defend":
                    System.out.println("You defend against the goblin!");
                    break;
                case "flee":
                    System.out.println("You flee from the goblin!");
                    inCombat = false;
                    Combators.remove(1);
                    Combators.remove(0);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    public boolean collision(int dimension, int x, int y) {
        return x < 0 || x >= dimension || y < 0 || y >= dimension;

    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                ret.append(lands.get(i * dimension + j));
            }
            ret.append("\n");
        }
        return ret.toString();
    }
}
