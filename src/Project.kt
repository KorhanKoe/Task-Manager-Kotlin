import java.time.temporal.IsoFields

class Project(
    title: String,
    description: String,
    deadline: Int,
    status: Status,
    //Mutable-List erlaubt das hinzufügen von neuen Elementen im Gegensatz zu einer normale Liste
    var tasks: MutableList<Task>,
) : WorkUnit (title, description, deadline,status)
{
    //Fortschritt der Bearbeitung eines Tasks soll in Prozent angegeben werden
    val progress: Double
        get() {
            //zählt, wie viele der betroffenen Aufgaben abgeschlossen sind
            val completedTasks = tasks.count { it.status == Status.DONE }
            //size wir verwendet, um die Anzahl aller Elemente zu erhalten.
            val totalTasks = tasks.size
            return if (totalTasks > 0) {
                //berechnet den Prozentsatz
                (completedTasks.toDouble() / totalTasks.toDouble()) * 100
            } else {
                0.0
            }
        }

    internal fun changeStatus(newStatus: Status) {
        status = newStatus
    }

    var suma = super.getSummary()

    internal fun addTask (task:Task) {
        tasks.add(task)
    }

    fun checkTasks(today: Int) {
        // Iteriere über die Liste der Aufgaben
        for (task in tasks){
            // Überprüfe den Typ der Aufgabe
           when (task) {
               is SingleTask->{
                   // Überprüfe, ob die Erinnerung heute ist
                   if (task.reminder == today) {
                       // Gib eine Alarmmeldung aus
                       println("Alarm: Die Deadline für die Aufgabe '${task.title}' steht heute an!")
                   }
               }
               is RecurringTask->{
                   // Überprüfe, ob die Deadline in der Vergangenheit liegt
                   // und ob die neue Deadline noch vor der Projektdeadline liegt
                    if (task.deadline < today && (today + task.frequency) <= deadline) {
                        // Setze die neue Deadline
                        deadline = today + task.frequency
                   }
               }
           }
        }
    }

    //Soll eine Zusammenfassung der wichtigsten Informationen eines Projekts erstellen
/*    fun getSummary(): String {
        val taskSummary = tasks.joinToString("\n") { it.getSummary() }
        return """"
               Projekt: $title
               Beschreibung: $description
               Deadline: $deadline
               Status: $status

                Das Projekt ’ Wocheneinkauf ’ mit der Beschreibung ’Essen für die
                kommende Woche besorgen ’ muss bis in 10 Tagen erledigt sein . Der
                aktuelle Status ist DOING . Das Projekt beinhaltet 3 Aufgaben .
                Aktuell sind 33 ,33% abgeschlossen .
               $taskSummary
            """.trimIndent()
    }*/



}