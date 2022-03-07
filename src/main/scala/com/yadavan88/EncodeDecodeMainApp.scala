package com.yadavan88
import mainargs.{main, arg, ParserForMethods, Flag}
import java.nio.charset.StandardCharsets.UTF_8

object EncodeDecodeMainApp {
  @main
  def run(
      @arg(short = 't', name = "text", doc = "string to encode/decode")
      text: String,
      @arg(name = "mode", doc = "use encode/decode for the required operation")
      mode: String = "encode"
  ) = {
    println(fansi.Color.DarkGray( "Hello, world, this is from ScalaNative! \n"))
    mode match {
      case "encode" =>
        val encoded =
          new String(java.util.Base64.getEncoder.encode(text.getBytes), UTF_8)
        val printStr = fansi.Color.Blue(s"Encoded value for `$text` : ${encoded} ")
        println(printStr)
      case "decode" =>
        val decoded =
          new String(java.util.Base64.getDecoder.decode(text), UTF_8)
        val printStr = fansi.Color.Green(s"Decoded value for `$text` : ${decoded} ")
        println(printStr)
      case _ => println(fansi.Underlined.On("Invalid option, use encode/decode for the mode").overlay(fansi.Color.Red))
    }
  }

  def main(args: Array[String]): Unit = ParserForMethods(this).runOrExit(args)
}
