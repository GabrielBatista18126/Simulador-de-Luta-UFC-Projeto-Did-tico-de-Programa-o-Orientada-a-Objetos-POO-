package com.gabrielbatista.ultraemoji.repository;

import com.gabrielbatista.ultraemoji.domain.Lutador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LutadorRepository extends JpaRepository<Lutador, Long> {
}
