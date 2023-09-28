package com.quadrinhos.catalog.model.MarvelComics;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.quadrinhos.catalog.model.Comics;
import com.quadrinhos.catalog.model.Publisher;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.OffsetDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelComicsResults extends Comics {
    private String title;
    private List<Datas> dates;
    private Creators creators;
    private Object thumbnail;

    public MarvelComicsResults() {
        setPublisher(Publisher.MARVEL.name());
    }

    public Object getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Object thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Datas> getDates() {
        return dates;
    }

    public void setDates(List<Datas> dates) {
        this.dates = dates;
    }

    public Creators getCreators() {
        return creators;
    }

    public void setCreators(Creators creators) {
        this.creators = creators;
    }

    private static class Creators {
        private List<Items> items;
        public List<Items> getItems() {
            return items;
        }

        public void setItems(List<Items> items) {
            this.items = items;
        }
    }

    private static class Datas {
        private String type;
        private OffsetDateTime date;
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public OffsetDateTime getDate() {
            return date;
        }

        public void setDate(OffsetDateTime date) {
            this.date = date;
        }
    }
    private static class Items {
        private String name;
        private String role;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }



}
