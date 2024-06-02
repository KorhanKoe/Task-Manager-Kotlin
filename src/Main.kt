// Erstellung von Aufgaben (Tasks) und Projekten in der Hauptmethode
fun main() {
    // Erstellen von Aufgaben
    val task1 = SingleTask("Task 1", "Beschreibung Task 1", 1, Status.TODO, 2)
    val task2 = SingleTask("Task 2", "Beschreibung Task 2", 8, Status.DOING)
    val task3 = RecurringTask("Task 3", "Beschreibung Task 3", 12, Status.DONE, 6,2,1)
    val task4 = RecurringTask("Task 4", "Beschreibung Task 4", 11, Status.DOING, 7,9,5)
    val task5 = SingleTask("Task 5", "Beschreibung Task 5", 31, Status.DOING, 9, 2)
    val task6 = SingleTask("Task 6", "Beschreibung Task 6", 3, Status.DONE, 18, 9)
    val task7 = SingleTask("Task 7", "Beschreibung Task 7", 4, Status.DOING, 14, 10)
    val task8 = SingleTask("Task 8", "Beschreibung Task 8", 32, Status.TODO, 12, 10)
    val task9 = SingleTask("Task 9", "Beschreibung Task 9", 5, Status.DOING, 18, 7)

    // Erstellen von Projekten
    val project1 = Project("Projekt 1", "Beschreibung Projekt 1", 10, Status.TODO, mutableListOf(task1, task2))
    val project2 = Project("Projekt 2", "Beschreibung Projekt 2", 12, Status.DOING, mutableListOf(task3, task4))
    val project3 = Project("Projekt 3", "Beschreibung Projekt 3", 15, Status.DONE, mutableListOf(task5, task6, task7, task8, task9))

    // Ausgabe des Deadline-Faktors
    val deadline1=task1.calculateDeadlineFactor()
    println("Aufgabe 1 Deadline-Faktor: $deadline1")
    val deadline2=task3.calculateDeadlineFactor()
    println("Aufgabe 3 Deadline-Faktor: $deadline2")

    // Ausgabe von Prioritäten mittels der Eigenschaft
    println("-------------------------------------------------------------------")
    println("Priorität der Aufgabe 2: ${task2.priority}")
    println("Priorität der Aufgabe 5: ${task5.priority}")
    println("Priorität der Aufgabe 8: ${task8.priority}")

    // Ausgabe von durchschnittlichen Prioritäten
    println("-------------------------------------------------------------------")
    println("Berechnen der durchschnittlichen Priorität Aufgabe 2: ${task2.prioritize()}")
    println("Berechnen der durchschnittlichen Priorität Aufgabe 5: ${task5.prioritize()}")
    println("Berechnen der durchschnittlichen Priorität Aufgabe 8: ${task8.prioritize()}")

    println("-------------------------------------------------------------------")
    //Hinzufügen einer neuen Aufgabe für das Projekt 2
    println("Alle Aufgaben aus Projekt 2: ")
    project2.print(project2)
    project2.addTask(task9)
    println("Nach dem Hinzufügen der Aufgabe 9: ")
    project2.print(project2)
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
    //-------------------------------------------------------------------
    //project1.checkTasks(8)    //Kommentiert wegen ausgeworfener Exception
    project2.checkTasks(10)
    project3.checkTasks(12)

    // Fortschrittsberechnung eines Gesamtprojekts
    println("-------------------------------------------------------------------")
    val taskManager = mutableListOf(task1, task2, task3, task4, task5, task6, task7, task8)
    val projectAll = Project("Projekt 4", "Beschreibung Projekt 4", 21, Status.TODO, taskManager)
    println("Der Fortschritt des Projekts beträgt: ${projectAll.progress}%")

    // Weitere Berechnungen und Ausgaben zu einzelnen Aufgaben
    println("-------------------------------------------------------------------")
    println("Der Faktor für die Arbeitsschritte beträgt für die Aufgabe 7: ${task7.calculateSteps()}")
    println("Der Faktor für die Deadline beträgt für die Aufgabe 1: ${task1.calculateDeadlineFactor()}")
    println("Der Faktor für den Stauts beträgt für das die Aufgabe 2: ${task2.calculateDeadlineFactor()}")

    // Änderung von Status für Projekte und Aufgaben
    println("-------------------------------------------------------------------")
    println("Der alte Status für das Projekt 2: ${project2.status}")
    project2.changeStatus(Status.DONE)
    println("Der neue Status für das Projekt 2: ${project2.status}")
    println("Der alte Status für die Aufgabe 4: ${task4.status}")
    task4.changeStatus(Status.DONE)
    println("Der neue Status für die Aufgabe 4: ${task4.status}")

    // Überprüfung der Erinnerung bis zur Deadline und Ausgabe
    println("-------------------------------------------------------------------")
    println("Prüfung bis zu der Deadline der Single-Task Aufgabe 2 ${task2.checkReminder()}")
    println("Prüfung bis zu der Deadline der Single-Task Aufgabe 8 ${task8.checkReminder()}")

    // Priorisierung von Aufgaben 1, 2 und 3 mithilfe der Methode prioritize() aus Task
    println("-------------------------------------------------------------------")
    println("Ausgabe der Prioritätswerte für die Aufgaben:")
    println("Priorität Aufgabe 1: ${task1.prioritize()}")
    println("Priorität Aufgabe 2: ${task2.prioritize()}")
    println("Priorität Aufgabe 3: ${task3.prioritize()}")

// Erstellung einer Liste von Projekten
    val projekte = mutableListOf(project1, project2, project3)

// Erstellung eines Managers mit den Projekten
    val manager1 = Manager(projekte)

    println("-------------------------------------------------------------------")
// Ausgabe der zu erledigenden Aufgaben, die nicht abgeschlossen sind
    println("Überprüfe alle Tasks aus den Porjekten auf den Status und füge sie der ToDo-Liste hinzu, wenn sie nicht abgeschlossen sind:")
    manager1.generateToDoList().forEach { println("Title: ${it.title}, Priority: ${it.priority}") }

    // Ausgabe einer priorisierten To Do-Liste
    println("-------------------------------------------------------------------")
    println("Generiere eine priorisierte ToDo-Liste:")
    manager1.getPriorityTodo().forEach { println("Title: ${it.title}, Priority: ${it.priority}") }

    println("-------------------------------------------------------------------")
    //Ausgabe der Berechnung von der durchschnittlichen Dauer von Aufgaben aus einem Projekt mit hoher Priorität
    println("Die Durchschnittszeit für ein Projekt mit hoher Priorität beträgt: ${manager1.avgTime()} Tage.")

/*
    Prüft die Deadline und gibt eine Fehlermeldung aus
    println("${task1.checkReminder()}")
*/
}