package com.neilconcepts.battlespace.domain

import scala.util.Random

/**
 * board ::
 * The game board is made of a Matrix which
 * contains GameSpace areas, really nothing
 * more is known about space so it should be
 * left at that.
 */
object Board extends Ships {
  type X = Int
  type Y = Int
  type Z = Int
  type GameSpace = String
  type Points = (X, Y, Z)

  sealed trait Direction
  case object Horizontal extends Direction
  case object Vertical extends Direction
  case object Diag extends Direction
  val directions = Seq(Horizontal, Vertical, Diag)

  val maxDimensions = 10
  case class Point(x: Int, y: Int, z: Int)
  case class BattleSpace(p: Point, g: GameSpace)
  case class BattleSpaceBoard(gb: Seq[BattleSpace])

  def getRandDirection: Option[Direction] =
    Random.shuffle(directions).headOption

  def generateRandomEncounter() = ???

  def addShips(gameBoard: BattleSpaceBoard): BattleSpaceBoard = {
      def positionByDirection: Direction => Points = {
        case Horizontal => ???
        case Vertical   => ???
        case Diag       => ???
      }
    for (
      ship <- spaceShips;
      num <- 1 to ship.siz;
      direction <- getRandDirection
    ) yield {
      positionByDirection(direction)
    }

    gameBoard
  }

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