package com.webdev.siteparser.repository;

import com.webdev.siteparser.domain.Page;
import com.webdev.siteparser.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
