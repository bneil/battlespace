package com.neilconcepts.battlespace.domain

import java.util.UUID

object bst {
  type PlayerID = UUID
  type Email = String

  case class Player(id: PlayerID) {
    override def toString() = id.toString
  }
  case class Registration(email: Email)
}

object board {
  type X = Int
  type Y = Int
  type Z = Int
  type GameBoard = (X, Y, Z)

  case class BattleSpaceBoard(gb: GameBoard)
}

object gameObjects {
  sealed abstract class GameObjects
  case object WormHole extends GameObjects
  case object FuelPlanet extends GameObjects
  case object MegaSun extends GameObjects
}
