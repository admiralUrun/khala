package dao

import cats.effect.IO
import javax.inject.Inject
import doobie._
import doobie.implicits._
import models.{GettingJson, Message, SendingJson}

class Dao @Inject()(dS: DoobieStore)  {
  private val xa = dS.getXa
  private val messageSelect = sql"select id, author, message, wasSend from messages "
  def insertAndGetNewMessages(g: GettingJson): IO[SendingJson] = {
    (for {
      _ <- sql"insert into messages(author, message, wasSend) values (${g.message.author}, ${g.message.message}, ${g.message.wasSent})".update.run
      feat <- sql"select count(id) from messages".query[Int].unique
      ms <- (messageSelect ++ fr"limit 50 offset ${g.feat}").query[Message].to[Array]
    } yield SendingJson(feat, ms)).transact(xa)
  }

  def getMessagesAndFeat: IO[SendingJson] ={
    (for {
      feat <- sql"select count(id) from messages".query[Int].unique
      ms <- (fr"select * from (" ++ messageSelect ++ fr" limit 50) sub order by id").query[Message].to[Array]
    } yield SendingJson(feat, ms)).transact(xa)
  }

}
