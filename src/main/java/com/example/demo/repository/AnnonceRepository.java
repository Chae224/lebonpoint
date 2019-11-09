package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Annonce;

public interface AnnonceRepository extends JpaRepository<Annonce, Integer> {
	
	Page<Annonce> findAllByActiveTrueAndModerationTrueAndTitleContainsOrContentContains(String title, String content, Pageable page);

	List<Annonce>findAllByModerationFalse();
	
	Page<Annonce> findAllByActiveTrueAndModerationTrue(Pageable pageWithFiveElements);

	Page<Annonce> findAllByActiveTrueAndModerationTrueAndTitleContains(String title, Pageable page);

	Page<Annonce> findAllByActiveTrueAndModerationTrueAndContentContains(String content, Pageable page);

}
