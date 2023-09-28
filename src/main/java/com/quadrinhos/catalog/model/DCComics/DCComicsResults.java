package com.quadrinhos.catalog.model.DCComics;

import com.quadrinhos.catalog.model.Comics;
import com.quadrinhos.catalog.model.Publisher;
import com.quadrinhos.catalog.service.serviceimpl.ObjectToLong;

public class DCComicsResults extends Comics{
    private String name;
    private Volume volume;
    private Image image;

    public DCComicsResults() {
        setPublisher(Publisher.DC.name());
    }


    public String getName() {
        return name;
    }

    public String getNameFromVolume(){
        if(volume != null){
            return volume.getName();
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    private static class Volume{
        private Long id;
        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    private static class Image{
        private String medium_url;

        public String getMedium_url() {
            return medium_url;
        }

        public void setMedium_url(String medium_url) {
            this.medium_url = medium_url;
        }
    }


}
