package `fun`.fantasea.bilicommentcrawler.action

import com.fasterxml.jackson.module.kotlin.readValue
import com.mikaa404.browser.ChromeBrowser
import `fun`.fantasea.bilicommentcrawler.model.RootResponse
import `fun`.fantasea.bilicommentcrawler.util.client
import `fun`.fantasea.bilicommentcrawler.util.om
import okhttp3.Headers
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Request

class GetComment(
    private val pn: Int,
    private val ps: Int = 20,
    private val oid: Long,
) {
    private val path = "https://api.bilibili.com/x/v2/reply"
    fun execute(): RootResponse {
        check(pn > 0)
        check(ps in 1..20)

        val url = path.toHttpUrl()
            .newBuilder()
            .addQueryParameter("type", "1")
            .addQueryParameter("oid", oid.toString())
            .addQueryParameter("sort", "0")
            .addQueryParameter("nohot", "1")
            // 1-base
            .addQueryParameter("pn", pn.toString())
            .addQueryParameter("ps", "20")
            .build()

        val request = Request.Builder()
            .url(url)
            .get()
            .headers(getHeaders())
            .build()

        val respJson = client.newCall(request)
            .execute()
            .body
            .use { it.string() }

        return respJson
            .let { om.readValue<RootResponse>(it) }
    }

    private fun getHeaders(): Headers {
        val cookies = ChromeBrowser.getInstance()
            .allCookies
            .filter { it.hostKey.contains("bilibili") }

        return Headers.Builder()
            .apply {
                cookies.forEach {
                    add(it.name, it.value)
                }

                add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
            }.build()
    }
}
