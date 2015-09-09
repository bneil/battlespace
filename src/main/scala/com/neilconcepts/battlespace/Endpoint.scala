package com.neilconcepts.battlespace

import java.util.UUID

import com.neilconcepts.battlespace.domain.ErrorHandling
import com.neilconcepts.battlespace.domain.bst.Player
import com.neilconcepts.battlespace.storage.Registration
import com.twitter.finagle.Service
import com.twitter.finagle.httpx.{ Request, Response }
import io.finch.response.{ BadRequest, Created }
import io.finch.route.{ Router, string, _ }
import io.circe.syntax._

object Endpoint extends ErrorHandling {

  import RegistrationRoute._

  def makeService(db: Registration): Service[Request, Response] =
    getRegUser.toService

}

object RegistrationRoute {
  def getRegUser: Router[Response] =
    get("registration" / string) { id: String =>
      //db.readRegistration(UUID.fromString(id))
      val registeredPlayer: Player = Player(UUID.randomUUID())
      Created(
        Map("player" -> registeredPlayer.toString).asJson.noSpaces
      )
    }
}
