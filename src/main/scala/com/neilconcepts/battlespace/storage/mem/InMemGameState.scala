package com.neilconcepts.battlespace.storage.mem

import com.neilconcepts.battlespace.domain.Messages.{ GameStateSaved, GameStateRetrievalFailed, GameStateRetrieved }
import com.neilconcepts.battlespace.domain.bst.{ GameID, GameState }
import com.neilconcepts.battlespace.storage.{ GameStateStorage => GameStateStorage }

import scala.collection.mutable
import com.twitter.util.{ Future }

import scala.util.{ Failure, Success, Try }

/**
 * InMemGameState ::
 * The implementation of the in-mem game state
 */
class InMemGameState extends GameStateStorage {
  private[this] val _gameState = mutable.Map.empty[GameID, GameState]

  override def retrieveGameState(gameID: GameID): GameStateResponse = Future(
    _gameState.get(gameID) match {
      case Some(gameState) =>
        Left(GameStateRetrieved(gameState))
      case None =>
        Right(GameStateRetrievalFailed("id not found"))
    }
  )

  override def saveGameState(gameState: GameState): GameStateResponse = Future(
    _gameState.synchronized {
      Try {
        _gameState(gameState.gameID) = gameState
      } match {
        case Success(_) =>
          Left(GameStateSaved)
        case Failure(ex) =>
          Right(GameStateRetrievalFailed("couldnt save game state"))
      }
    }
  )
}
