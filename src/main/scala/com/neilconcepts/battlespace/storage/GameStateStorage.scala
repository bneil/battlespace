package com.neilconcepts.battlespace.storage

import com.neilconcepts.battlespace.domain.Messages.{ GameStateError, GameStateMessage }
import com.neilconcepts.battlespace.domain.bst.{ GameID, GameState => GS }
import com.twitter.util.Future

/**
 * GameState ::
 * A way to save and retrieve gamestate since http is stateless
 */
trait GameStateStorage {
  type GameStateResponse = Future[Either[GameStateMessage, GameStateError]]

  def retrieveGameState(gameID: GameID): GameStateResponse
  def saveGameState(gameState: GS): GameStateResponse
}