package tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;

@SpringBootApplication   // <1>
public class TacoCloudApplication {

  public static void main(String[] args) {
    SpringApplication.run(TacoCloudApplication.class, args); // <2>
  }

  @Bean
  public CommandLineRunner dataLoader(IngredientRepository ingredientRepository){
    return args -> {
      ingredientRepository.deleteAll(); 
      ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
      ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
      ingredientRepository.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
      ingredientRepository.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
      ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
      ingredientRepository.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
      ingredientRepository.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
      ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
      ingredientRepository.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
      ingredientRepository.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
    };
  }

}
