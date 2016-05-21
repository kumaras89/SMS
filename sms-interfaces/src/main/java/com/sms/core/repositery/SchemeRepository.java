package com.sms.core.repositery;

import com.sms.core.scheme.Scheme;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SchemeRepository extends JpaRepository<Scheme, Long> {

    Scheme findByCodeIgnoreCase(final String code);
}
