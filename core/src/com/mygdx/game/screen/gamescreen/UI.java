package com.mygdx.game.screen.gamescreen;

import com.mygdx.game.Main;
import com.mygdx.game.entity.player.Player;
import com.mygdx.game.entity.player.inventory.Inventory;

public class UI {

  Main main;
  Player player;

  public UI(Main m, Player player) {
    this.main = m;
    this.player = player;
  }

  public void drawInventory(Inventory inventory) {}
}
