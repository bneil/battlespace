package com.neilconcepts.battlespace.domain

import java.util.UUID

import com.neilconcepts.battlespace.domain.bst.Player
import org.scalatest.{ Matchers, WordSpec }

class BSTSpec extends WordSpec with Matchers {
  "BattleSpaceTypes" when {
    "generating a player" should {
      "create a unique player" in {
        val idStr = "7a68a829-af66-4f7e-9b34-03d9c9f79a3f"
        val id = UUID.fromString(idStr)
        val player = Player(id)

        player.id should be(id)
      }
    }
  }
}
