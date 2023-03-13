import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


data class CreditGetUserResponse(
    val id: String,
    val name: String,
    val surname: String,
    val pid: String,
)

fun findByPid(pid: String): CreditGetUserResponse {
    val httpClient = HttpClient.newHttpClient()

    val request = HttpRequest.newBuilder()
        .uri(URI.create("http://credit-users:5000/credit-users/$pid"))
        .GET()
        .build()

    val response = httpClient.send(
        request,
        HttpResponse.BodyHandlers.ofString()
    )
    val typeReference = object : TypeReference<Map<String, Any>>() {}

    val mapResponse = jacksonObjectMapper().readValue(response.body(), typeReference)

    return map(mapResponse) {
        CreditGetUserResponse(
            name = string("name")!!,
            pid = string("pid")!!,
            id = string("id")!!,
            surname = string("surname")!!
        )
    }

}
