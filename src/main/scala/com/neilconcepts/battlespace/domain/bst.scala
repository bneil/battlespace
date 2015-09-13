package com.neilconcepts.battlespace.domain

import java.util.UUID

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
object Board {
  type X = Int
  type Y = Int
  type Z = Int
  type GameBoard = Matrix

  case class Point(x: X, y: Y, z: Z)
  case class GameSpace(gameObj: GameObject)
  case class Matrix(x:GameSpace, y: GameSpace, z: GameSpace)
  case class BattleSpaceBoard(gb: GameBoard)
}

