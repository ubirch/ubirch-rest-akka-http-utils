package com.ubirch.util.rest.akka.directives

import akka.http.scaladsl.model.HttpMethods.{DELETE, GET, OPTIONS, POST, PUT}
import akka.http.scaladsl.server.{Directives, RejectionHandler, Route}
import ch.megard.akka.http.cors.scaladsl.CorsDirectives.{cors, corsRejectionHandler}
import ch.megard.akka.http.cors.scaladsl.settings.CorsSettings

import scala.collection.immutable.Seq

/**
  * author: cvandrei
  * since: 2016-09-06
  */
trait CORSDirective extends Directives {

  private val corsSettings = CorsSettings.defaultSettings.withAllowedMethods(Seq(GET, POST, PUT, DELETE, OPTIONS))

  private val rejectionHandler = corsRejectionHandler withFallback RejectionHandler.default

  def respondWithCORS(routes: => Route): Route = {

    cors(corsSettings) {
      handleRejections(rejectionHandler) {
        routes
      }
    }

  }

}
