package com.neilconcepts.battlespace

import com.twitter.finagle.Httpx
import com.twitter.util.Await
import io.finch.route._
import io.finch.request._


/**
 * Boot ::
 * Just boilerplate at the moment, working on getting this api going
*/
object Boot extends App {
  val title: RequestReader[String] = paramOption("title").withDefault("")

  val api: Router[String] =
    get(("hello" | "hi") / string ? title) { (name: String, title: String) =>
      s"Hello, $title $name!"
    }

  println("Starting serving on port 8080")
  Await.ready(Httpx.serve(":8080", api.toService))
}
