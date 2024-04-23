package `fun`.fantasea.bilicommentcrawler.config

import com.mikaa404.browser.ChromeBrowser
import okhttp3.Headers
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun headers(): Headers {
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
