package com.neilconcepts.game

import java.util.UUID

import com.neilconcepts.battlespace.domain.bst.Player
import com.neilconcepts.battlespace.game.BattleSpaceGame
import org.scalatest.{ Matchers, WordSpec }

class BattleSpaceGameSpec extends WordSpec with Matchers {
  "A BattleSpaceGame" when {
    "generating" should {
      "create a new game" in {
        val id = UUID.fromString("a4601858-10f7-4b5d-acdb-bb9de98f0505")
        val player = Player(id)
        val bsg = new BattleSpaceGame(player)
        bsg should not be (null)
      }
    }
  }
}
