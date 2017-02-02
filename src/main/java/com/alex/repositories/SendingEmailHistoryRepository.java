package com.alex.repositories;

import com.alex.domain.SendingEmailHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SendingEmailHistoryRepository extends JpaRepository<SendingEmailHistory, Long> {

    @Query("select s from SendingEmailHistory  s where s.companyId = :company_id")
    List<SendingEmailHistory> getAllByCompanyId(long id);
}
