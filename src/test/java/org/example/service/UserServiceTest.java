package org.example.service;

import org.example.dao.UserDao;
import org.example.model.User;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        User user = new User(1L, "Michael Jackson", "mjackson@example.com");
        userService.createUser(user);
        verify(userDao, times(1)).save(user);
    }

    @Test
    public void testGetUserById() {
        String userName = "Michael Jackson";
        Long userId = 1L;
        User user = new User(userId, userName, "mjackson@example.com");
        when(userDao.getById(userId)).thenReturn(user);

        User previousUser = userService.getUser(userId);
        System.out.println(previousUser + "the namecito " + previousUser.getName() );

        assertEquals(userName, previousUser.getName());
        verify(userDao, times(1)).getById(1L);
    }
}