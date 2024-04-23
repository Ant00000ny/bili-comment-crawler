package `fun`.fantasea.bilicommentcrawler.util


fun <T : CharSequence> T.toJsonString(pretty: Boolean): String = if (pretty) {
    om.writerWithDefaultPrettyPrinter().writeValueAsString(this)
} else {
    om.writeValueAsString(this)
}


fun <T : Any> T?.ifNull(defaultValue: () -> T): T = this ?: defaultValue()
