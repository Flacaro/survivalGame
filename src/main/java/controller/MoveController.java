package controller;

import model.domain.GameDomain;
import services.PlayerServices;

public class MoveController {

    public boolean move(int move, GameDomain game){
        PlayerServices playerServices= new PlayerServices();
        return playerServices.move(move,game);
}}
