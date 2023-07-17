package com.skillball.repository;

import com.skillball.entity.Vocab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocabRepository extends JpaRepository<Vocab, Integer> {
}