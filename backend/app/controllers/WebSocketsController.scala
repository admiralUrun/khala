package controllers
import actors.{ChatActor, WebSocketActor}
import play.api.libs.json._
import akka.actor._
import akka.stream.Materializer
import javax.inject._
import play.api.libs.streams.ActorFlow
import play.api.mvc._

@Singleton
class WebSocketsController @Inject()(cc: ControllerComponents)(implicit system: ActorSystem, mat: Materializer)
  extends  AbstractController(cc) {
  val chatActor: ActorRef = system.actorOf(Props[ChatActor], "chatActor")
  def startConnection: WebSocket = WebSocket.accept[JsValue, JsValue] { _ =>
    ActorFlow.actorRef { out =>
      WebSocketActor.props(out, chatActor)
    }
  }



}
