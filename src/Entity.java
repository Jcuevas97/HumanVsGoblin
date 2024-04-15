import java.util.ArrayList;

public class Entity {
    int x,y;
    String status;
    int health = 100;

    Entity(int x, int y, String status){
        this.x = x;
        this.y = y;
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }

    public void move(String direction) {
        switch (direction) {
            case "w":
                this.y--;
                break;
            case "s":
                this.y++;
                break;
            case "a":
                this.x--;
                break;
            case "d":
                this.x++;
                break;
            default:
                break;
        }
    }

    public void attack(Entity entity) {
        double hitChance = Math.random(); // generates a random number between 0.0 (inclusive) and 1.0 (exclusive)
        if (hitChance < 0.8) { // 80% chance to hit
            int damage = (int) (Math.random() * 20) + 1; // generates a random number between 1 and 20
            entity.health -= damage;
            System.out.println("Hit! \n" + entity.getClass().getSimpleName() + " health: " + entity.health);
        } else {
            System.out.println("Missed!");
        }
    }

}
