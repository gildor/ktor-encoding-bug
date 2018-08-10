import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.routing.contentType
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.Jetty

fun main(args: Array<String>) {
    embeddedServer(
            Jetty,
            port = 8081,
            module = Application::module
    ).start(true)
}

fun Application.module() {
    routing {
        post("/") {
            val requestBody = call.receiveText()
            val expectedBody = expectedBody()
            if (requestBody == expectedBody) {
                call.respond("Body is valid")
            } else {
                call.respond(
                        HttpStatusCode.InternalServerError,
                        """
                        Request body is incorrect:
                        $requestBody

                        expected:
                        $expectedBody
                        """.trimIndent()
                )
            }
        }
    }
}

private fun Application.expectedBody() = javaClass
        .classLoader
        .getResourceAsStream("body.json")
        // Read expected body with UTF-8 encoding
        .reader(charset = Charsets.UTF_8)
        .readText()