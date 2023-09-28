package com.quadrinhos.catalog.model;



import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.quadrinhos.catalog.service.serviceimpl.ObjectToLong;

public abstract class Comics {

    private Long id;
    private String publisher;
    private String description;
    //@JsonProperty("issue_number")
    @JsonAlias("issue_number")
    private Integer issueNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIssueNumber(){
        return issueNumber;
    }

    public void setIssueNumber(Object issue_number){
        //ObjectToLong issueNumber = new ObjectToLong();
//        System.out.println(publisher);
        //this.issueNumber = issueNumber.convert(issue_number);
        if(issue_number instanceof Integer){
            this.issueNumber = (Integer) issue_number;
        }
        ObjectToLong issue = new ObjectToLong();
        System.out.println(issue_number);
        this.issueNumber = issue.convert(issue_number);
    }



}