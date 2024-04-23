package `fun`.fantasea.bilicommentcrawler.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 根对象
 */
data class RootResponse(
    /**
     * 返回值
     */
    @JsonProperty("code")
    val code: Int,
    /**
     * 错误信息
     */
    @JsonProperty("message")
    val message: String,
    /**
     * 1
     */
    @JsonProperty("ttl")
    val ttl: Int,
    /**
     * 数据本体
     */
    @JsonProperty("data")
    val data: Data?,
)

/**
 * 数据本体
 */
data class Data(
    /**
     * 页信息
     */
    @JsonProperty("page")
    val page: Page,
    /**
     * 评论区显示控制
     */
    @JsonProperty("config")
    val config: Config,
    /**
     * 评论列表
     */
    @JsonProperty("replies")
    val replies: List<Comment>?,
    /**
     * 热评列表
     */
    @JsonProperty("hots")
    val hots: List<Comment>?,
    /**
     * 置顶评论
     */
    @JsonProperty("upper")
    val upper: Upper,
    /**
     * (?)
     */
    @JsonProperty("top")
    val top: Any?,
    /**
     * 评论区公告信息
     */
    @JsonProperty("notice")
    val notice: Notice?,
    /**
     * 投票评论?
     */
    @JsonProperty("vote")
    val vote: Int,
    /**
     * (?)
     */
    @JsonProperty("blacklist")
    val blacklist: Int,
    /**
     * (?)
     */
    @JsonProperty("assist")
    val assist: Int,
    /**
     * 评论区类型id
     */
    @JsonProperty("mode")
    val mode: Int,
    /**
     * 评论区支持的类型id
     */
    @JsonProperty("support_mode")
    val supportMode: List<Int>,
    /**
     * 折叠相关信息
     */
    @JsonProperty("folder")
    val folder: Folder,
    /**
     * (?)
     */
    @JsonProperty("lottery_card")
    val lotteryCard: Any?,
    /**
     * 显示bvid?
     */
    @JsonProperty("show_bvid")
    val showBvid: Boolean,
    /**
     * 评论区输入属性
     */
    @JsonProperty("control")
    val control: Control,
)

/**
 * 页信息
 */
data class Page(
    /**
     * 当前页码
     */
    @JsonProperty("num")
    val num: Int,
    /**
     * 每页项数
     */
    @JsonProperty("size")
    val size: Int,
    /**
     * 根评论条数
     */
    @JsonProperty("count")
    val count: Int,
    /**
     * 总计评论条数
     */
    @JsonProperty("acount")
    val acount: Int,
)

/**
 * 评论区显示控制
 */
data class Config(
    /**
     * 是否显示管理置顶
     */
    @JsonProperty("showadmin")
    val showAdmin: Int,
    /**
     * (?)
     */
    @JsonProperty("showentry")
    val showEntry: Int,
    /**
     * 是否显示楼层号
     */
    @JsonProperty("showfloor")
    val showFloor: Int,
    /**
     * 是否显示话题
     */
    @JsonProperty("showtopic")
    val showTopic: Int,
    /**
     * 是否显示“UP 觉得很赞”标志
     */
    @JsonProperty("show_up_flag")
    val showUpFlag: Boolean,
    /**
     * 是否只读评论区
     */
    @JsonProperty("read_only")
    val readOnly: Boolean,
    /**
     * 是否显示删除记录
     */
    @JsonProperty("show_del_log")
    val showDelLog: Boolean,
)

/**
 * 置顶评论
 */
data class Upper(
    /**
     * UP 主 mid
     */
    @JsonProperty("mid")
    val mid: Int,
    /**
     * 置顶条目
     */
    @JsonProperty("top")
    val top: Comment?,
    /**
     * 投票评论？
     */
    @JsonProperty("vote")
    val vote: Any?,
)

/**
 * 评论区公告信息
 */
data class Notice(
    /**
     * 公告正文
     */
    @JsonProperty("content")
    val content: String,
    /**
     * 公告 id
     */
    @JsonProperty("id")
    val id: Int,
    /**
     * 公告页面链接 url
     */
    @JsonProperty("link")
    val link: String,
    /**
     * 公告标题
     */
    @JsonProperty("title")
    val title: String,
)

/**
 * 折叠相关信息
 */
data class Folder(
    /**
     * 评论区是否存在折叠评论
     */
    @JsonProperty("has_folded")
    val hasFolded: Boolean,
    /**
     * 是否折叠?
     */
    @JsonProperty("is_folded")
    val isFolded: Boolean,
    /**
     * 相关规则页面 url
     */
    @JsonProperty("rule")
    val rule: String,
)

/**
 * 评论区输入属性
 */
data class Control(
    /**
     * 是否禁止新增评论
     */
    @JsonProperty("input_disable")
    val inputDisable: Boolean,
    /**
     * 评论框文字
     */
    @JsonProperty("root_input_text")
    val rootInputText: String,
    /**
     * 评论框文字
     */
    @JsonProperty("child_input_text")
    val childInputText: String,
    /**
     * 空评论区文字
     */
    @JsonProperty("bg_text")
    val bgText: String,
    /**
     * 评论是否筛选后可见
     */
    @JsonProperty("web_selection")
    val webSelection: Boolean,
    /**
     * 答题页面链接文字
     */
    @JsonProperty("answer_guide_text")
    val answerGuideText: String,
    /**
     * 答题页面图标 url
     */
    @JsonProperty("answer_guide_icon_url")
    val answerGuideIconUrl: String,
    /**
     * 答题页面 ios url
     */
    @JsonProperty("answer_guide_ios_url")
    val answerGuideIosUrl: String,
    /**
     * 答题页面安卓 url
     */
    @JsonProperty("answer_guide_android_url")
    val answerGuideAndroidUrl: String,
)

/**
 * 评论条目
 */
data class Comment(
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
     * 评论区类型代码
     */
    @JsonProperty("type")
    val type: Int,
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
     * (?)
     */
    @JsonProperty("state")
    val state: Int,
    /**
     * 是否具有粉丝标签
     */
    @JsonProperty("fansgrade")
    val fansGrade: Int,
    /**
     * 某属性位？
     */
    @JsonProperty("attr")
    val attr: Long,
    /**
     * 评论发送时间
     */
    @JsonProperty("ctime")
    val cTime: Long,
    /**
     * 评论rpid
     */
    @JsonProperty("rpid_str")
    val rpidStr: String,
    /**
     * 根评论rpid
     */
    @JsonProperty("root_str")
    val rootStr: String,
    /**
     * 回复父评论rpid
     */
    @JsonProperty("parent_str")
    val parentStr: String,
    /**
     * 评论获赞数
     */
    @JsonProperty("like")
    val like: Int,
    /**
     * 当前用户操作状态
     */
    @JsonProperty("action")
    val action: Int,
    /**
     * 评论发送者信息
     */
    @JsonProperty("member")
    val member: Member,
    /**
     * 评论信息
     */
    @JsonProperty("content")
    val content: Content,
    /**
     * 评论回复条目预览
     */
    @JsonProperty("replies")
    val replies: List<Comment>?,
    /**
     * (?)
     */
    @JsonProperty("assist")
    val assist: Int,
    /**
     * 折叠信息
     */
    @JsonProperty("folder")
    val folder: Folder,
    // /**
    //  * 评论 UP 主操作信息
    //  */
    // @JsonProperty("up_action")
    // val upAction: UpAction,
    /**
     * (?)
     */
    @JsonProperty("show_follow")
    val showFollow: Boolean,
    /**
     * 评论是否被隐藏
     */
    @JsonProperty("invisible")
    val invisible: Boolean,
    // /**
    //  * 右上角卡片标签信息
    //  */
    // @JsonProperty("card_label")
    // val cardLabel: CardLabel,
    // /**
    //  * 评论提示文案信息
    //  */
    // @JsonProperty("reply_control")
    // val replyControl: ReplyControl,
)

/**
 * 评论发送者信息
 */
data class Member(
    /**
     * 发送者 mid
     */
    @JsonProperty("mid")
    val mid: String,
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
     * 发送者等级
     */
    @JsonProperty("level_info")
    val levelInfo: LevelInfo,
    /**
     * 发送者头像框信息
     */
    @JsonProperty("pendant")
    val pendant: Pendant,
    /**
     * 发送者勋章信息
     */
    @JsonProperty("nameplate")
    val nameplate: Nameplate,
    /**
     * 发送者认证信息
     */
    @JsonProperty("official_verify")
    val officialVerify: OfficialVerify,
    /**
     * 发送者会员信息
     */
    @JsonProperty("vip")
    val vip: Vip,
    /**
     * 发送者粉丝标签
     */
    @JsonProperty("fans_detail")
    val fansDetail: FansDetail?,
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
     * 发送者评论条目装扮信息
     */
    @JsonProperty("user_sailing")
    val userSailing: UserSailing?,
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
)

/**
 * 发送者等级
 */
data class LevelInfo(
    /**
     * 用户等级
     */
    @JsonProperty("current_level")
    val currentLevel: Int,
)

/**
 * 发送者头像框信息
 */
data class Pendant(
    /**
     * 头像框 id
     */
    @JsonProperty("pid")
    val pid: Int,
    /**
     * 头像框名称
     */
    @JsonProperty("name")
    val name: String,
    /**
     * 头像框图片 url
     */
    @JsonProperty("image")
    val image: String,
    /**
     * 0
     */
    @JsonProperty("expire")
    val expire: Int,
    /**
     * 头像框图片 url
     */
    @JsonProperty("image_enhance")
    val imageEnhance: String,
    /**
     * (?)
     */
    @JsonProperty("image_enhance_frame")
    val imageEnhanceFrame: String,
)

/**
 * 发送者勋章信息
 */
data class Nameplate(
    /**
     * 勋章 id
     */
    @JsonProperty("nid")
    val nid: Int,
    /**
     * 勋章名称
     */
    @JsonProperty("name")
    val name: String,
    /**
     * 挂件图片 url 正常
     */
    @JsonProperty("image")
    val image: String,
    /**
     * 勋章图片 url 小
     */
    @JsonProperty("image_small")
    val imageSmall: String,
    /**
     * 勋章等级
     */
    @JsonProperty("level")
    val level: String,
    /**
     * 勋章条件
     */
    @JsonProperty("condition")
    val condition: String,
)

/**
 * 发送者认证信息
 */
data class OfficialVerify(
    /**
     * 是否认证
     */
    @JsonProperty("type")
    val type: Int,
    /**
     * 认证信息
     */
    @JsonProperty("desc")
    val desc: String,
)

/**
 * 发送者会员信息
 */
data class Vip(
    /**
     * 大会员类型
     */
    @JsonProperty("vipType")
    val vipType: Int,
    /**
     * 大会员到期时间
     */
    @JsonProperty("vipDueDate")
    val vipDueDate: Long,
    /**
     * (?)
     */
    @JsonProperty("dueRemark")
    val dueRemark: String,
    /**
     * (?)
     */
    @JsonProperty("accessStatus")
    val accessStatus: Int,
    /**
     * 大会员状态
     */
    @JsonProperty("vipStatus")
    val vipStatus: Int,
    /**
     * (?)
     */
    @JsonProperty("vipStatusWarn")
    val vipStatusWarn: String,
    /**
     * 会员样式 id
     */
    @JsonProperty("theme_type")
    val themeType: Int,
    /**
     * 会员铭牌样式
     */
    @JsonProperty("label")
    val label: Label,
    /**
     * (?)
     */
    @JsonProperty("avatar_subscript")
    val avatarSubscript: Int,
    /**
     * (?)
     */
    @JsonProperty("avatar_subscript_url")
    val avatarSubscriptUrl: String?,
    /**
     * 昵称颜色
     */
    @JsonProperty("nickname_color")
    val nicknameColor: String,
)

/**
 * 会员铭牌样式
 */
data class Label(
    /**
     * (?)
     */
    @JsonProperty("path")
    val path: String,
    /**
     * 会员类型文案
     */
    @JsonProperty("text")
    val text: String,
    /**
     * 会员类型
     */
    @JsonProperty("label_theme")
    val labelTheme: String,
    /**
     * 文字颜色?
     */
    @JsonProperty("text_color")
    val textColor: String,
    /**
     * (?)
     */
    @JsonProperty("bg_style")
    val bgStyle: Int,
    /**
     * 背景颜色?
     */
    @JsonProperty("bg_color")
    val bgColor: String,
    /**
     * 描边颜色?
     */
    @JsonProperty("border_color")
    val borderColor: String,
)

/**
 * 发送者粉丝标签
 */
data class FansDetail(
    /**
     * 用户 mid
     */
    @JsonProperty("uid")
    val uid: Int,
    /**
     * 粉丝标签 id
     */
    @JsonProperty("medal_id")
    val medalId: Int,
    /**
     * 粉丝标签名
     */
    @JsonProperty("medal_name")
    val medalName: String,
    /**
     * (?)
     */
    @JsonProperty("score")
    val score: Int,
    /**
     * 当前标签等级
     */
    @JsonProperty("level")
    val level: Int,
    /**
     * (?)
     */
    @JsonProperty("intimacy")
    val intimacy: Int,
    /**
     * (?)
     */
    @JsonProperty("master_status")
    val masterStatus: Int,
    /**
     * (?)
     */
    @JsonProperty("is_receive")
    val isReceive: Int,
)

/**
 * 发送者评论条目装扮信息
 */
data class UserSailing(
    /**
     * 头像框信息
     */
    @JsonProperty("pendant")
    val pendant: Pendant?,
    /**
     * 评论卡片装扮
     */
    @JsonProperty("cardbg")
    val cardBg: CardBg?,
    /**
     * (?)
     */
    @JsonProperty("cardbg_with_focus")
    val cardBgWithFocus: Any?,
)

/**
 * 评论卡片装扮
 */
data class CardBg(
    /**
     * 评论条目装扮 id
     */
    @JsonProperty("id")
    val id: Long,
    /**
     * 评论条目装扮名称
     */
    @JsonProperty("name")
    val name: String,
    /**
     * 评论条目装扮图片 url
     */
    @JsonProperty("image")
    val image: String,
    /**
     * 评论条目装扮商城页面 url
     */
    @JsonProperty("jump_url")
    val jumpUrl: String,
    @JsonProperty("fan")
    val
    /**
     * 粉丝专属信息
     */

    fan: Fan,
    /**
     * 装扮类型
     */
    @JsonProperty("type")
    val type: String,
)

/**
 * 粉丝专属信息
 */
data class Fan(
    /**
     * 是否为粉丝专属装扮
     */
    @JsonProperty("is_fan")
    val isFan: Int,
    /**
     * 粉丝专属编号
     */
    @JsonProperty("number")
    val number: Int,
    /**
     * 数字颜色
     */
    @JsonProperty("color")
    val color: String,
    /**
     * 装扮名称
     */
    @JsonProperty("name")
    val name: String,
    /**
     * 粉丝专属编号
     */
    @JsonProperty("num_desc")
    val numDesc: String,
)

/**
 * 评论信息
 */
data class Content(
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
    /**
     * at 到的用户信息
     */
    @JsonProperty("members")
    val members: List<Member>,
    /**
     * 需要渲染的表情转义
     */
    @JsonProperty("emote")
    val emote: Map<String, Emote>?,
    /**
     * 需要高亮的超链转义
     */
    @JsonProperty("jump_url")
    val jumpUrl: Map<String, JumpUrl>,
    /**
     * 收起最大行数
     */
    @JsonProperty("max_line")
    val maxLine: Int,
    /**
     * 评论图片数组
     */
    @JsonProperty("pictures")
    val pictures: List<Picture>?,
)

/**
 * 表情转义符信息
 */
data class Emote(
    /**
     * 表情 id
     */
    @JsonProperty("id")
    val id: Int,
    /**
     * 表情包 id
     */
    @JsonProperty("package_id")
    val packageId: Int,
    /**
     * 0
     */
    @JsonProperty("state")
    val state: Int,
    /**
     * 表情类型
     */
    @JsonProperty("type")
    val type: Int,
    /**
     * (?)
     */
    @JsonProperty("attr")
    val attr: Int,
    /**
     * 表情转义符
     */
    @JsonProperty("text")
    val text: String,
    /**
     * 表情图片 url
     */
    @JsonProperty("url")
    val url: String,
    /**
     * 属性信息
     */
    @JsonProperty("meta")
    val meta: Meta,
    /**
     * 表情创建时间
     */
    @JsonProperty("mtime")
    val mTime: Long,
    /**
     * 表情名称
     */
    @JsonProperty("jump_title")
    val jumpTitle: String,
)

/**
 * 表情属性信息
 */
data class Meta(
    /**
     * 表情尺寸信息
     */
    @JsonProperty("size")
    val size: Int,
    /**
     * 简写名
     */
    @JsonProperty("alias")
    val alias: String?,
)

/**
 * 超链转义信息
 */
data class JumpUrl(
    /**
     * 标题
     */
    @JsonProperty("title")
    val title: String,
    /**
     * 图标 url
     */
    @JsonProperty("state")
    val state: Int,
    /**
     * (?)
     */
    @JsonProperty("prefixIcon")
    val prefixIcon: String?,
    /**
     * (?)
     */
    @JsonProperty("appUrlSchema")
    val appUrlSchema: String?,
    /**
     * (?)
     */
    @JsonProperty("appName")
    val appName: String?,
    /**
     * (?)
     */
    @JsonProperty("appPackageName")
    val appPackageName: String?,
    /**
     * 上报 id
     */
    @JsonProperty("clickReport")
    val clickReport: String?,
)

/**
 * 评论图片信息
 */
data class Picture(
    /**
     * 图片地址
     */
    @JsonProperty("img_src")
    val imgSrc: String,
    /**
     * 图片宽度
     */
    @JsonProperty("img_width")
    val imgWidth: Int,
    /**
     * 图片高度
     */
    @JsonProperty("img_height")
    val imgHeight: Int,
    /**
     * 图片大小
     */
    @JsonProperty("img_size")
    val imgSize: Int,
)
