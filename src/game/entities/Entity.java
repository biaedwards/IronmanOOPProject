package game.entities;

import game.Attack;

abstract class Entity implements Attack {

  String name;
  int maxHp;
  int currentHp;
  int damage;
  int gold;
  int xp;

}
