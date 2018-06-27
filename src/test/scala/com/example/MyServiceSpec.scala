package com.example

import spray.testkit.{ScalatestRouteTest, Specs2RouteTest}
import spray.http._
import StatusCodes._
import org.scalatest.{FreeSpec, Matchers}
import spray.http.HttpHeaders.RawHeader

class MyServiceSpec extends FreeSpec with ScalatestRouteTest with Matchers with MyService {

  def actorRefFactory = system // connect the DSL to the test ActorSystem

  "my route should" - {
    "work with application/json" in {
      Get() ~> addHeader(RawHeader("Accept","application/json")) ~> myRoute ~> check {
        contentType.toString() should startWith("application/json")
        responseAs[String] should include("there")
      }
    }

    "work with application/myJson" in {
      Get() ~> addHeader(RawHeader("Accept","application/myJson")) ~> myRoute ~> check {
        contentType.toString() should startWith("application/myJson")
        responseAs[String] should include("there")
      }
    }

  }


}

