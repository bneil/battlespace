package com.neilconcepts.battlespace.game

import com.neilconcepts.battlespace.domain.Board.{Point, GameSpace, BattleSpaceBoard, GameMatrix}
import com.neilconcepts.battlespace.domain.bst.Player

/**
 * Engine ::
 * simple factory for the game engine
 */
object Engine {
  def apply(player: Player) = new Engine(player)
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
class Engine(player: Player, numPlayers: Int = 1) {
  val maxDimensions = 10
  var gameBoard : BattleSpaceBoard = generateBoard()

  def generateBoard(): BattleSpaceBoard = {
    val gameSpace : Vector[(Point, GameSpace)] = (for(
      x <- 1 to maxDimensions;
      y <- 1 to maxDimensions;
      z <- 1 to maxDimensions;
    ) yield (Point(x,y,z), None)).toVector

    val board : Map[Point, GameSpace]= gameSpace.map(g => g._1 -> g._2).toMap
    BattleSpaceBoard(gb = board)
  }

  def generateGameObjects(): Unit ={

  }

  def apply(): Unit ={
    generateGameObjects()
  }
}
