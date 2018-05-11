package Game.Entities;

import Game.Attack;
import Game.Items.Item;

public abstract class Entity implements Attack {

  String name;
  int maxHP;
  int currentHP;
  int damage;
  int gold;
  int xp;


}
