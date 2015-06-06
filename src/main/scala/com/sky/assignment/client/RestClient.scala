package com.sky.assignment.client

import akka.actor.{Props, ActorSystem}
import org.apache.http.client.methods.{HttpGet, CloseableHttpResponse}
import org.apache.http.impl.client.{CloseableHttpClient, HttpClients}
import org.apache.http.util.EntityUtils
import spray.httpx.encoding.{Deflate, Gzip}
import spray.http.{BasicHttpCredentials, HttpResponse, Uri, HttpRequest}
import scala.util.{Try, Success, Failure}

/**
 * Created by iman on 31/05/15.
 */

trait RestClient

object RestClient {

  def APICall(url: String): String = {

    //    val pipeline: HttpRequest => Future[AnyRef] = sendReceive
    //    val response: Future[AnyRef] = pipeline(Get(Uri(url)))
    //    val result = Try(Await.result(response, 10.seconds))

    val httpClient: CloseableHttpClient = {
      val client = HttpClients.createDefault()
      client
    }

    val httpGet: HttpGet = new HttpGet(url);
    httpGet.addHeader("accept", "application/json")
    val response1: CloseableHttpResponse = httpClient.execute(httpGet);
    val responseBody = EntityUtils.toString(response1.getEntity);

    return responseBody
  }

}

