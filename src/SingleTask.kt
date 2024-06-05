import java.time.LocalDate
import java.time.temporal.ChronoUnit

//Klasse SingeTask, erbt von der Task-Klasse
class SingleTask(    title: String,
                        description: String,
                        deadline: LocalDate,
                        status: Status,
                        steps : Int?=null,
                        estimatedTime : Int?=null) :Task(title, description, deadline, status, steps, estimatedTime) {
    //Gibt eine Erinnerung aus, wenn bis zu der Deadline 2 Tage verbleiben
    val reminder: Long
        get() = maxOf(
            ChronoUnit.DAYS.between(LocalDate.now(), deadline) - 2,
            0
        ) //MaxOf stellt sicher, dass der Wert für die Erinnerung nicht negativ ist

    //Soll überprüfen, wieviele Tage bis zu der Deadline verbleiben
    fun checkReminder() {
        val daysUntilDeadline = ChronoUnit.DAYS.between(LocalDate.now(), deadline)
        if (daysUntilDeadline >= 2) {     //Ist sie mehr als zwei Tage entfernt, wird eine Erinnerung ausgegeben
            println("Reminder: $daysUntilDeadline Tage bis zur Deadline!")
        } else if (daysUntilDeadline <= 2) {      //Sind es weniger als zwei Tage, wird ein Exception-Error ausgeworfen
            throw ReminderException("Die Deadline steht kurz bevor! Es sind noch $deadline bis zu der Deadline.")
        }
    }
}
