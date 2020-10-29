package actors

import actors.ChatActor._
import akka.actor._
import cats.effect.IO
import play.api.libs.json.{JsValue, Json}

class ChatActor(onMessage: JsValue => JsValue, onOpen: => JsValue) extends Actor {
  private var chatters = List.empty[ActorRef]
  def receive: Receive = {
    case NewChatter(newChatter) => {
      chatters = newChatter :: chatters
      newChatter ! WebSocketActor.SendingMessage(onOpen)
    }
    case Message(msg) =>
      val x = onMessage(msg)
      chatters.foreach(c => c ! WebSocketActor.SendingMessage(x))
    case _ => println("I didn't parse this message")
  }
}

object ChatActor {
  case class NewChatter(chatter: ActorRef)
  case class Message(msg: play.api.libs.json.JsValue)
  def props(onMessage: JsValue => JsValue, onOpen: => JsValue): Props = Props(new ChatActor(onMessage, onOpen))
}
