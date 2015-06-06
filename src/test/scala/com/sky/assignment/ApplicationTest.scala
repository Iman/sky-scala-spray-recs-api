package scala.com.sky.assignment


import com.sky.assignment.Application
import org.scalatest._
import spray.routing.Directives
import spray.testkit.ScalatestRouteTest

import scala.util.parsing.json.JSONObject


/**
 * Created by iman on 01/06/15.
 */

class ApplicationTest extends FlatSpec with ShouldMatchers with ScalatestRouteTest with Directives {

  it should "have recommendations" in {
    Get("/personalised/abc") ~> Application.apiRoute ~> check {
      responseAs[String] should include("recommendations")
    }
  }

  it should "have uuid" in {
    Get("/personalised/abc") ~> Application.apiRoute ~> check {
      responseAs[String] should include("uuid")
    }
  }

  it should "have start" in {
    Get("/personalised/abc") ~> Application.apiRoute ~> check {
      responseAs[String] should include("start")
    }
  }

  it should "have end" in {
    Get("/personalised/abc") ~> Application.apiRoute ~> check {
      responseAs[String] should include("end")
    }
  }

  it should "have expiry" in {
    Get("/personalised/abc") ~> Application.apiRoute ~> check {
      responseAs[String] should include("expiry")
    }
  }

  it should "try other paths unhandled" in {
    Get("/dummy") ~> Application.apiRoute ~> check {
      handled == false
    }
  }

  it should "return a MethodNotAllowed error for PUT requests to the root path" in {
    Get("/personalised/abc") ~> Application.apiRoute ~> check {
      status === "MethodNotAllowed"
      responseAs[String] === "HTTP method not allowed, supported methods: GET"
    }
  }


}