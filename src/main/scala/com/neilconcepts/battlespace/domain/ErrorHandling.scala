package com.neilconcepts.battlespace.domain

import com.neilconcepts.battlespace.domain.Messages.RegError
import com.twitter.finagle.httpx.{ Request, Response }
import com.twitter.finagle.{ Service, SimpleFilter }
import com.twitter.util.Future
import io.circe.generic.auto._
import io.finch.circe._
import io.finch.request._
import io.finch.request.items._
import io.finch.response._

/**
 * ErrorHandling ::
 * Handles all the errors within the application
 */
trait ErrorHandling {
  /**
   * Tells the app how to handle certain types of servable errors (i.e. Error)
   */
  def errorHandler: PartialFunction[Throwable, Response] = {
    case NotPresent(ParamItem(p)) => BadRequest(
      Map("error" -> "param_not_present", "param" -> p)
    )
    case NotPresent(BodyItem) => BadRequest(
      Map("error" -> "body_not_present")
    )
    case NotParsed(ParamItem(p), _, _) => BadRequest(
      Map("error" -> "param_not_parsed", "param" -> p)
    )
    case NotParsed(BodyItem, _, _) => BadRequest(
      Map("error" -> "body_not_parsed")
    )
    case NotValid(ParamItem(p), rule) => BadRequest(
      Map("error" -> "param_not_valid", "param" -> p, "rule" -> rule)
    )
    // Domain errors
    case error: RegError => NotFound(
      Map("error" -> error.msg)
    )
  }

  /**
   * A simple Finagle filter that handles all the exceptions, which might be thrown by
   * a request reader of one of the REST services.
   */
  def handleExceptions: SimpleFilter[Request, Response] = new SimpleFilter[Request, Response] {
    def apply(req: Request, service: Service[Request, Response]): Future[Response] =
      service(req).handle(errorHandler)
  }
}
