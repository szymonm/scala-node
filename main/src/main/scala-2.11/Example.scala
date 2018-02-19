package example

import fr.hmil.roshttp.HttpRequest
import fr.hmil.roshttp.response.SimpleHttpResponse
import monix.execution.Scheduler.Implicits.global

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import scala.util.{Failure, Success}

@JSExportTopLevel("example")
object Example {

	def helloWorld() = {
    println("Hello World!")
    "asldkjf"
  }

  def main(args: Array[String]): Unit = {
    println(args.mkString("\n"))
    main2()
  }

  @JSExport
  def main1(args: Array[String]): Unit = {
    helloWorld()
    val request = HttpRequest("https://schema.org/WebPage")

    request.send().onComplete({
                                case res:Success[SimpleHttpResponse] => println(res.get.body)
                                case e: Failure[SimpleHttpResponse] => println("Houston, we got a problem!")
                              })
    helloWorld()
  }

  @JSExport
  def main2(): Unit = {
    helloWorld()
    val request = HttpRequest("https://schema.org/WebPage")

    request.send().onComplete({
      case res:Success[SimpleHttpResponse] => println(res.get.body)
      case e: Failure[SimpleHttpResponse] => println("Houston, we got a problem!")
    })
    helloWorld()
  }
}

