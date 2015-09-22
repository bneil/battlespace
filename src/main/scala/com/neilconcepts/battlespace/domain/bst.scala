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
    gameId: GameId,
    gameBoard: BattleSpaceBoard
  )

  //  implicit val gameStateCodec: CodecJson[GameState] =
  //    casecodec2(GameState.apply, GameState.unapply)("gameId", "gameBoard")

  implicit val playerCodec: CodecJson[Player] =
    casecodec1(Player.apply, Player.unapply)("id")
  //implicit val playerIdCode: CodecJson[PlayerId] =
  //  casecodec1(PlayerId.apply, PlayerId.unapply)
  implicit val gameStateCodec: CodecJson[GameState] =
    casecodec2(GameState.apply, GameState.unapply)("gameId", "gameBoard")
}

