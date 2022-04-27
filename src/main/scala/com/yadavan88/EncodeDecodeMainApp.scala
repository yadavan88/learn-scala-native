package com.yadavan88

import mainargs.{Flag, ParserForMethods, arg, main}
import scalanative._
import java.nio.charset.StandardCharsets.UTF_8
import scala.scalanative.unsafe._
import scala.scalanative.libc.string

object EncodeDecodeMainApp {
  @main
  def run(
           @arg(short = 't', name = "text", doc = "string to encode/decode")
           text: String = "eWFkdQ==",
           @arg(name = "mode", doc = "use encode/decode for the required operation")
           mode: String = "decode"
         ) = {
    println(fansi.Color.DarkGray("This is a coloured text \n"))

    val s1 = "Scala"
    val s2 = "Native"
    val scalaNative: String = Zone { implicit z =>
      val scalaNativeC: CString = string.strcat(toCString(s1), toCString(s2))
      println(fromCString(scalaNativeC))
      fromCString(scalaNativeC)
    }
    val strLength: CSize = string.strlen(c"Hello ScalaNative")
    println(s"Length of string is: " + strLength)
    println("In Long: " + strLength.toLong)

    val hello = "Hello "
    val world = "World"
    val helloWorld: String = Zone { implicit z =>
      val combined = string.strcat(toCString(hello), toCString(world))
      fromCString(combined)
    }
    println(helloWorld) //prints Hello World

    val appendedStr = Zone { implicit z =>
      val asd = string.strcat(toCString(text), toCString(mode))
      println("Result before is: " + fromCString(asd))
      fromCString(asd)
    }

    println("appendedStr: " + appendedStr)

    string_oper.get_length(c"Good Morning")

    mode match {
      case "encode" =>
        val encoded =
          new String(java.util.Base64.getEncoder.encode(text.getBytes), UTF_8)
        val printStr =
          fansi.Color.Blue(s"Encoded value for `$text` : ${encoded} ")
        println(printStr)
      case "decode" =>
        val decoded =
          new String(java.util.Base64.getDecoder.decode(text), UTF_8)
        val printStr =
          fansi.Color.Green(s"Decoded value for `$text` : ${decoded} ")
        println("Decoded from Scala: " + printStr)
        Zone { implicit z =>
          val l = string_oper.get_length(toCString(text))
          println(s"Length of '$text' is : " + l)
          curl_app.base64_decode(toCString(text))
        }
      case _ =>
        println(
          fansi.Underlined
            .On("Invalid option, use encode/decode for the mode")
            .overlay(fansi.Color.Red)
        )
    }
  }

  def main(args: Array[String]): Unit = ParserForMethods(this).runOrExit(args)
}

@extern
object string_oper {
  def get_length(str: CString): Int = extern
}

@extern
@link("curl")
object curl_app {
  def base64_decode(str: CString): Int = extern
}
