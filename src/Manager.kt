//Klasse Manager
class Manager ( val projects : MutableList < Project >)
{
    val todo = mutableListOf <Task >()

    //Generiere eine To-Do Liste für die Klasse Manager
    fun generateToDoList(): MutableList<Task> {
        todo.clear()        //Stellt sicher, dass die Liste leer ist
        for (project in projects) {     //Durchläuft alle Elemente der Liste projects
            for (task in project.tasks) {       //Durchläuft für jedes Projekt die Aufgaben
                if (task.status != Status.DONE) {       //Filtert nach nicht erledigten Aufgaben
                    todo.add(task)      //Weist diese der Liste To-Do zu
                }
            }
        }
        return todo     //Gibt die Liste zurück
    }

    //Soll die priorisierten Aufgaben aus der Liste Filtern
    fun getPriorityTodo(): List<Task> {
        var highPriority = mutableListOf<Task>()        //Erstellt eine neue Liste mit Aufgaben die eine hohe Priorität haben
        for (task in todo) {        //Durchläuft die Aufgaben aus der Liste mit noch zu erledigenden Aufgaben
            if (task.priority== Priority.HIGH) {        //Prüft, ob die Priorität hoch ist
                highPriority.add(task)      //Falls ja, wird die Aufgabe der Liste hinzugefügt
            }
        }
        return highPriority     //Gibt die Liste mit Aufgaben einer hohen Priorität aus
    }

    //funktioniert nicht richtig
/*    fun avgTime(): Double {
        val highPriorityTasks = getPriorityTodo()
        if (highPriorityTasks.isEmpty()) {
            return 0.0
        }
        val totalTime = highPriorityTasks.sumOf { it.estimatedTime?.toDouble() ?: 0.0 }
        return totalTime / highPriorityTasks.size
        }*/
}
