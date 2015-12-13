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
trait Ships {
  import GameActions._
  import GameObjects._

  val spaceShips = Vector(
    Scout,
    SpaceTruck,
    SpaceWinnebago,
    SpaceGecko,
    SpaceBus,
    Thargoid
  )

  case object Scout extends GameObject {
    override val num = 1
    val size = 2
    val birth = None
    val life = None
    val death = None
  }

  case object SpaceTruck extends GameObject {
    override val num = 3
    val size = 3
    val birth = None
    val life = None
    val death = None
  }

  case object SpaceWinnebago extends GameObject {
    override val num = 3
    val size = 3
    val birth = None
    val life = None
    val death = None
  }

  case object SpaceGecko extends GameObject {
    override val num = 2
    val size = 5
    val birth = None
    val life = None
    val death = None
  }

  case object SpaceBus extends GameObject {
    override val num = 3
    val size = 7
    val birth = None
    val life = None
    val death = None
  }

  case object Thargoid extends GameObject {
    override val num = 1
    val size = 8
    val birth = None
    val life = None
    val death = None
  }
}
