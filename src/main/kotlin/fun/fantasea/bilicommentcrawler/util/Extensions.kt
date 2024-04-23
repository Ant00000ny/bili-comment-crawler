package `fun`.fantasea.bilicommentcrawler.util


fun Any.toJsonString(pretty: Boolean = false): String = if (pretty) {
    om.writerWithDefaultPrettyPrinter().writeValueAsString(this)
} else {
    om.writeValueAsString(this)
}


fun <T : Any> T?.ifNull(defaultValue: () -> T): T = this ?: defaultValue()
