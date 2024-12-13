package src.common;

import lombok.Getter;
import lombok.Setter;
import src.order.Ingredient;

import java.util.List;

@Getter
@Setter
public class Data {
    private boolean success;
    private List<Ingredient> data;
}
