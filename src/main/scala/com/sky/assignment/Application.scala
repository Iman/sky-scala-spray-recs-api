package com.sky.assignment

import akka.actor.ActorSystem
import akka.event.slf4j.SLF4JLogging
import com.sky.assignment.client.RecsEngineClient
import spray.http.MediaTypes
import spray.routing.SimpleRoutingApp

object Application extends App with SimpleRoutingApp with SLF4JLogging {

  implicit val system = ActorSystem("recs")

  lazy val apiRoute = {
    path("personalised" / Segment) { subscriber =>
      get {
        respondWithMediaType(MediaTypes.`application/json`) {
          complete {

            val finalResult = RecsEngineClient.callRecsEngine(subscriber)
            finalResult
          }
        }
      }
    }
  }

  startServer(interface = "localhost", port = 8090) {
    apiRoute
  }
}
