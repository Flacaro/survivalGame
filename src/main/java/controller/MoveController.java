package controller;

import model.domain.GameDomain;
import services.PlayerService;

public class MoveController {

    public boolean move(int move, GameDomain game){
        PlayerService playerServices= new PlayerService();
        return playerServices.move(move,game);
}}
