package com.hackathon.acceldata.repository;

import com.hackathon.acceldata.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

}
