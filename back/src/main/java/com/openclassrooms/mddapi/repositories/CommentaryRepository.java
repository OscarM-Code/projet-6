package com.openclassrooms.mddapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.Commentary;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {

}
