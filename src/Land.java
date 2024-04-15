public class Land {

    int x,y;
    boolean isOccupied = false;
    Entity occupyingObject = null;


    public Land(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        if(isOccupied){
            return occupyingObject.toString();
        }
        return "\uD83C\uDF32";
    }

    public void setEntity(Entity entity) {
        this.isOccupied = true;
        this.occupyingObject = entity;
    }
    public void removeEntity() {
        this.isOccupied = false;
        this.occupyingObject = null;
    }

    public boolean getEntity() {
        return isOccupied;
    }
}
