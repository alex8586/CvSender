package com.alex.service;

import com.alex.domain.SendingEmailHistory;

import java.util.List;

public interface SendingEmailHistoryService {

    SendingEmailHistory create(SendingEmailHistory sendingEmailHistory);

    void update(SendingEmailHistory sendingEmailHistory);

    void delete(long id);

    List<SendingEmailHistory> getByCompanyId(long companyId);
}
