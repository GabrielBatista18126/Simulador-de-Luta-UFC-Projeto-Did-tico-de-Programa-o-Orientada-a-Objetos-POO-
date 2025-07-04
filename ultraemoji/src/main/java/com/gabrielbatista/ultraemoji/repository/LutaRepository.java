package com.gabrielbatista.ultraemoji.repository;

import com.gabrielbatista.ultraemoji.domain.Luta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LutaRepository extends JpaRepository<Luta, Long> {
}
