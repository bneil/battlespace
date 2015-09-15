package com.neilconcepts.battlespace.domain

import java.util.UUID

import com.neilconcepts.battlespace.domain.Board.BattleSpaceBoard
import com.neilconcepts.battlespace.domain.GameObjects.GameObject

/**
 * bst ::
 * BattleSpace Types
 * When using circe im not liking how it doens't
 * convert the case class names into JSON, as such
 * I had to add an override to the toString.
 */
object bst {
  type GameID = UUID
  type PlayerID = UUID
  type Email = String

  case class Player(id: PlayerID) {
    override def toString = id.toString
  }
  case class Registration(email: Email)
  case class GameState(
    gameID: GameID,
    gameBoard: BattleSpaceBoard
  )
}

