package `fun`.fantasea.bilicommentcrawler.crawler

import `fun`.fantasea.bilicommentcrawler.action.GetComment
import `fun`.fantasea.bilicommentcrawler.persistence.CommentRepository
import `fun`.fantasea.bilicommentcrawler.util.ifNull
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Headers
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@Component
class CommentCrawler(
    private val commentRepository: CommentRepository,
    private val headers: Headers,
) {
    private val log = LoggerFactory.getLogger(this.javaClass)
    private val ps = 20

    fun execute(oid: Long) = runBlocking {
        // total num of root comments
        val resp = GetComment(
            pn = 1,
            ps = ps,
            oid = oid,
            headers = headers
        ).execute()

        val totalRootCount = resp.data
            .ifNull { throw RuntimeException("failed to get comment") }
            .page
            .count

        log.info("saving comments of page ${resp.data?.page?.num}, total ${resp.data?.page?.count}")

        delay(0.1.seconds)

        // each page
        (1..totalRootCount / ps + 1)
            .forEach { pn ->
                delay((200 ..800).random().milliseconds)
                val comments = OnePageTask(pn = pn, ps = ps, headers = headers).execute()
                launch {
                    commentRepository.saveAll(comments)
                    log.info("saved ${comments.size} comments of page $pn, total saved ${commentRepository.count()}")
                }
            }
    }
}
