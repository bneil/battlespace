package com.neilconcepts.battlespace.domain

import com.neilconcepts.battlespace.domain.GameObjects.GameObject

/**
 * Space Ships --
 * --------------
 * Aircraft carrier	5
 * Battleship	4
 * Submarine	3
 * Destroyer (or Cruiser)	3
 * Patrol boat (or destroyer)	2
 */
private trait Ships {
  import GameActions._
  import GameObjects._

  val spaceShips = Vector(
    Scout,
    SpaceTruck,
    SpaceWinnebago,
    SpaceGecko,
    SpaceBus
  )

  case object Scout extends GameObject {
    val size = 2
    val birth = None
    val life = None
    val death = None
  }

  case object SpaceTruck extends GameObject {
    val size = 3
    val birth = None
    val life = None
    val death = None
  }

  case object SpaceWinnebago extends GameObject {
    val size = 3
    val birth = None
    val life = None
    val death = None
  }

  case object SpaceGecko extends GameObject {
    val size = 5
    val birth = None
    val life = None
    val death = None
  }

  case object SpaceBus extends GameObject {
    val size = 7
    val birth = None
    val life = None
    val death = None
  }

  case object Thargoid extends GameObject {
    val size = 8
    val birth = None
    val life = None
    val death = None
  }
}
