package com.test.service;

import com.test.DAO.PostOfficeDAO;
import com.test.entity.PostOffice;
import org.springframework.stereotype.Service;

@Service
public class PostOfficeService {
    private final PostOfficeDAO postOfficeDAO;

    public PostOfficeService(PostOfficeDAO postOfficeDAO) {
        this.postOfficeDAO = postOfficeDAO;
    }

    public PostOffice getPostOffice(int postOfficeId) {
        return postOfficeDAO.getPostOffice(postOfficeId);
    }
}
