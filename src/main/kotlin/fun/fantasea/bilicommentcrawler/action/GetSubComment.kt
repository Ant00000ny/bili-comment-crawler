package `fun`.fantasea.bilicommentcrawler.action

import com.fasterxml.jackson.module.kotlin.readValue
import `fun`.fantasea.bilicommentcrawler.model.RootResponse
import `fun`.fantasea.bilicommentcrawler.util.client
import `fun`.fantasea.bilicommentcrawler.util.om
import okhttp3.Headers
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Request

class GetSubComment(
    private val headers: Headers,
    private val root: Long,
    private val oid: Long,
    private val pn: Int,
    private val ps: Int = 20,
) {
    private val path = "https://api.bilibili.com/x/v2/reply/reply"
    fun execute(): RootResponse {
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
    }
}
