package `fun`.fantasea.bilicommentcrawler.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.time.Duration
import java.time.Instant

class RateLimiter(private val minDuration: Duration) {
    private val mutex: Mutex = Mutex()

    @Volatile
    private var last: Instant = Instant.EPOCH

    suspend fun acquire() = mutex.withLock {
        val now = Instant.now()
        val duration = Duration.between(last, now)
        if (duration < minDuration) {
            delay((minDuration - duration).toMillis())
        }
        last = now
    }
}
