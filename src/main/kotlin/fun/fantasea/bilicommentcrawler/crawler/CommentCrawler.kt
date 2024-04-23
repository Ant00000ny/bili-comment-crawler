package `fun`.fantasea.bilicommentcrawler.crawler

import `fun`.fantasea.bilicommentcrawler.action.GetComment
import `fun`.fantasea.bilicommentcrawler.persistence.CommentRepository
import `fun`.fantasea.bilicommentcrawler.util.ifNull
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import kotlin.time.Duration.Companion.seconds

@Component
class CommentCrawler(
    private val commentRepository: CommentRepository,
) : ApplicationRunner {
    private val log = LoggerFactory.getLogger(this.javaClass)
    private val ps = 20

    override fun run(args: ApplicationArguments?) = runBlocking {
        // total num of root comments
        val total = GetComment(
            pn = 1,
            ps = ps,
            oid = 1900845498
        ).execute()
            .data
            .ifNull { throw RuntimeException("failed to get comment") }
            .page
            .count

        delay(0.1.seconds)

        // each page
        (1..total / ps + 1)
            .map { pn ->
                delay(0.5.seconds)
                OnePageTask(pn = pn, ps = ps).execute()
            }
            .flatten()
            .chunked(100)
            .forEach { chunk -> commentRepository.saveAll(chunk) }
    }
}
