package com.neilconcepts.battlespace.domain

import com.neilconcepts.battlespace.domain.Board.BattleSpaceBoard
import org.scalatest._

class BoardSpec extends WordSpec with Matchers {
  val mx = Board.maxDimensions
  var gb: Option[BattleSpaceBoard] = None

  "A Board" when {
    "Generating a board" should {
      s"create a valid $mx / $mx / $mx board" in {
        gb = Some(Board.generateBoard())
        gb.get should not be null
      }
      "generate the game pieces properly" in {

      }
    }
  }

}
