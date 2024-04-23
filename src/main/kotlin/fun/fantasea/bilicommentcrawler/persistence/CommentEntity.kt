package `fun`.fantasea.bilicommentcrawler.persistence

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
@Table(name = "t_bili_comment")
data class CommentEntity(
    /**
     * 评论 rpid
     */
    @JsonProperty("rpid")
    @Column(name = "rpid", columnDefinition = "BIGINT")
    val rpid: Long,
    /**
     * 评论区对象 id
     */
    @JsonProperty("oid")
    @Column(name = "oid", columnDefinition = "BIGINT")
    val oid: Long,
    /**
     * 发送者 mid
     */
    @JsonProperty("mid")
    @Column(name = "mid", columnDefinition = "BIGINT")
    val mid: Long,
    /**
     * 根评论 rpid
     */
    @JsonProperty("root")
    @Column(name = "root", columnDefinition = "BIGINT")
    val root: Long,
    /**
     * 回复父评论 rpid
     */
    @JsonProperty("parent")
    @Column(name = "parent", columnDefinition = "BIGINT")
    val parent: Long,
    /**
     * 回复对方 rpid
     */
    @JsonProperty("dialog")
    @Column(name = "dialog", columnDefinition = "BIGINT")
    val dialog: Long,
    /**
     * 二级评论条数
     */
    @JsonProperty("count")
    @Column(name = "count", columnDefinition = "INT")
    val count: Int,
    /**
     * 回复评论条数
     */
    @JsonProperty("rcount")
    @Column(name = "rcount", columnDefinition = "INT")
    val rcount: Int,
    /**
     * 评论楼层号
     */
    @JsonProperty("floor")
    @Column(name = "floor", columnDefinition = "INT")
    val floor: Int,
    /**
     * 是否具有粉丝标签
     */
    @JsonProperty("fansgrade")
    @Column(name = "fansgrade", columnDefinition = "INT")
    val fansGrade: Int,
    /**
     * 评论发送时间
     */
    @JsonProperty("ctime")
    @Column(name = "ctime", columnDefinition = "BIGINT")
    val cTime: Long,
    /**
     * 评论获赞数
     */
    @JsonProperty("like")
    @Column(name = "`like`", columnDefinition = "INT")
    val like: Int,

    // member start -----------------------------------------------------------------

    /**
     * 发送者昵称
     */
    @Column(name = "uname", columnDefinition = "VARCHAR(255)")
    @JsonProperty("uname")
    val uname: String,
    /**
     * 发送者性别
     */
    @Column(name = "sex", columnDefinition = "VARCHAR(255)")
    @JsonProperty("sex")
    val sex: String,
    /**
     * 发送者签名
     */
    @Column(name = "sign", columnDefinition = "VARCHAR(255)")
    @JsonProperty("sign")
    val sign: String,
    /**
     * 发送者头像 url
     */
    @Column(name = "avatar", columnDefinition = "VARCHAR(255)")
    @JsonProperty("avatar")
    val avatar: String,
    /**
     * 是否关注该用户
     */
    @Column(name = "following", columnDefinition = "INT")
    @JsonProperty("following")
    val following: Int,
    /**
     * 是否被该用户关注
     */
    @Column(name = "is_followed", columnDefinition = "INT")
    @JsonProperty("is_followed")
    val isFollowed: Int,
    /**
     * 是否为合作用户？
     */
    @Column(name = "is_contractor", columnDefinition = "BOOLEAN")
    @JsonProperty("is_contractor")
    val isContractor: Boolean,
    /**
     * 合作用户说明？
     */
    @Column(name = "contract_desc", columnDefinition = "VARCHAR(255)")
    @JsonProperty("contract_desc")
    val contractDesc: String?,

    // member end -----------------------------------------------------------------

    // content start -----------------------------------------------------------------

    /**
     * 评论内容
     */
    @Column(name = "message", columnDefinition = "TEXT")
    @JsonProperty("message")
    val message: String,
    /**
     * 评论发送端
     */
    @Column(name = "plat", columnDefinition = "INT")
    @JsonProperty("plat")
    val plat: Int,
    /**
     * 评论发送平台设备
     */
    @Column(name = "device", columnDefinition = "VARCHAR(255)")
    @JsonProperty("device")
    val device: String?,
    // content end -----------------------------------------------------------------
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
}
