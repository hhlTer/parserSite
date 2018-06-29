package com.webdev.siteparser.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "domain")
    private String domain;

    @Column(name = "parsingEnabled")
    private boolean parsingEnabled;

    //delete all pages if delete project(EAGER)
    @JsonIgnore
    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Page> pages = new HashSet<>();

    public void setPages(Set<Page> pages) {
        this.pages = pages;
    }

    public void setParsingEnabled(boolean parsingEnabled) {
        this.parsingEnabled = parsingEnabled;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Set<Page> getPages() { return pages; }

    public boolean getParseEnabled(){ return parsingEnabled; }

    public String getDomain() { return domain; }

    public long getId() { return id; }
}
