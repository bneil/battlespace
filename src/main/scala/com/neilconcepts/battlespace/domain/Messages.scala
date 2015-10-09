package com.neilconcepts.battlespace.domain

import com.neilconcepts.battlespace.domain.bst.{ GameId, GameState, Player }

/**
 * Messages ::
 * Messages that are used throughout the BattleSpace game
 */
object Messages {
  sealed trait Message

  sealed trait RegMessage extends Message
  case object RegUpdated extends RegMessage
  case class RegCreated(player: String, gameId: String) extends RegMessage
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

