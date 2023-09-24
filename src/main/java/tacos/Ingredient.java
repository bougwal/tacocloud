package tacos;

import lombok.Data;

@Data
public class Ingredient {
    
    private final String id; 
    private final String name; 
    private Type type; 

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
