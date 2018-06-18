package com.webdev.siteparser.repository;


import com.webdev.siteparser.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

    @Query("from Page p where p.url = :url")
    List<Page> findPageByUrl(@Param("url") String url);

    @Query(nativeQuery = true, value = "select * FROM page WHERE project_id = :projectId AND url = :url")
    Page getPagesByProjectId(@Param("projectId") long projectId, @Param("url") String url);

    @Query(nativeQuery = true, value = "select * FROM page WHERE project_id = :projectId")
    List<Page> getPagesByProjectId(@Param("projectId") long projectId);
}
