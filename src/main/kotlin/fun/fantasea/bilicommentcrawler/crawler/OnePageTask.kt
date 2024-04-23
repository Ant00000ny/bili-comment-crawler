package `fun`.fantasea.bilicommentcrawler.crawler

import `fun`.fantasea.bilicommentcrawler.action.GetComment
import `fun`.fantasea.bilicommentcrawler.persistence.CommentEntity
import `fun`.fantasea.bilicommentcrawler.util.ifNull
import org.slf4j.LoggerFactory

class OnePageTask(
    private val pn: Int,
    private val ps: Int = 20,
) {
    private val log = LoggerFactory.getLogger(this.javaClass)
    fun execute(): List<CommentEntity> {
        log.info("getting page $pn")
        val resp = GetComment(
            pn = pn,
            ps = ps,
            oid = 1900845498
        ).execute()


        log.info("saving comments of page ${resp.data?.page?.num}(${resp.data?.page?.num?.times(ps)}), total ${resp.data?.page?.count}")

        val comments = resp.data
            .ifNull { throw RuntimeException("failed to get comment on page $pn") }
            .replies

        if (comments.isNullOrEmpty()) {
            log.warn("no comments found for page $pn")
            return emptyList()
        }

        return comments
            .map { comment ->
                CommentEntity(
                    rpid = comment.rpid,
                    oid = comment.oid,
                    mid = comment.mid,
                    root = comment.root,
                    parent = comment.parent,
                    dialog = comment.dialog,
                    count = comment.count,
                    rcount = comment.rcount,
                    floor = comment.floor,
                    fansGrade = comment.fansGrade,
                    cTime = comment.cTime,
                    like = comment.like,
                    uname = comment.member.uname,
                    sex = comment.member.sex,
                    sign = comment.member.sign,
                    avatar = comment.member.avatar,
                    rank = comment.member.rank,
                    displayRank = comment.member.displayRank,
                    following = comment.member.following,
                    isFollowed = comment.member.isFollowed,
                    isContractor = comment.member.isContractor,
                    contractDesc = comment.member.contractDesc,
                    message = comment.content.message,
                    plat = comment.content.plat,
                    device = comment.content.device,
                )
            }
    }
}