package com.neilconcepts.battlespace.domain

/**
 * GameObjects ::
 * All the things that take up space. And well
 * try to deter that space.
 * Other then that they each do thar own thing
 * when assigned to the gameboard.
 *
 * WormHole = Any projectile that enters
 */
object GameObjects {
  import GameActions._

  sealed trait Horizontal
  sealed trait Vertical
  type Direction = (Horizontal, Vertical)

  type Size = Int

  abstract class GameObject {
    val size: Size
    val birth: Option[GameAction]
    val life: Option[GameAction]
    val death: Option[GameAction]
  }

  case object WormHole extends GameObject {
    val size = 1
    val birth = Takes(3)
    val life = RandomTransport(3)
    val death = None //Immortal
  }

  case object FuelPlanet extends GameObject {
    val size = 1
    val birth = None
    val life = None
    val death = Explode(5)
  }

  case object MegaSun extends GameObject {
    val size = 1
    val birth = Illuminate(5)
    val life = None
    val death = None
  }

  case object Probe extends GameObject {
    val size = 1
    val birth = None
    val life = Illuminate(5)
    val death = None
  }

}
