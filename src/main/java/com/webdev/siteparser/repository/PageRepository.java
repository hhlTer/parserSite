package com.webdev.siteparser.repository;


import com.webdev.siteparser.domain.Page;
import com.webdev.siteparser.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

//    @Query(nativeQuery = true, value = "")
    int countPagesByProjectId(long projectId);

    @Query(nativeQuery = true, value = "SELECT * FROM page WHERE project_id = :projectId LIMIT :countPages OFFSET :offset")
    List<Page> getPagesByOffset(@Param("projectId") long projectId,
                                @Param("offset") int offset,
                                @Param("countPages")int countPages);

    @Query("from Page p where p.url = :url")
    List<Page> findPageByUrl(@Param("url") String url);

    @Query(nativeQuery = true, value = "select * FROM page WHERE project_id = :projectId AND url = :url")
    Page getPagesByProjectId(@Param("projectId") long projectId, @Param("url") String url);

    @Query(nativeQuery = true, value = "select * FROM page WHERE project_id = :projectId")
    List<Page> getPagesByProjectId(@Param("projectId") long projectId);

    @Query(nativeQuery = true, value = "SELECT * FROM page WHERE project_id = :projectId AND content is NULL")
    List<Page> getUnparsedPagesByProjectId(@Param("projectId") long projectId);

    @Query(nativeQuery = true,
    value = "SELECT * FROM page WHERE project_id = :projectId AND url LIKE %:keyword%")
    Set<Page> findUrlByKeywords(@Param("projectId") long projectId,
                                 @Param("keyword")String keyword);

    @Query(nativeQuery = true,
    value = "SELECT * FROM page WHERE project_id = :projectId AND title LIKE %:keyword%")
    Set<Page> findTitleByKeywords(@Param("projectId") long projectId,
                                   @Param("keyword")String keyword);

    @Query(nativeQuery = true,
    value = "SELECT * FROM page WHERE project_id = :projectId AND description LIKE %:keyword%")
    Set<Page> findDescriptionByKeywords(@Param("projectId") long projectId,
                                        @Param("keyword")String keyword);

    @Query(nativeQuery = true,
    value = "SELECT * FROM page WHERE project_id = :projectId AND content LIKE %:keyword%")
    Set<Page> findContentByKeywords(@Param("projectId") long projectId,
                                     @Param("keyword")String keyword);


}
