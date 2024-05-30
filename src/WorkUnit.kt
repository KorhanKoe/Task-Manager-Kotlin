open class WorkUnit(
    val title: String,
    val description: String,
    var deadline: Int,
    var status : Status
) : Prioritizable
{
    var priority : Priority ? = null
    get () = Priority . fromFactor ( this . prioritize ())

    override fun prioritize(): Double {
       val deadlineFactor = calculateDeadlineFactor()
        val statusFactor = calculateStatusFactor()
        var faktor = (deadlineFactor + statusFactor) / 2.0
        return faktor
    }

   fun calculateDeadlineFactor(): Double {
        val daysUntilDeadline = deadline
        return when {
            daysUntilDeadline <= 7 -> 1.0
            daysUntilDeadline <= 30 -> 2.0
            else -> 3.0
        }
    }
 /*   fun deadlineFactor(): Priority {
        return when {
            deadline <= 7 -> {
                println("Die Priorität ist bei $deadline Tagen HIGH")
                Priority.HIGH
            }
            deadline <= 30 -> {
                println("Die Priorität ist bei $deadline Tagen MEDIUM")
                Priority.MEDIUM
            }
            else -> {
                println("Die Priorität ist bei $deadline Tagen LOW")
                Priority.LOW
            }
        }*/

    fun calculateStatusFactor(): Double {
        return when (status) {
            Status.DOING -> 1.0
            Status.TODO -> 2.0
            Status.DONE -> 3.0
        }
    }
    //Diese Methode ist hilfreich zum logging von Informationen oder für eine strukturierte Anzeige in einem
    //GUI-Element oder einem Bericht
    open fun getSummary(): String {
        return """"
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