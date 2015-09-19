package com.neilconcepts.battlespace.domain

import java.util.UUID

import argonaut.Argonaut._
import argonaut.CodecJson
import com.neilconcepts.battlespace.domain.Board.BattleSpaceBoard

/**
 * bst ::
 * BattleSpace Types
 * When using circe im not liking how it doens't
 * convert the case class names into JSON, as such
 * I had to add an override to the toString.
 */
object bst {
  type GameId = UUID
  type PlayerId = UUID
  type Email = String

  case class Player(id: PlayerId) {
    override def toString = id.toString
  }
  case class Registration(email: Email)
  case class GameState(
    gameID: GameId,
    gameBoard: BattleSpaceBoard
  )

  //  implicit val gameStateCodec: CodecJson[GameState] =
  //    casecodec2(GameState.apply, GameState.unapply)("gameId", "gameBoard")

}

