package `fun`.fantasea.bilicommentcrawler.crawler

import `fun`.fantasea.bilicommentcrawler.action.GetComment
import `fun`.fantasea.bilicommentcrawler.action.GetSubComment
import `fun`.fantasea.bilicommentcrawler.persistence.CommentEntity
import `fun`.fantasea.bilicommentcrawler.util.RateLimiter
import `fun`.fantasea.bilicommentcrawler.util.ifNull
import okhttp3.Headers
import org.slf4j.LoggerFactory

class OnePageTask(
    private val headers: Headers,
    private val pn: Int,
    private val ps: Int = 20,
    private val rateLimiter: RateLimiter,
) {
    private val log = LoggerFactory.getLogger(this.javaClass)
    suspend fun execute(): List<CommentEntity> {
        log.info("getting page $pn")
        rateLimiter.acquire()
        val resp = GetComment(
            pn = pn,
            ps = ps,
            oid = 1900845498,
            headers = headers
        ).execute()

        val comments = resp.data
            .ifNull { throw RuntimeException("failed to get comment on page $pn: $resp") }
            .replies

        if (comments.isNullOrEmpty()) {
            log.warn("no comments found for page $pn")
            return emptyList()
        }

        // get root comments of this page
        val rootComments = comments
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
                    following = comment.member.following,
                    isFollowed = comment.member.isFollowed,
                    isContractor = comment.member.isContractor,
                    contractDesc = comment.member.contractDesc,
                    message = comment.content.message,
                    plat = comment.content.plat,
                    device = comment.content.device,
                )
            }
        log.info("got ${rootComments.size} root comments")

        val innerReplies = comments
            .flatMap { comment ->
                if (comment.replies.isNullOrEmpty()) return@flatMap emptyList<CommentEntity>()
                // get sub-comment of this comment
                rateLimiter.acquire()
                val subResp = GetSubComment(
                    headers = headers,
                    root = comment.rpid,
                    oid = comment.oid,
                    pn = 1,
                    ps = 20,
                ).execute()
                val total = subResp.data
                    ?.page
                    ?.count
                    .ifNull { throw RuntimeException("failed to get sub comment count: $subResp") }

                return@flatMap (1..total / ps + 1)
                    .map { pn ->
                        GetSubComment(
                            headers = headers,
                            root = comment.rpid,
                            oid = comment.oid,
                            pn = pn,
                            ps = ps,
                        ).execute()
                            .data
                            ?.replies
                            .ifNull { emptyList() }
                            .also { log.info("got ${it.size} sub comments") }
                    }
                    .flatten()
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
        log.info("got ${innerReplies.size} inner replies")
        return rootComments + innerReplies
    }
}
