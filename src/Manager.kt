class Manager ( val projects : MutableList < Project >)
{
    val todo = mutableListOf <Task >()

    fun generateToDoList(): MutableList<Task> {
        todo.clear()
        for (project in projects) {
            for (task in project.tasks) {
                if (task.status != Status.DONE) {
                    todo.add(task)
                }
            }
        }
        return todo
    }

    fun getPriorityTodo(): List<Task> {
        var highPriority = mutableListOf<Task>()
        for (task in todo) {
            if (task.priority== Priority.HIGH) {
                highPriority.add(task)
            }
        }
        return highPriority
    }

    //funktioniert nicht richtig
    fun avgTime(): Double {
        val highPriorityTasks = getPriorityTodo()
        if (highPriorityTasks.isEmpty()) {
            return 0.0
        }
        val totalTime = highPriorityTasks.sumOf { it.estimatedTime?.toDouble() ?: 0.0 }
        return totalTime / highPriorityTasks.size
        }
}
