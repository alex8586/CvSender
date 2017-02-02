package com.alex.service;

import com.alex.domain.SendingEmailHistory;
import com.alex.repositories.SendingEmailHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendingEmailHistoryServiceImpl implements SendingEmailHistoryService {

    @Autowired
    private SendingEmailHistoryRepository historyRepository;

    @Override
    public SendingEmailHistory create(SendingEmailHistory sendingEmailHistory) {
        if(sendingEmailHistory != null){
            sendingEmailHistory = historyRepository.saveAndFlush(sendingEmailHistory);
        }
        return sendingEmailHistory;
    }

    @Override
    public void update(SendingEmailHistory sendingEmailHistory) {
        if(sendingEmailHistory != null){
            historyRepository.saveAndFlush(sendingEmailHistory);
        }
    }

    @Override
    public void delete(long id) {
        historyRepository.delete(id);
    }

    @Override
    public List<SendingEmailHistory> getByCompanyId(long companyId) {
        return historyRepository.getAllByCompanyId(companyId);
    }
}
