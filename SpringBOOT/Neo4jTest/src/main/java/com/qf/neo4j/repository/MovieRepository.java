package com.qf.neo4j.repository;

import com.qf.neo4j.nodeEntity.Movie;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * @ClassName: CompanyRepository
 * @Description:
 * @author: zhbo
 * @date: 2017/10/16 18:41
 * @Copyright: 2017 . All rights reserved.
 */
public interface MovieRepository extends GraphRepository<Movie> {


    @Query("MATCH (movie:movie {title:{title}}) RETURN movie")
    /*@Query("match(n:person) return n")*/
    Movie getPersonByName(@Param("title") String title);
}
