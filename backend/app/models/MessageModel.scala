package models

import cats.effect.IO
import dao.Dao
import javax.inject._
import play.api.libs.json._
import play.api.libs.functional.syntax._

@Singleton
class MessageModel @Inject()(dao: Dao) {
  implicit val messageReader: Reads[Message] = (
    (JsPath \ "id").readNullable[Int] and
      (JsPath \ "author").read[String] and
      (JsPath \ "message").read[String] and
      (JsPath \ "wasSent").read[String]
  )(Message.apply _)

  implicit val gettingJsonReader: Reads[GettingJson] = (
    (JsPath \ "feat").read[Int] and
      (JsPath \ "message").read[Message]
  )(GettingJson.apply _)

  implicit val messageWriter: OWrites[Message] = (
    (JsPath \ "id").writeNullable[Int] and
      (JsPath \ "author").write[String] and
      (JsPath \ "message").write[String] and
      (JsPath \ "wasSent").write[String]
    )(unlift(Message.unapply))

  implicit val sendingJsonWriter: OWrites[SendingJson] = (
    (JsPath \ "feat").write[Int] and
      (JsPath \ "messages").write[Array[Message]]
  )(unlift(SendingJson.unapply))


  def onMessage(json: play.api.libs.json.JsValue): play.api.libs.json.JsValue = {
    Json.toJson(dao.insertAndGetNewMessages(json.as[GettingJson]).unsafeRunSync())
  }

  def onOpen: JsValue ={
    Json.toJson(dao.getMessagesAndFeat.unsafeRunSync())
  }

}
case class SendingJson(feat: Int, messages: Array[Message])
case class GettingJson(feat: Int, message: Message)
case class Message(id: Option[Int], author: String, message: String, wasSent: String)