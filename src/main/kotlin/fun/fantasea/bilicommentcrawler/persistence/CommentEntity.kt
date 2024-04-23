package `fun`.fantasea.bilicommentcrawler.persistence

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "t_bili_comment")
data class CommentEntity(
    /**
     * 评论 rpid
     */
    @JsonProperty("rpid")
    val rpid: Long,
    /**
     * 评论区对象 id
     */
    @JsonProperty("oid")
    val oid: Long,
    /**
     * 发送者 mid
     */
    @JsonProperty("mid")
    val mid: Long,
    /**
     * 根评论 rpid
     */
    @JsonProperty("root")
    val root: Long,
    /**
     * 回复父评论 rpid
     */
    @JsonProperty("parent")
    val parent: Long,
    /**
     * 回复对方 rpid
     */
    @JsonProperty("dialog")
    val dialog: Long,
    /**
     * 二级评论条数
     */
    @JsonProperty("count")
    val count: Int,
    /**
     * 回复评论条数
     */
    @JsonProperty("rcount")
    val rcount: Int,
    /**
     * 评论楼层号
     */
    @JsonProperty("floor")
    val floor: Int,
    /**
     * 是否具有粉丝标签
     */
    @JsonProperty("fansgrade")
    val fansGrade: Int,
    /**
     * 评论发送时间
     */
    @JsonProperty("ctime")
    val cTime: Long,
    /**
     * 评论获赞数
     */
    @JsonProperty("like")
    val like: Int,

    // member start -----------------------------------------------------------------

    /**
     * 发送者昵称
     */
    @JsonProperty("uname")
    val uname: String,
    /**
     * 发送者性别
     */
    @JsonProperty("sex")
    val sex: String,
    /**
     * 发送者签名
     */
    @JsonProperty("sign")
    val sign: String,
    /**
     * 发送者头像 url
     */
    @JsonProperty("avatar")
    val avatar: String,
    /**
     * (?)
     */
    @JsonProperty("rank")
    val rank: String,
    /**
     * (?)
     */
    @JsonProperty("DisplayRank")
    val displayRank: String?,
    /**
     * 是否关注该用户
     */
    @JsonProperty("following")
    val following: Int,
    /**
     * 是否被该用户关注
     */
    @JsonProperty("is_followed")
    val isFollowed: Int,
    /**
     * 是否为合作用户？
     */
    @JsonProperty("is_contractor")
    val isContractor: Boolean,
    /**
     * 合作用户说明？
     */
    @JsonProperty("contract_desc")
    val contractDesc: String,

    // member end -----------------------------------------------------------------

    // content start -----------------------------------------------------------------

    /**
     * 评论内容
     */
    @JsonProperty("message")
    val message: String,
    /**
     * 评论发送端
     */
    @JsonProperty("plat")
    val plat: Int,
    /**
     * 评论发送平台设备
     */
    @JsonProperty("device")
    val device: String?,
    // content end -----------------------------------------------------------------
) {
    @Id
    var id: String? = null
}
