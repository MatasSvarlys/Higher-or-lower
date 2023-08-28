package com.accenture.higherorlower;

import com.accenture.higherorlower.model.Celebrity;
import com.accenture.higherorlower.model.Game;
import com.accenture.higherorlower.repository.CelebrityRepository;
import com.accenture.higherorlower.repository.PlayerRepository;
import com.accenture.higherorlower.service.impl.GameServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class GameServiceImplTest2 {

    @Mock
    private CelebrityRepository celebrityRepository;

    @Mock
    private PlayerRepository playerRepository;

    private GameServiceImpl gameService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        gameService = new GameServiceImpl(celebrityRepository, playerRepository);
        gameService.createGame();
        // Optionally, you can set up any additional dependencies or configuration here
    }

    @Test
    public void testGetAllLT() {
        // Create mock celebrities
        Celebrity celebrity1 = new Celebrity("Test Celebrity LT 1", "LT", 100);
        Celebrity celebrity2 = new Celebrity("Test Celebrity LT 2", "LT", 150);

        // Configure mock repository behavior
        when(celebrityRepository.findCelebrityByCountry("LT")).thenReturn(List.of(celebrity1, celebrity2));

        // Call the method you want to test
        List<Celebrity> ltCelebrities = gameService.getAllLT();

        // Assertions
        assertEquals(2, ltCelebrities.size());
        // You can add more specific assertions based on the test data you created

        // Verify that the repository method was called
        verify(celebrityRepository, times(1)).findCelebrityByCountry("LT");
    }

    @Test
    public void testGetAllLV() {
        // Create mock celebrity
        Celebrity celebrity1 = new Celebrity("Test Celebrity LV 1", "LV", 200);

        // Configure mock repository behavior
        when(celebrityRepository.findCelebrityByCountry("LV")).thenReturn(List.of(celebrity1));

        // Call the method you want to test
        List<Celebrity> lvCelebrities = gameService.getAllLV();

        // Assertions
        assertEquals(1, lvCelebrities.size());
        // You can add more specific assertions based on the test data you created

        // Verify that the repository method was called
        verify(celebrityRepository, times(1)).findCelebrityByCountry("LV");
    }
    @Test
    public void testGetRandomLTCelebrity() {
        // Create mock Game and CelebrityRepository
        Game mockGame = mock(Game.class);
        CelebrityRepository mockCelebrityRepository = mock(CelebrityRepository.class);

        // Configure mockGame behavior
        when(mockGame.getUsedIdsLT()).thenReturn(new ArrayList<>()); // No used ids initially

        // Configure mockCelebrityRepository behavior
        when(mockCelebrityRepository.findCelebrityById(anyInt())).thenReturn(new Celebrity("Test Celebrity", "LT", 100));

        // Set up the game service with the mocks
        GameServiceImpl gameService = new GameServiceImpl(mockCelebrityRepository, playerRepository);
        gameService.setGame(mockGame); // Set the mock game

        // Call the method you want to test
        Celebrity result = gameService.getRandomLTCelebrity();

        // Assertions
        assertNotNull(result);
        // You can add more specific assertions based on the test data you created

        // Verify that the necessary methods were called
        verify(mockGame, times(1)).getUsedIdsLT();
        verify(mockCelebrityRepository, times(1)).findCelebrityById(anyInt());
    }

    @Test
    public void testGetRandomLVCelebrity() {
        // Create mock Game and CelebrityRepository
        Game mockGame = mock(Game.class);
        CelebrityRepository mockCelebrityRepository = mock(CelebrityRepository.class);

        // Configure mockGame behavior
        when(mockGame.getUsedIdsLV()).thenReturn(new ArrayList<>()); // No used ids initially

        // Configure mockCelebrityRepository behavior
        when(mockCelebrityRepository.findCelebrityById(anyInt())).thenReturn(new Celebrity("Test Celebrity", "LV", 100));

        // Set up the game service with the mocks
        GameServiceImpl gameService = new GameServiceImpl(mockCelebrityRepository, playerRepository);
        gameService.setGame(mockGame); // Set the mock game

        // Call the method you want to test
        Celebrity result = gameService.getRandomLVCelebrity();

        // Assertions
        assertNotNull(result);
        // You can add more specific assertions based on the test data you created

        // Verify that the necessary methods were called
        verify(mockGame, times(1)).getUsedIdsLV();
        verify(mockCelebrityRepository, times(1)).findCelebrityById(anyInt());
    }

}
