package com.nimsoc.ai;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AIController {

    private final ChatService chatService;
    private final ImageService  imageService;
    private final RecipeService recipeService;

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }

    @GetMapping("ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt) {
        return chatService.getResponseOptions(prompt);
    }

    @GetMapping("generate-image")
    public List<String> generateImage(
            @RequestParam String prompt,
            @RequestParam(value="quality", defaultValue = "hd") String quality,
            @RequestParam(value="width", defaultValue = "512") int width,
            @RequestParam(value="height", defaultValue = "512") int height,
            @RequestParam(value="n", defaultValue = "1") int n
    ) {
        ImageResponse imageResponse = imageService.generateImage(
                prompt,
                quality,
                width,
                height,
                n
        );

        return imageResponse.getResults().stream().
                map(result -> result.getOutput().getUrl()).
                toList();
    }

    @GetMapping("create-recipe")
    public String createRecipe(
            @RequestParam String ingredients,
            @RequestParam(defaultValue = "any") String cuisine,
            @RequestParam(defaultValue = "") String dietaryRestrictions
    ) {
        return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);
    }

}
