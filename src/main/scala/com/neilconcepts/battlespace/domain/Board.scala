package com.neilconcepts.battlespace.domain

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
  type GameSpace = String

  val maxDimensions = 10
  case class Point(x: X, y: Y, z: Z)
  case class BattleSpace(p: Point, g: GameSpace)
  case class BattleSpaceBoard(gb: Seq[BattleSpace])

  def generateBoard(): BattleSpaceBoard = {
    val gameSpaces: Seq[BattleSpace] = for (
      x <- 1 to maxDimensions;
      y <- 1 to maxDimensions;
      z <- 1 to maxDimensions
    ) yield BattleSpace(Point(x, y, z), "")

    BattleSpaceBoard(gameSpaces)
  }

  //attack the board
  def attackBoard(point: Point, gameBoard: BattleSpaceBoard): BattleSpaceBoard = {
    gameBoard.gb.find(_.p == point) match {
      case Some(gameSpace) =>
        println(s"hit gameSpace! contains: ${gameSpace.g}")
      case None =>
      //do nothing
    }
    gameBoard
  }
}