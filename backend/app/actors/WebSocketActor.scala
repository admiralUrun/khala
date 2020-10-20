package actors

import actors.ChatActor.NewChatter
import actors.WebSocketActor.SendingMessage
import akka.actor._

class WebSocketActor(out: ActorRef, chatActor: ActorRef) extends Actor {
  chatActor ! NewChatter(self)
  def receive: Receive = {
    case s: play.api.libs.json.JsValue => chatActor ! ChatActor.Message(s)
    case SendingMessage(msg) => out ! msg
    case m => println("I didn't parse this message" + m)
  }
}

object WebSocketActor {
  case class SendingMessage(msg: play.api.libs.json.JsValue)
  def props(out: ActorRef, chatActor: ActorRef): Props = Props(new WebSocketActor(out, chatActor))
}
