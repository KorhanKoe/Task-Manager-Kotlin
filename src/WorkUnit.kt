import java.time.LocalDate
import java.time.temporal.ChronoUnit

//Oberklasse WorkUnit
sealed class WorkUnit(
    val title: String,
    val description: String,
    var deadline: LocalDate,
    var status : Status
) : Prioritizable
{
    //Definiert eine Variable Priority, null sein kann

    var priority : Priority ? = null
        //Erstellt einen Getter

    get()
    {
        // Ruft die prioritize-Methode auf, um den Prioritätsfaktor zu erhalten
        val factor = prioritize()
        // Ruft die fromFactor-Methode der Klasse Priority auf, um eine Priority-Instanz basierend auf dem Faktor zu erstellen
        return Priority.fromFactor(factor)
    }

    //Methode für den Deadline-Faktor der Prioritäten
   fun calculateDeadlineFactor(today: LocalDate = LocalDate.now()): Double {
        val daysUntilDeadline = ChronoUnit.DAYS.between(today, deadline)
        return when {
            daysUntilDeadline <= 7 -> 1.0
            daysUntilDeadline <= 30 -> 2.0
            else -> 3.0
        }
    }

    //Methode für den Status-Faktor der Priorität
    fun calculateStatusFactor(): Double {
        return when (status) {
            Status.DOING -> 1.0
            Status.TODO -> 2.0
            Status.DONE -> 3.0
        }
    }

    //Methode, die den Durchschnittwert für die Priorität berechnet
    override fun prioritize(): Double {
        val deadlineFactor = calculateDeadlineFactor()
        val statusFactor = calculateStatusFactor()
        val factor = (deadlineFactor + statusFactor) / 2.0
        return factor
    }

    //Gibt einen String mit den wichtigsten Informationen zurück
    //Diese Methode ist hilfreich zum logging von Informationen oder für eine strukturierte Anzeige in einem
    //GUI-Element oder einem Bericht
    open fun getSummary(): String {
        return """
               Projekt: $title
               Beschreibung: $description
               Deadline: $deadline
               Status: $status
               
                Das Projekt ’ Wocheneinkauf ’ mit der Beschreibung ’Essen für die
                kommende Woche besorgen ’ muss bis in 10 Tagen erledigt sein . Der
                aktuelle Status ist DOING . Das Projekt beinhaltet 3 Aufgaben .
                Aktuell sind 33 ,33% abgeschlossen .
            """.trimIndent()
    }
}