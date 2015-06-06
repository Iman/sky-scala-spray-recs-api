package com.sky.assignment.client

import scala.concurrent.duration._
import scala.util.parsing.json.{JSONArray, JSON}
import com.google.gson.{JsonArray, JsonElement, JsonObject, JsonParser}

/**
 * Created by iman on 31/05/15.
 */
trait RecsEngineClient

case class generateDuration(start: Long, end: Long) extends RecsEngineClient

object RecsEngineClient {

  private val numberOfLoops = 3
  private val duration = Duration(1, HOURS)

  def callRecsEngine(subscriber: String): String = {

    val finalJsonElement: JsonArray = new JsonArray()

    //In parallel
    /**
     * Example of endpoints URL with unique time sequences (start=1433116550892&end=1433120150892) * 3
     *
     * http://localhost:8080/recs/personalised?num=5&start=1433116550892&end=1433120150892&subscriber=abc
     * http://localhost:8080/recs/personalised?num=5&start=1433116550892&end=1433120150892&subscriber=abc
     * http://localhost:8080/recs/personalised?num=5&start=1433116550892&end=1433120150892&subscriber=abc
     */

    // for (time <- getEndpointDuration(numberOfLoops)) yield finalJsonElement.add(getRecsEngine(decorateEndpointUrl(subscriber, time), numberOfLoops))

    //In chain
    /**
     * Example of endpoints URL with unique time sequences
     *
     * http://localhost:8080/recs/personalised?num=5&start=1433116264182&end=1433119864182&subscriber=abc
     * http://localhost:8080/recs/personalised?num=5&start=1433116274186&end=1433119874186&subscriber=abc
     * http://localhost:8080/recs/personalised?num=5&start=1433116284190&end=1433119884190&subscriber=abc
     *
     */

    for (i <- 1 to numberOfLoops) yield finalJsonElement.add(getRecsEngine(decorateEndpointUrl(subscriber, getEndpointDuration(1).toIndexedSeq(0)), i))

    return finalJsonElement.toString
  }


  def getTime(index: Int): generateDuration = {
    val now = System.currentTimeMillis()
    generateDuration(start = now + (index - 1) * duration.toMillis, end = now + index * duration.toMillis)
  }

  def decorateEndpointUrl(subscriber: String, time: generateDuration): String =
    s"http://localhost:8080/recs/personalised?num=5&start=${time.start}&end=${time.end}&subscriber=$subscriber"

  def getEndpointDuration(total: Int): Iterable[generateDuration] = {
    for (i <- 1 to total) yield getTime(i)
  }

  def getRecsEngine(url: String, index: Int): JsonElement = {
    val jsonElement: JsonElement = new JsonParser().parse(RestClient.APICall(url));
    val jsonObject: JsonObject = jsonElement.getAsJsonObject();
    jsonObject.addProperty("expiry", getTime(index).end)
    val result: String = jsonObject.toString()

    return jsonObject
  }

}
