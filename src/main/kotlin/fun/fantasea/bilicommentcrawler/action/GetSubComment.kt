package `fun`.fantasea.bilicommentcrawler.action

import com.fasterxml.jackson.module.kotlin.readValue
import `fun`.fantasea.bilicommentcrawler.model.RootResponse
import `fun`.fantasea.bilicommentcrawler.util.client
import `fun`.fantasea.bilicommentcrawler.util.om
import kotlinx.coroutines.delay
import okhttp3.Headers
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Request
import org.slf4j.LoggerFactory
import kotlin.time.Duration.Companion.seconds

class GetSubComment(
    private val headers: Headers,
    private val root: Long,
    private val oid: Long,
    private val pn: Int,
    private val ps: Int = 20,
) {
    private val log = LoggerFactory.getLogger(this.javaClass)
    private val path = "https://api.bilibili.com/x/v2/reply/reply"
    suspend fun execute(): RootResponse {
        val url = path.toHttpUrl()
            .newBuilder()
            .addQueryParameter("type", "1")
            .addQueryParameter("oid", oid.toString())
            .addQueryParameter("root", root.toString())
            .addQueryParameter("ps", "20")
            // 1-base
            .addQueryParameter("pn", pn.toString())
            .build()

        val request = Request.Builder()
            .url(url)
            .get()
            .headers(headers)
            .build()

        val respJson = client.newCall(request)
            .execute()
            .body
            .use { it.string() }

        return respJson
            .let { om.readValue<RootResponse>(it) }
            .also {
                if (it.code == -412) {
                    log.info("-412 error: ${it.message}, retrying after 60 secs...")
                    delay(60.seconds)
                    return this.execute()
                }
            }
    }
}
