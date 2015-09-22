package com.neilconcepts.storage.mem

import java.util.UUID

import com.neilconcepts.battlespace.domain.Messages.RegCreated
import com.neilconcepts.battlespace.storage.mem.InMemRegistration
import com.twitter.util.Await
import org.scalatest.{ WordSpec, Matchers }
import com.twitter.conversions.time._

class InMemGameStateSpec extends WordSpec with Matchers {
  val _db = new InMemRegistration
  val playerId = UUID.fromString("57530a42-1fed-4af7-928f-c9d316fff393")
  var gameId:Option[UUID] = None

  "InMem Game Storage" when {
    "saving" should {
      "create a new save" in {
        val reg = _db.createRegistration(playerId)
        val rez = Await.result(reg, 1.second)
        rez should be(RegCreated(_))
      }
    }
  }

}
