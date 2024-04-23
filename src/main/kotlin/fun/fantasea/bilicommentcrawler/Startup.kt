package `fun`.fantasea.bilicommentcrawler

import `fun`.fantasea.bilicommentcrawler.analyzer.SimpleCommentAnalyzer
import `fun`.fantasea.bilicommentcrawler.crawler.CommentCrawler
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class Startup(
    private val commentCrawler: CommentCrawler,
    private val commentAnalyzer: SimpleCommentAnalyzer,
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        // commentCrawler.execute(1900845498)
        val comment = commentAnalyzer.mostCommentedBySelf(10)
        comment.forEach { comment ->
            commentAnalyzer.showCommentTree(comment.rpid)
            println("\n\n---\n\n")
        }
    }
}
