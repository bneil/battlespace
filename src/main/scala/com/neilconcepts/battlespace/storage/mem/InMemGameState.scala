package com.neilconcepts.battlespace.storage.mem

import com.neilconcepts.battlespace.domain.Board.BattleSpaceBoard
import com.neilconcepts.battlespace.domain.Messages.{ GameStateSaved, GameStateRetrievalFailed, GameStateRetrieved }
import com.neilconcepts.battlespace.domain.bst.{ GameId, GameState }
import com.neilconcepts.battlespace.storage.{ GameStateStorage => GameStateStorage }

import scala.collection.mutable
import com.twitter.util.Future

import scala.util.{ Failure, Success, Try }

/**
 * InMemGameState ::
 * The implementation of the in-mem game state
 */
class InMemGameState extends GameStateStorage {
  private[this] val _gameState = mutable.Map.empty[GameId, BattleSpaceBoard]

  override def retrieveGameState(gameId: GameId): GameStateResponse = Future(
    _gameState.get(gameId) match {
      case Some(gameBoard) =>
        Left(GameStateRetrieved(GameState(gameId, gameBoard)))
      case None =>
        Right(GameStateRetrievalFailed("id not found"))
    }
  )

  override def saveGameState(gameState: GameState): GameStateResponse = Future(
    _gameState.synchronized {
      Try {
        _gameState(gameState.gameId) = gameState.gameBoard
      } match {
        case Success(_) =>
          Left(GameStateSaved)
        case Failure(ex) =>
          Right(GameStateRetrievalFailed("couldnt save game state"))
      }
    }
  )
}
