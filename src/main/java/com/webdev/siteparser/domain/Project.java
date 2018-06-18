package com.webdev.siteparser.domain;

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


    //delete all pages if delete project(EAGER)
    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Page> pages = new HashSet<>();

    public void setPages(Set<Page> pages) {
        this.pages = pages;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Set<Page> getPages() {

        return pages;
    }

    public String getDomain() {

        return domain;
    }

    public long getId() {

        return id;
    }
}
