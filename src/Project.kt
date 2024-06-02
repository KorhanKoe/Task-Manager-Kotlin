import java.time.temporal.IsoFields
//Unterklasse Project, erbt von der Klasse WorkUnit
class Project(
    title: String,
    description: String,
    deadline: Int,
    status: Status,
    var tasks: MutableList<Task>,
) : WorkUnit (title, description, deadline,status) {
    //Get-Methode für die Ausgabe für den Bearbeitungsstands
    val progress: Double
        get() {
            val completedTasks =
                tasks.count { it.status == Status.DONE }       //Zählt, wie viele der betroffenen Aufgaben abgeschlossen sind
            val totalTasks = tasks.size     //Verwendet die Größe der Liste, um die Anzahl aller Aufgaben zu erhalten
            return if (totalTasks > 0) {        //Wenn Aufgaben existieren, wird der Prozentsatz berechnet
                (completedTasks.toDouble() / totalTasks.toDouble()) * 100
            } else {
                0.0     //Wenn keine Aufgaben existieren ist der Wert 0.0
            }
        }

    //Funktion, die den Status ändern soll. Der Zugriff soll nur befugten im selben Modul ermöglicht werden
    internal fun changeStatus(newStatus: Status) {
        status = newStatus
    }

    //Aufruf der Funktion getSummary aus der WorkUnit-Klasse, für einen String mit den wichtigsten Informationen, kein println
    var suma = super.getSummary()

    //Das hinzufügen einer Aufgabe soll genau so wie changeStatus nur befugten im selben Modul gestattet werden
    internal fun addTask(task: Task) {
        tasks.add(task)
    }

    //Methode für die Überprüfung von Deadlines einzelner Aufgaben
       fun checkTasks(today: Int) {
        for (task in tasks)        //Weist dann den aktuellen Wert der Variablen zu
            // Überprüfung der aktuellen Aufgabe
            when (task) {
                is SingleTask -> {
                    if (task.deadline == today) {
                        throw ReminderException("Deadline ist heute fällig!")
                    }
                }
                //Überprüfung der Deadline der aktuellen RecurringTask
                is RecurringTask -> {
                    if (task.deadline < today) {        //Falls die Task-Deadline kleiner ist als Heute
                        val newDeadline = today + (task.frequency ?: 0)     //Sollen die Widerholungen der Aufgabe auf heute addiert werden um die neue Deadline zu bestimmen
                        if (newDeadline <= deadline) {      //Wenn die neue Deadline vor oder gleich der Projektdeadline liegt
                            task.deadline = newDeadline     //Aktualisiere die Deadline der Aufgabe auf die neue Deadline
                        } else {
                            throw ReminderException("Neue Deadline liegt nach der Projektdeadline.")
                        }
                    }
                }
            }
        }

}