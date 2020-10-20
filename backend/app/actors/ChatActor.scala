package actors

import play.api.libs.json.JsValue
import actors.ChatActor._
import akka.actor._

class ChatActor extends Actor {
  private var chatters = List.empty[ActorRef]
  def receive: Receive = {
    case NewChatter(newChatter) => chatters = newChatter :: chatters
    case Message(msg) => chatters.foreach(c => c ! WebSocketActor.SendingMessage(msg))
    case s: String =>
      println(s)
    case _ => println("I didn't parse this message")
  }
}

object ChatActor {
  case class NewChatter(chatter: ActorRef)
  case class Message(msg: play.api.libs.json.JsValue)
  def props: Props = Props(new ChatActor)
}
