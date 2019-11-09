package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Annonce;
import com.example.demo.model.User;
import com.example.demo.repository.AnnonceRepository;
import com.example.demo.repository.UserRepository;

@Service
public class AnnonceService {

	@Autowired
	AnnonceRepository annonceRepository;
	
	@Autowired
	UserRepository userRepository;

	public void moderated(List<Integer> annonceModerated) {
		for (Integer entry : annonceModerated) {
			Optional<Annonce> annonce = annonceRepository.findById(entry);
			if(annonce.isPresent()) {
				annonce.get().setModeration(true);
				annonceRepository.save(annonce.get());
			}
		}
	}
	
	public Page<Annonce> allAnnonce(int page) {
		Pageable fiveAnnonce = PageRequest.of(page, 5);
		return annonceRepository.findAllByActiveTrueAndModerationTrue(fiveAnnonce);
	}
	
	public List<Annonce> findAllByModerationFalse() {
		return annonceRepository.findAllByModerationFalse();
	}

	public Optional<Annonce> oneAnnonce(int id) {
		return annonceRepository.findById(id);
	}

	public void save(Annonce annonce) {
		annonceRepository.save(annonce);
	}
	
	public User findUser(String username) {
		return userRepository.findByUsername(username);
	}
	
	public void remove(int id) {
		Optional<Annonce> modif = annonceRepository.findById(id);
		if(modif.isPresent()) {
			modif.get().setActive(false);
			annonceRepository.save(modif.get());
		}
	}
	
	public Page<Annonce> searchFilter(String title, String content, Pageable page) {
		Page<Annonce> result = null;
		if(!title.isEmpty() && !content.isEmpty()) {
			result = annonceRepository.findAllByActiveTrueAndModerationTrueAndTitleContainsOrContentContains(title, content, page);
		} else if(!title.isEmpty() && content.isEmpty()) {
			result = annonceRepository.findAllByActiveTrueAndModerationTrueAndTitleContains(title, page);
		}else if (title.isEmpty() && !content.isEmpty()) {
			result = annonceRepository.findAllByActiveTrueAndModerationTrueAndContentContains(content, page);
		}
		return result;
	}
}
