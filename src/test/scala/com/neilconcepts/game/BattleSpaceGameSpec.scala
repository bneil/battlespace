package com.neilconcepts.game

import java.util.UUID

import com.neilconcepts.battlespace.domain.bst.Player
import com.neilconcepts.battlespace.game.BattleSpaceGame
import org.scalatest.{ Matchers, WordSpec }

class BattleSpaceGameSpec extends WordSpec with Matchers {
  val playerId = UUID.fromString("a4601858-10f7-4b5d-acdb-bb9de98f0505")
  var bsg: Option[BattleSpaceGame] = None

  "A BattleSpaceGame" when {
    "generating" should {
      "create a new game" in {
        val player = Player(playerId)
        bsg = Some(new BattleSpaceGame(player))

        bsg.get should not be (None)
        bsg.get.player.id === playerId
      }
    }
  }
}
