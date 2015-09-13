package com.neilconcepts.battlespace.domain

import java.util.UUID

import com.neilconcepts.battlespace.domain.GameActions.GameAction
import com.neilconcepts.battlespace.domain.GameObjects.GameObject

/**
 * bst ::
 * BattleSpace Types
 * When using circe im not liking how it doens't
 * convert the case class names into JSON, as such
 * I had to add an override to the toString.
 */
object bst {
  type PlayerID = UUID
  type Email = String

  case class Player(id: PlayerID) {
    override def toString() = id.toString
  }
  case class Registration(email: Email)
}

/**
 * board ::
 * The game board is made of a Matrix which
 * contains GameSpace areas, really nothing
 * more is known about space so it should be
 * left at that.
 */
object board {
  type X = Int
  type Y = Int
  type Z = Int
  type GameBoard = Matrix

  case class Point(x: X, y: Y, z: Z)
  case class GameSpace(gameObj: GameObject)
  case class Matrix(x:GameSpace, y: GameSpace, z: GameSpace)
  case class BattleSpaceBoard(gb: GameBoard)
}

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
  sealed abstract class GameObject{
    val birth : Option[GameAction]
    val life : Option[GameAction]
    val death: Option[GameAction]
  }

  case object WormHole extends GameObject{
    val birth = None // =  Takes(3)spaces
    val life = None  // =  Takes(3)spaces
    val death = None //Explode(3)spaces
  }

  case object FuelPlanet extends GameObject{
    val birth = None
    val life = None
    val death = None
  }

  case object MegaSun extends GameObject{
    val birth = None
    val life = None
    val death = None
  }

  //space ships
  case object TheDude extends GameObject{
    val birth = None
    val life = None
    val death = None
  }

}

object GameActions {
  sealed abstract class GameAction
}
