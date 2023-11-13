package com.test.service;

import com.test.DAO.PostOfficeDAO;
import com.test.entity.PostOffice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PostOfficeServiceTest {
    @InjectMocks
    PostOfficeService postOfficeService;
    @Mock
    PostOfficeDAO postOfficeDAO;

    @Test
    void getPostOffice() {
        PostOffice postOffice = new PostOffice(3, "dhl", "Kursk");
        Mockito.when(postOfficeDAO.getPostOffice(3)).thenReturn(postOffice);
        Assertions.assertEquals(postOffice, postOfficeService.getPostOffice(3));
    }
}