package scala.com.sky.assignment.client

import com.sky.assignment.client.{RestClient}
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfter, FunSuite}

/**
 * Created by iman on 01/06/15.
 */
class RestClientUnitTest extends FunSuite with BeforeAndAfter with MockitoSugar {

  test("Rest Client") {

    trait MockRestClient {
      val restClient = mock[RestClient.type ]

      val dummyURL = "http://localhost/dummy/foo/bar&time=54645763836"

      when(restClient.APICall(dummyURL)).thenReturn("responseBody")
      when(restClient.APICall("localhost")).thenReturn("<h1>hello world</h1>")

      val dummyRestClientA = restClient.APICall(dummyURL)
      val dummyRestClientB = restClient.APICall("localhost")

      assert(dummyRestClientA == "responseBody")
      assert(dummyRestClientB.contains("<h1>hello world</h1>"))
    }

  }
}
