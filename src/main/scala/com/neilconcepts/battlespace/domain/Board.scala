package com.neilconcepts.battlespace.domain

import com.neilconcepts.battlespace.domain.GameObjects.GameObject

/**
 * board ::
 * The game board is made of a Matrix which
 * contains GameSpace areas, really nothing
 * more is known about space so it should be
 * left at that.
 */
object Board {
  type X = Int
  type Y = Int
  type Z = Int
  type GameSpace = Option[GameObject]

  val maxDimensions = 50
  case class Point(x: X, y: Y, z: Z)
  case class BattleSpaceBoard(gb: Map[Point, GameSpace])

  def generateBoard(): BattleSpaceBoard = {
    val gameSpace: Vector[(Point, GameSpace)] = (for (
      x <- 1 to maxDimensions;
      y <- 1 to maxDimensions;
      z <- 1 to maxDimensions
    ) yield (Point(x, y, z), None)).toVector

    val board: Map[Point, GameSpace] = gameSpace.map(g => g._1 -> g._2).toMap
    BattleSpaceBoard(gb = board)
  }

}