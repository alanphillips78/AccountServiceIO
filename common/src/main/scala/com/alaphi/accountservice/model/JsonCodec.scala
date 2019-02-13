package com.alaphi.accountservice.model

import cats.effect.IO
import com.alaphi.accountservice.model.Account._
import org.http4s.circe.{jsonEncoderOf, jsonOf}
import io.circe.generic.auto._
import io.circe.Encoder
import io.circe.syntax._

object JsonCodec {
  implicit val decoderAccCreate = jsonOf[IO, AccountCreation]
  implicit val decoderDeposit = jsonOf[IO, Deposit]
  implicit val decoderMoneyTransfer = jsonOf[IO, MoneyTransfer]

  implicit val encoderAcc = jsonEncoderOf[IO, Account]
  implicit val encoderAccs = jsonEncoderOf[IO, Seq[Account]]
  implicit val encoderDepositSuccess = jsonEncoderOf[IO, DepositSuccess]
  implicit val encoderTransferSuccess = jsonEncoderOf[IO, TransferSuccess]

  implicit val encodePayload: Encoder[Payload] = Encoder.instance {
    case accountCreation @ AccountCreation(_, _) => accountCreation.asJson
  }

}