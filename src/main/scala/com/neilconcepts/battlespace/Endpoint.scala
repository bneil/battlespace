package com.neilconcepts.battlespace

import java.util.UUID

import com.neilconcepts.battlespace.domain.ErrorHandling
import com.neilconcepts.battlespace.domain.bst.{ PlayerID, Player }
import com.neilconcepts.battlespace.storage.Registration
import com.twitter.finagle.Service
import com.twitter.finagle.httpx.{ Request, Response }
import io.finch.response.{ BadRequest, Created }
import io.finch.route.{ Router, string, _ }
import io.circe.syntax._

/**
 * Endpoint ::
 * This object is going to be where all the finch routes get
 * composed
 */
object Endpoint extends ErrorHandling {

  import RegistrationRoutes._

  def makeService(db: Registration): Service[Request, Response] =
    getRegUser.toService

}

/**
 * RegistrationRoutes ::
 * These are all responsible for registering new accounts or reporting back that
 * the accounts are no accessable. Registrations are apart of the [[Registration]]
 * case class and are in the bs types domain.
 *
 * getRegUser -> this is currently a testing route and will be deprecated once I
 *               get used to the rest of the system.
 *
 */
object RegistrationRoutes {

  def getRegUser: Router[Response] =
    get("registration" / string) { id: String =>
      //db.readRegistration(UUID.fromString(id))
      val registeredPlayer: Player = Player(UUID.randomUUID())
      println(registeredPlayer.toString)
      Created(
        Map("player" -> registeredPlayer.toString).asJson.noSpaces
      )
    }
}
