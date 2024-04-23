package `fun`.fantasea.bilicommentcrawler.analyzer

import `fun`.fantasea.bilicommentcrawler.persistence.CommentEntity
import `fun`.fantasea.bilicommentcrawler.persistence.CommentRepository
import org.springframework.stereotype.Component

@Component
class CommentAnalyzer(
    private val commentRepository: CommentRepository,
) {
    fun getMostCommented(): Pair<CommentEntity, List<CommentEntity>> {
        val comments = commentRepository.findAll()
            .associateBy { it.rpid }

        val commentGroups = comments.values
            .filter { it.parent != 0L }
            .groupBy { it.parent }

        val mostCommented = commentGroups
            .maxBy { it.value.size }
            .let { comments[it.key]!! to it.value }

        return mostCommented
    }
}
