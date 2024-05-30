// Erstellung von Aufgaben (Tasks) und Projekten in der Hauptmethode
fun main() {
    // Erstellen von Aufgaben
    val task1 = SingleTask("Task 1", "Beschreibung Task 1", 3, Status.TODO, 2)
    val task2 = SingleTask("Task 2", "Beschreibung Task 2", 8, Status.DOING)
    val task3 = RecurringTask("Task 3", "Beschreibung Task 3", 7, Status.TODO, 3)
    val task4 = RecurringTask("Task 4", "Beschreibung Task 4", 6, Status.DONE, 2)
    val task5 = SingleTask("Task 5", "Beschreibung Task 5", 31, Status.DOING, 9, 2)
    val task6 = SingleTask("Task 6", "Beschreibung Task 6", 3, Status.DONE)
    val task7 = SingleTask("Task 7", "Beschreibung Task 7", 4, Status.DOING, 14, 10)
    val task8 = SingleTask("Task 8", "Beschreibung Task 8", 32, Status.DONE, 1, 10)

    // Erstellen von Projekten
    val project1 = Project("Projekt 1", "Beschreibung Projekt 1", 10, Status.TODO, mutableListOf(task1, task2))
    val project2 = Project("Projekt 2", "Beschreibung Projekt 2", 12, Status.DOING, mutableListOf(task3, task4))
    val project3 = Project("Projekt 3", "Beschreibung Projekt 3", 15, Status.DONE, mutableListOf(task5, task6, task7))

    // Ausgabe von Informationen zu den Aufgaben
    println("Aufgabe 1 Deadline-Faktor: ${task1.calculateDeadlineFactor()}")
    println("Aufgabe 3 Deadline-Faktor: ${task3.calculateDeadlineFactor()}")

    // Ausgabe von Prioritäten
    println("-------------------------------------------------------------------")
    println("Priorität der Aufgabe 2: ${task2.priority}")
    println("Priorität der Aufgabe 5: ${task5.priority}")
    println("Priorität der Aufgabe 8: ${task8.priority}")

    // Ausgabe von durchschnittlichen Prioritäten
    println("-------------------------------------------------------------------")
    println("Berechnen der durchschnittlichen Priorität Aufgabe 2: ${task2.prioritize()}")
    println("Berechnen der durchschnittlichen Priorität Aufgabe 5: ${task5.prioritize()}")
    println("Berechnen der durchschnittlichen Priorität Aufgabe 8: ${task8.prioritize()}")

    // Ausgabe von Status-Faktoren
    println("-------------------------------------------------------------------")
    println("Aufgabe 2 Status-Faktor: ${task2.calculateStatusFactor()}")
    println("Aufgabe 4 Status-Faktor: ${task4.calculateStatusFactor()}")

    // Ausgabe von Gesamt-Prioritäten der Projekte
    println("-------------------------------------------------------------------")
    println("Gesamt-Priorität des ersten Projekts: ${project1.prioritize()}")
    println("Gesamt-Priorität des zweiten Projekts: ${project2.prioritize()}")
    println("Gesamt-Priorität des dritten Projekts: ${project3.prioritize()}")

    // Überprüfung von Aufgaben in Projekten
    println("-------------------------------------------------------------------")
    project1.checkTasks(8)
    project2.checkTasks(10)
    project3.checkTasks(12)

    // Fortschrittsberechnung eines Gesamtprojekts
    println("-------------------------------------------------------------------")
    val taskManager = mutableListOf(task1, task2, task3, task4, task5, task6, task7, task8)
    val projectAll = Project("Projekt 4", "Beschreibung Projekt 4", 21, Status.TODO, taskManager)
    println("Der Fortschritt des Projekts ist: ${projectAll.progress}%")
    println("Ausgabe des Prozess-Fortschritts der Aufgabe:")
    task1.checkReminder()

    // Weitere Berechnungen und Ausgaben zu einzelnen Aufgaben
    println("-------------------------------------------------------------------")
    println("Die Anzahl der benötigten Arbeitsschritte sind ${task7.calculateSteps()}")
    println("Der Faktor für die Deadline beträgt für die Aufgabe 1: ${task1.calculateDeadlineFactor()}")
    println("Der Faktor für den Stauts beträgt für das die Aufgabe: ${task2.calculateDeadlineFactor()}")

    // Änderung von Status für Projekte und Aufgaben
    println("-------------------------------------------------------------------")
    println("Ein neuer Status für das Projekt 3: ${project3.changeStatus(Status.DONE)}")
    println("Ein neuer Status für die Aufgabe 4: ${task4.changeStatus(Status.DONE)}")

    // Überprüfung der Erinnerung bis zur Deadline und Ausgabe
    println("-------------------------------------------------------------------")
    println("Prüfung bis zu der Deadline der single-Task Aufgabe 5 ${task5.checkReminder()}")
    println("Prüfung bis zu der Deadline der single-Task Aufgabe 8 ${task8.checkReminder()}")

    // Priorisierung von Aufgaben 1, 2 und 3
    println("-------------------------------------------------------------------")
    task1.prioritize()
    task2.prioritize()
    task3.prioritize()

// Erstellung einer Liste von Projekten
    val projekte = mutableListOf(project1, project2, project3)

// Erstellung eines Managers mit den Projekten
    val manager1 = Manager(projekte)

// Ausgabe der zu erledigenden Aufgaben, die nicht abgeschlossen sind
    println("Überprüfe alle Tasks aus den Porjekten auf den Status und füge sie der ToDo-Liste hinzu, wenn sie nicht abgeschlossen sind:")
    manager1.generateToDoList().forEach { println(it.title) }

    // Ausgabe einer priorisierten To Do-Liste
    println("-------------------------------------------------------------------")
    println("Generiere eine priorisierte ToDo-Liste:")
    manager1.getPriorityTodo().forEach { println(it.title) }

    /*
    println("Die Durchschnittszeit für ein Projekt: ${manager1.avgTime()}")
*/
}