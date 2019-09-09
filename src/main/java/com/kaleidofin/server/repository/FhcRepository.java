package com.kaleidofin.server.repository;

import com.kaleidofin.server.model.Fhc;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface FhcRepository extends JpaRepository<Fhc, Long> {
	@Async
    @Query(value = "SELECT response_payload,DATE_FORMAT(created_at ,'%M %d, %Y') FROM fhc_transaction", nativeQuery = true)
	List<Object[]> findBySessionId(@Param("SessionId") String SessionId);

}
