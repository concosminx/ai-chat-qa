package com.nimsoc.ai;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeService {

  private final ChatModel chatModel;

  public String getResponse(String prompt) {
    return chatModel.call(prompt);
  }

  public String createRecipe(String ingredients, String cuisine, String dietaryRestrictions) {

    PromptTemplate promptTemplate = getPromptTemplate();
    Map<String, Object> params =
        Map.of(
            "ingredients", ingredients,
            "cuisine", cuisine,
            "dietaryRestrictions", dietaryRestrictions);
    Prompt prompt = promptTemplate.create(params);
    ChatResponse chatResponse = chatModel.call(prompt);
    return chatResponse.getResult().getOutput().getText();
  }

  private static PromptTemplate getPromptTemplate() {
    var template =
        """
            I want to create a recipe using the following ingredients: {ingredients}.
            The cuisine type i prefer is {cuisine}.
            Please consider the following dietary restrictions: {dietaryRestrictions}.
            Please provide me with a detailed recipe including title, list of ingredients, and cooking instructions.
            Please provide all the quantities in grams or kilograms.
        """;

      return new PromptTemplate(template);
  }
}
