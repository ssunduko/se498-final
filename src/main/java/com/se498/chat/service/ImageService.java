package com.se498.chat.service;

import com.se498.chat.model.Image;
import com.se498.chat.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public Image addImage(Image image) {

        if(image.getImageId() == null){
            image.setImageId(UUID.randomUUID().toString());
        }

        return this.imageRepository.save(image);

    }

    public List<Image> getChatImages() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(this.imageRepository.findAll().iterator(), Spliterator.ORDERED), false)
                .collect(Collectors.toList());
    }

    public Image getImage(String id){
        return imageRepository.findById(id).get();
    }
}
