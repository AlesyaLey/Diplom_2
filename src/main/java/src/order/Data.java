package src.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Data {
    private boolean success;
    private List<Ingredient> data;
}
