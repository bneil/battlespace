package com.neilconcepts.battlespace.domain

import com.neilconcepts.battlespace.domain.Board.BattleSpaceBoard
import org.scalatest._

class BoardSpec extends WordSpec with Matchers {
  val mx = Board.maxDimensions
  "A Board" when {
    "Generating a board" should {
      s"create a valid $mx / $mx / $mx board" in {
        val newBoard: BattleSpaceBoard = Board.generateBoard()
        newBoard should not be null
      }
    }
  }

}
