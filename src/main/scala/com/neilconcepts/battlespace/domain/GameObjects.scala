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

  type NumPoints = Int

  sealed abstract class GameObject{
    val size: (NumPoints, Direction)
    val birth : Option[GameAction]
    val life : Option[GameAction]
    val death: Option[GameAction]
  }

  case object WormHole extends GameObject{
    val size = (1, (1,1))
    val birth = Takes(3)
    val life = None  // =  Takes(3)spaces
    val death = None //Explode(3)spaces
  }

  case object FuelPlanet extends GameObject{
    val size = (1,(1,1))
    val birth = None
    val life = None
    val death = None
  }

  case object MegaSun extends GameObject{
    val size = (1,(1,1))
    val birth = None
    val life = None
    val death = None
  }

  case object Probe extends GameObject{
    val size = (1,(1,1))
    val birth = None
    val life = Illuminate(5)
    val death = None
  }

  //space ships
  case object TheDude extends GameObject{
    val size = (1,(1,1))
    val birth = None
    val life = None
    val death = None
  }

  case object SpaceTruck extends GameObject{
    val size = (3,(3,1))
    val birth = None
    val life = None
    val death = None
  }

  case object SpaceBus extends GameObject{
    val size = (7,(1,7))
    val birth = None
    val life = None
    val death = None
  }

}
