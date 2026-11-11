package ortega.adolfo.mipokedex_ortegaadolfo.model.domain

import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit

@Serializable
data class CompanionData(
    val pokemon: Pokemon? = null,
    val selectedAtMillis: Long = 0L
) {
    fun daysAsCompanion(today: LocalDate = LocalDate.now()): Long {
        if (pokemon == null) return 0L
        val start = Instant.ofEpochMilli(selectedAtMillis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        return ChronoUnit.DAYS.between(start, today).coerceAtLeast(0L)
    }
}