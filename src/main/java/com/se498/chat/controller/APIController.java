package com.se498.chat.controller;

import com.se498.chat.model.ChatMessage;
import com.se498.chat.service.ImageService;
import com.se498.chat.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.se498.chat.model.Image;

import java.util.Collection;

@RestController
@SecurityRequirement(name = "basicAuth")
public class APIController {

    @Autowired
    ImageService imageService;

    MessageService messageService;


    @Operation(summary = "Create Images", tags = {"images"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Image.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping("/image")
    public Image createImage(@RequestBody Image image) {
        return imageService.addImage(image);
    }

    @Operation(summary = "Retrieve All Images", tags = {"images"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ChatMessage.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "There are no images", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/images")
    public Collection<Image> getImages() {
        return imageService.getChatImages();
    }

    @Operation(summary = "Retrieve Image By Id", tags = {"images"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Image.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "There is no images with such id", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/image/{id}")
    public Image getImage(@PathVariable String id) {
        return imageService.getImage(id);
    }

    //TODO: Implement Create Message API
    public ChatMessage createMessage(@RequestBody ChatMessage chatMessage) {
        //TODO: Implement creating message through the service
        return null;
    }
    //TODO: Implement Get Message API
    public ChatMessage getMessage(@PathVariable String id) {
        //TODO: Implement getting message from the service
        return null;
    }

    //TODO: Implement Get Messages API
    public Collection<ChatMessage> getMessages() {
        //TODO: Implement getting all messages from the service
        return null;
    }

}
