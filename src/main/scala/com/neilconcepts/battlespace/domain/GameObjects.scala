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

  sealed abstract class GameObject{
    val size: Size
    val birth : Option[GameAction]
    val life : Option[GameAction]
    val death: Option[GameAction]
  }

  case object WormHole extends GameObject{
    val size = 1
    val birth = Takes(3)
    val life = None  // =  Takes(3)spaces
    val death = None //Explode(3)spaces
  }

  case object FuelPlanet extends GameObject{
    val size = 1
    val birth = None
    val life = None
    val death = None
  }

  case object MegaSun extends GameObject{
    val size = 1
    val birth = None
    val life = None
    val death = None
  }

  case object Probe extends GameObject{
    val size = 1
    val birth = None
    val life = Illuminate(5)
    val death = None
  }



}

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

  case object Scout extends GameObject{
    val size = 2
    val birth = None
    val life = None
    val death = None
  }

  case object SpaceTruck extends GameObject{
    val size = 3
    val birth = None
    val life = None
    val death = None
  }

  case object SpaceWinnebago extends GameObject{
    val size = 3
    val birth = None
    val life = None
    val death = None
  }

  case object SpaceGecko extends GameObject{
    val size = 5
    val birth = None
    val life = None
    val death = None
  }

  case object SpaceBus extends GameObject{
    val size = 7
    val birth = None
    val life = None
    val death = None
  }

  case object Thargoid extends GameObject{
    val size = 8
    val birth = None
    val life = None
    val death = None
  }
}
