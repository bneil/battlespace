package com.neilconcepts.storage.mem

import java.util.UUID

import com.neilconcepts.battlespace.domain.Messages
import com.neilconcepts.battlespace.domain.Messages.{ RegRemoved, RegMessage, RegFound, RegCreated }
import com.neilconcepts.battlespace.domain.bst.Player
import com.neilconcepts.battlespace.storage.mem.InMemRegistration
import com.twitter.util.Await
import org.scalatest.{ WordSpec, Matchers }
import com.twitter.conversions.time._

class InMemGameStateSpec extends WordSpec with Matchers {
  import Messages.RegUpdated

  val _db = new InMemRegistration
  val playerId = UUID.fromString("57530a42-1fed-4af7-928f-c9d316fff393")
  var gameId: Option[UUID] = None
  val player = Player(playerId)

  "InMem Game Storage" when {
    "saving" should {
      "create a new save" in {
        val reg = _db.createRegistration(playerId)
        val rez = Await.result(reg, 1.second)
        rez shouldBe a[RegFound]
      }
    }
    "updating" should {
      "update an entry" in {
        val reg = _db.updateRegistration(playerId, player)
        val upd = Await.result(reg, 1.second).right
        val rez = for (a <- upd) yield a
        rez should be(Right(RegUpdated))
      }
    }
    "read" should {
      "be able to read an entry" in {
        val reg = _db.readRegistration(playerId)
        val rez = Await.result(reg, 1.second).get
        rez shouldBe a[Player]
      }
    }
    "retrieve" should {
      "be able to return all players" in {
        val reg = _db.retrieveRegistrations
        val rez = Await.result(reg, 1.second)
        rez shouldBe a[Seq[_]]
      }
    }
    "remove" should {
      "be able to remove an entry" in {
        val reg = _db.removeRegistration(playerId)
        val rem = Await.result(reg, 1.second).right
        val rez = for (a <- rem) yield a
        rez should be(Right(RegRemoved))
      }
    }
  }
  //def removeRegistration(id: PlayerId): RegResponse
  //def updateRegistration(oldID: PlayerId, updatedPlayer: Player): RegResponse
  //def readRegistration(id: PlayerId): Future[Option[Player]]
  //def retrieveRegistrations: Future[Seq[Player]]
}
