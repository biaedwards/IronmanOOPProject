package game.entities;

import game.Attack;

abstract class Entity implements Attack {

  String name;
  int maxHP;
  int currentHP;
  int damage;
  int gold;
  int xp;

}
