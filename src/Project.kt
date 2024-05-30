import java.time.temporal.IsoFields
//Unterklasse Project, erbt von der Klasse WorkUnit
class Project(
    title: String,
    description: String,
    deadline: Int,
    status: Status,
    var tasks: MutableList<Task>,
) : WorkUnit (title, description, deadline,status)
{
    //Get-Methode für die Ausgabe des aktuellen Standes der Bearbeitung
    val progress: Double
        get() {
            val completedTasks = tasks.count { it.status == Status.DONE }       //Zählt, wie viele der betroffenen Aufgaben abgeschlossen sind
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

    //Aufruf der Funktion getSummary aus der WorkUnit-Klasse
    var suma = super.getSummary()

    //Das hinzufügen einer Aufgabe soll genau so wie changeStatus nur befugten im selben Modul gestattet werden
    internal fun addTask (task:Task) {
        tasks.add(task)
    }

    //Methode für die Überprüfung von Deadlines einzelner Aufgaben
    fun checkTasks(today: Int) {
        // Iteriere über die Liste der Aufgaben
        for (task in tasks){
            // Überprüfe den Typ der Aufgabe
           when (task) {
               //Falls die Aufgabe Teil der Klasse SingleTask ist
               is SingleTask->{
                   // Überprüfe, ob die Erinnerung heute ist
                   if (task.reminder == today) {
                       // Gib eine Alarmmeldung aus
                       println("Alarm: Die Deadline für die Aufgabe '${task.title}' steht heute an!")
                   }
               }
               //Falls die Aufgabe Teil der Klasse RecurringTask ist
               is RecurringTask->{
                   // Überprüfe, ob die Deadline der Aufgabe in der Vergangenheit liegt
                   // und ob die neue Deadline noch vor der Projektdeadline liegt
                    if (task.deadline < today && (today + task.frequency) <= deadline) {
                        // Setze die neue Deadline
                        deadline = today + task.frequency
                   }
               }
           }
        }
    }
}