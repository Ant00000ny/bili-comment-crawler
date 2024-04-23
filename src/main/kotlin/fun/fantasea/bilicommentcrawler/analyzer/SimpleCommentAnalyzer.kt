package `fun`.fantasea.bilicommentcrawler.analyzer

import `fun`.fantasea.bilicommentcrawler.persistence.CommentEntity
import `fun`.fantasea.bilicommentcrawler.persistence.CommentRepository
import `fun`.fantasea.bilicommentcrawler.util.ifNull
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class SimpleCommentAnalyzer(
    commentRepository: CommentRepository,
) {
    private val commentIdMap = commentRepository.findAll().associateBy { it.rpid }
    private val log = LoggerFactory.getLogger(this.javaClass)

    /**
     * root rpid -> reply comment rpids
     */
    private val commentReplyMap = commentIdMap
        .entries
        .filter { it.value.root != 0L }
        .groupBy({ it.value.root }, { it.value.rpid })

    fun mostCommented(top: Int): List<CommentEntity> {
        val mostCommentedId = commentReplyMap
            .entries
            .sortedByDescending { it.value.size }
            .take(top)
            .map { it.key }

        return mostCommentedId.map { commentIdMap[it] ?: throw IllegalStateException("No comment found") }
    }

    fun mostCommentedBySelf(top: Int): List<CommentEntity> {
        val mostCommentedId = commentReplyMap
            .entries
            .sortedByDescending {
                val commenterMid = commentIdMap[it.key]
                    .ifNull { throw RuntimeException("No comment found for ${it.key}") }
                    .mid
                it.value.count { replyRpid -> commentIdMap[replyRpid].ifNull { throw IllegalStateException("No comment fount for $replyRpid") }.mid == commenterMid }
            }
            .take(top)
            .map { it.key }

        return mostCommentedId.map { commentIdMap[it] ?: throw IllegalStateException("No comment found") }
    }

    fun showCommentTree(rpid: Long, indent: Int = 0) {
        commentIdMap[rpid]?.let {
            println("${" ".repeat(indent)}${it.uname}(${Instant.ofEpochSecond(it.cTime)}): ${it.message}")
            commentReplyMap[rpid]?.forEach { replyRpid ->
                showCommentTree(replyRpid, indent + 2)
            }
        }
    }
}
