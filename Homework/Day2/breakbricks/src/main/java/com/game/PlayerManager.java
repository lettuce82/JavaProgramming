package com.game;



import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private List<Player> players;
    private int currentPlayerIndex;

    public PlayerManager() {
        players = new ArrayList<>();
        currentPlayerIndex = 0; // 처음 시작 시 첫 번째 플레이어로 초기화
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void moveToNextPlayer() {
        currentPlayerIndex++; // 다음 플레이어로 인덱스 증가

        // 현재 인덱스가 플레이어 수를 넘어가면 처음 플레이어로 돌아감
        if (currentPlayerIndex >= players.size()) {
            currentPlayerIndex = 0;
        }
    }
}
