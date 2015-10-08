package com.neilconcepts.battlespace.domain

import argonaut.Argonaut._
import argonaut.CodecJson
import com.neilconcepts.battlespace.domain.Messages.RegCreated
import com.neilconcepts.battlespace.domain.bst.{ GameState, Player }

/**
 * Messages ::
 * Messages that are used throughout the BattleSpace game
 */
object Messages extends MessageCodecs {
  sealed trait Message

  sealed trait RegMessage extends Message
  case object RegUpdated extends RegMessage
  case class RegCreated(player: Player) extends RegMessage
  case object RegRemoved extends RegMessage
  case class RegFound(player: Player) extends RegMessage

  sealed trait GameStateMessage extends Message
  case class GameStateRetrieved(gameState: GameState) extends GameStateMessage
  case object GameStateSaved extends GameStateMessage

  type ErrorMsg = String
  sealed trait Error
  sealed trait RegError extends Error { val msg: ErrorMsg }
  case class RegFailed(msg: ErrorMsg) extends RegError

  sealed trait GameStateError extends Error { val msg: ErrorMsg }
  case class GameStateSaveFailed(msg: ErrorMsg) extends GameStateError
  case class GameStateRetrievalFailed(msg: ErrorMsg) extends GameStateError
}

trait MessageCodecs {
  implicit val regCreatedCodec: CodecJson[RegCreated] =
    casecodec1(RegCreated.apply, RegCreated.unapply)("player")

}
