import com.github.plokhotnyuk.jsoniter_scala.core.{JsonValueCodec, writeToString}
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import zio._
import zio.http._

final case class Message(message: String)

object Main extends ZIOAppDefault {
  private final val message                         = "Hello, World!"
  implicit val codec: JsonValueCodec[Message] = JsonCodecMaker.make
  private final val plainText: Route[Any, Nothing] = Method.GET / "plaintext" -> handler(Response.text(message))
  private final val json: Route[Any, Nothing] = Method.GET / "json" -> handler(Response.json(writeToString(Message(message))))

  private final val routes: Routes[Any, Response] =
    // List of all the routes
    Routes(plainText, json)
      // Handle all unhandled errors
      .handleError(Response.internalServerError)

  // Serving the routes using the default server layer on port 8080
  def run = Server.serve(routes).provide(Server.defaultWithPort(8080))

// TODO:
//  val formatter: DateTimeFormatter                = DateTimeFormatter.RFC_1123_DATE_TIME.withZone(ZoneOffset.UTC)
//  val constantHeaders: List[Header]               = Header("server", "zio-http") :: Nil
//  @volatile var lastHeaders: (Long, List[Header]) = (0, Nil)
//  def headers(): List[Header] = {
//    val t = System.currentTimeMillis()
//    if (t - lastHeaders._1 >= 1000)
//      lastHeaders = (t, Header("date", formatter.format(Instant.ofEpochMilli(t))) :: constantHeaders)
//    lastHeaders._2
//  }
}
