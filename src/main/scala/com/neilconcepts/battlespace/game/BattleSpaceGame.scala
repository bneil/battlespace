package com.neilconcepts.battlespace.game

import com.neilconcepts.battlespace.domain.Board
import com.neilconcepts.battlespace.domain.Board.BattleSpaceBoard
import com.neilconcepts.battlespace.domain.bst.Player

/**
 * BattleSpaceGame ::
 * simple factory for the game engine
 */
object BattleSpaceGame {
  def apply(player: Player) = new BattleSpaceGame(player)
}

/**
 * Engine ::
 * Used to control all the gameplay in battle space.
 * On initialization the game objects get set to map
 * and the player is given the option to start a single
 * player or two player game.
 *
 * A single player game, allows the computer to generate
 * a world for the player to demolish within a set number
 * of turns.
 *
 * Two player, allows the player to set there own gameboard
 * and be attacked.
 *
 * @param player the player
 * @param numPlayers defines the num of players,
 *                   given 1 its a one handed battle
 */
class BattleSpaceGame(val player: Player, numPlayers: Int = 1) {
  var gameBoard: BattleSpaceBoard = Board.generateBoard()

  def generateGameObjects(): Unit = {

  }

  def apply(): Unit = {
    generateGameObjects()
  }
}
