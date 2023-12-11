package mancala;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Mancala data structure for the Mancala game.
 * Do not change the signature of any of the methods provided.
 * You may add methods if you need them.
 * Do not add game logic to this class
 */
public class MancalaDataStructure implements Serializable {
    private final int PLAYER_ONE = 6;
    private final int PLAYER_TWO = 13;
    private int START_STONES = 4;  // not final because we might want a different size board in the future

    private List<Countable> data = new ArrayList<>();
    private int iteratorPos = 0;
    private int playerSkip = PLAYER_TWO;
    private int pitSkip = -1; // will never match the iteratorPos unless set specifically

    /**
     * Constructor to initialize the MancalaDataStructure.
     *
     * @param startStones The number of stones to place in pits at the start of the game. Default values are 4.
     */
    public MancalaDataStructure(int startStones) {
        START_STONES = startStones;
        for (int i = 0; i < PLAYER_ONE; i++) {
            data.add(new Pit());
        }
        data.add(new Store());
        for (int i = PLAYER_ONE + 1; i < PLAYER_TWO; i++) {
            data.add(new Pit());
        }
        data.add(new Store());
    }

    /**
     * Constructor to initialize the MancalaDataStructure with default start stones.
     */
    public MancalaDataStructure() {
        this(4);
    }

    // Existing methods remain unchanged...

    /**
     * Empties both players' stores.
     */
    public void emptyStores() {
        for (int i = 0; i < 2; i++) {
            data.set(storePos(i + 1), new Store());
        }
    }

    /**
     * Sets up pits with a specified number of starting stones.
     *
     * @param startingStonesNum The number of starting stones for each pit.
     */
    public void setUpPits() {
        for (int i = 0; i < PLAYER_ONE; i++) {
            data.get(i).addStones(START_STONES);
        }

        for (int i = PLAYER_ONE + 1; i < PLAYER_TWO; i++) {
            data.get(i).addStones(START_STONES);
        }
    }

    /**
     * Adds a store that is already connected to a Player.
     *
     * @param store     The store to set.
     * @param playerNum The player number (1 or 2).
     */
    public void setStore(Countable store, int playerNum) {
        data.set(storePos(playerNum), store);
    }

    // Additional methods (assumed)
    // Please replace these with the actual methods you need or let me know the requirements.

    /**
     * Checks if a pit is empty.
     *
     * @param pitNum The number of the pit.
     * @return True if the pit is empty, false otherwise.
     */
    public boolean isPitEmpty(int pitNum) {
        Countable pit = data.get(pitPos(pitNum));
        return pit.getStoneCount() == 0;
    }

    /**
     * Gets the count of stones in the player's store.
     *
     * @param playerNum The player number (1 or 2).
     * @return The count of stones in the player's store.
     */
    public int getStoreStoneCount(int playerNum) {
        Countable store = data.get(storePos(playerNum));
        return store.getStoneCount();
    }
}
