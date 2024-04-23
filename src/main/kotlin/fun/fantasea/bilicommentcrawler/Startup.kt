package `fun`.fantasea.bilicommentcrawler

import `fun`.fantasea.bilicommentcrawler.analyzer.CommentAnalyzer
import `fun`.fantasea.bilicommentcrawler.crawler.CommentCrawler
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class Startup(
    private val commentCrawler: CommentCrawler,
    private val commentAnalyzer: CommentAnalyzer,
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        commentCrawler.execute(1900845498)
        // println(commentAnalyzer.getMostCommented().toJsonString(true))
    }
}
