package com.quadrinhos.catalog.service.serviceimpl;

import com.quadrinhos.catalog.model.Comics;
import com.quadrinhos.catalog.model.DCComics.DCComicsResults;
import com.quadrinhos.catalog.model.MarvelComics.MarvelComicsResults;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class ComicsValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        if(MarvelComicsResults.class.equals(clazz) || DCComicsResults.class.equals(clazz) || Comics.class.equals(clazz)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void validate(Object target, Errors errors) {
        //System.out.println(target.getClass());
        if(target instanceof DCComicsResults){
            DCComicsResults comic = (DCComicsResults) target;
            if(comic.getId() == null || comic.getId() < 1){
                errors.rejectValue("Id", "invalidId", "Id cannot be less than 1 or null");
            }
            if(comic.getNameFromVolume() == null || comic.getNameFromVolume().isBlank()){
                errors.rejectValue("Title", "Title is invalid", "Title cannot be null or blank");
            }
            if (comic.getIssueNumber() == null && comic.getIssueNumber() < 1) {
                errors.rejectValue("issueNumber", "invalidIssueNumber", "Issue number cannot be less than 1");
            }
        } else if (target instanceof MarvelComicsResults) {
            MarvelComicsResults comic = (MarvelComicsResults) target;
            if (comic.getIssueNumber() == null || comic.getIssueNumber() < 1) {
                errors.rejectValue("issueNumber", "invalidIssueNumber", "Issue number cannot be less than 1");
            }
            if(comic.getId() == null || comic.getId() < 1){
                errors.rejectValue("Id", "invalidId", "Id cannot be less than 1 or null");
            }
            if(comic.getTitle() == null || comic.getTitle().isBlank()){
                errors.rejectValue("Title", "Title is invalid", "Title cannot be null or blank");
            }
        }


    }
}
