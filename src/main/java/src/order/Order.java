package src.order;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class Order {
    private String[] ingredients;

    public Order(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }
}
