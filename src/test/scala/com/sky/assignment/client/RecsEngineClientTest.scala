package scala.com.sky.assignment.client

import com.sky.assignment.client.{generateDuration, RecsEngineClient}
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._


/**
 * Created by iman on 01/06/15.
 */

class RecsEngineClientTest extends FunSuite with BeforeAndAfter with MockitoSugar {

  test("Recs Engine Client") {

    val duration = generateDuration.apply(1433116264182L, 1433119864182L);

    trait MockRecsEngineClient {

      //callRecsEngine
      val recsEngineClient = mock[RecsEngineClient.type]
      when(recsEngineClient.callRecsEngine("abc")).thenCallRealMethod()
      when(recsEngineClient.callRecsEngine("abc")).thenReturn("dummy")

      val recsEngineClientResult = recsEngineClient.callRecsEngine("abc")

      assert(recsEngineClientResult == "abc")
      assert(recsEngineClientResult == "dummy")
      assert(recsEngineClientResult == recsEngineClient.callRecsEngine("abc"))
      assert(!recsEngineClientResult.isEmpty)


      //getTime
      when(recsEngineClient.getTime(1)).thenReturn(duration)
      val recsEngineClientTime = recsEngineClient.getTime(1)

      assert(recsEngineClientTime == duration)


      //decorateEndpointUrl
      val url = "http://localhost:8080/recs/personalised?num=5&start=1433116264182L&end=1433119864182L&subscriber=dummyId"
      when(recsEngineClient.decorateEndpointUrl("dummyId", duration)).thenReturn(url)

      val recsEngineClientEndpointUrl = recsEngineClient.decorateEndpointUrl("dummyId", duration)

      assert(recsEngineClientEndpointUrl == url)
      assert(!recsEngineClientEndpointUrl.isEmpty)

      //getEndpointDuration
      var totalIteration = 4
      when(recsEngineClient.getEndpointDuration(totalIteration)).thenCallRealMethod()

      for (i <- 1 to 4) yield assert(recsEngineClient.getEndpointDuration(i) == duration)

      //getRecsEngine
      var dummyRecsEngineJson = "{\n  \"recommendations\": {\n    \"recommendations\": [\n      {\n        \"uuid\": \"dba541fa-6810-414e-80a7-435a3e227b7d\",\n        \"start\": \"1433214937318\",\n        \"end\": \"1433221957318\"\n      },\n      {\n        \"uuid\": \"88f4d54e-56d1-44d3-b501-e07c799faa0d\",\n        \"start\": \"1433197897318\",\n        \"end\": \"1433204557318\"\n      },\n      {\n        \"uuid\": \"5df0fefd-3e2f-40b5-a1f4-63b3bd4d2ca1\",\n        \"start\": \"1433191957318\",\n        \"end\": \"1433198857318\"\n      },\n      {\n        \"uuid\": \"2dc13668-ecdf-42ac-ad01-34404bfc7b0b\",\n        \"start\": \"1433216917318\",\n        \"end\": \"1433222077318\"\n      },\n      {\n        \"uuid\": \"ca866633-c01d-474c-9768-a93dcfc0dd1a\",\n        \"start\": \"1433213557319\",\n        \"end\": \"1433220577319\"\n      }\n    ]\n  }\n}"
      var dummyJsonResult = "[\n   {\n    \"recommendations\": [\n      {\n        \"uuid\": \"dba541fa-6810-414e-80a7-435a3e227b7d\",\n        \"start\": \"1433214937318\",\n        \"end\": \"1433221957318\"\n      },\n      {\n        \"uuid\": \"88f4d54e-56d1-44d3-b501-e07c799faa0d\",\n        \"start\": \"1433197897318\",\n        \"end\": \"1433204557318\"\n      },\n      {\n        \"uuid\": \"5df0fefd-3e2f-40b5-a1f4-63b3bd4d2ca1\",\n        \"start\": \"1433191957318\",\n        \"end\": \"1433198857318\"\n      },\n      {\n        \"uuid\": \"2dc13668-ecdf-42ac-ad01-34404bfc7b0b\",\n        \"start\": \"1433216917318\",\n        \"end\": \"1433222077318\"\n      },\n      {\n        \"uuid\": \"ca866633-c01d-474c-9768-a93dcfc0dd1a\",\n        \"start\": \"1433213557319\",\n        \"end\": \"1433220577319\"\n      }\n    ]\n      \"expiry\":1415282400000\n   }\n]"
      when(recsEngineClient.getRecsEngine(dummyRecsEngineJson, 1)).thenCallRealMethod()
      val apiResult = recsEngineClient.getRecsEngine(dummyRecsEngineJson, 1)

      assert(apiResult.isJsonObject)
      assert(apiResult.toString ==  dummyJsonResult)
    }

    val recsEngineClientDuration = mock[generateDuration]

    when(recsEngineClientDuration.start).thenReturn(1433116264182L)
    when(recsEngineClientDuration.end).thenReturn(1433119864182L)

    assert(duration.start == 1433116264182L)
    assert(duration.end == 1433119864182L)

    assert(recsEngineClientDuration.start == 1433116264182L)
    assert(recsEngineClientDuration.end == 1433119864182L)

    assert(recsEngineClientDuration.start == duration.start)
    assert(recsEngineClientDuration.end == duration.end)

  }
}
