package com.nimsoc.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final OpenAiImageModel openAiImageModel;

    public ImageResponse generateImage(String prompt, String quality, int width, int height, int n) {

        ImageResponse imageResponse = openAiImageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .withModel("dall-e-2")
                                //.withQuality(quality)
                                .withN(n)
                                .withHeight(height)
                                .withWidth(width)
                                .build()
                        )
        );

        return imageResponse;
    }


}
