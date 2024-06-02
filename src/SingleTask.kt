//Klasse SingeTask, erbt von der Task-Klasse
class SingleTask(    title: String,
                        description: String,
                        deadline: Int,
                        status: Status,
                        steps : Int?=null,
                        estimatedTime : Int?=null) :Task(title, description, deadline, status, steps, estimatedTime)
{
   //Gibt eine Erinnerung aus, wenn die Deadline nurnoch 2 Tage hat
    val reminder: Int
        get() = (deadline - 2).coerceAtLeast(0)     //Dieser Wert muss mindestens 0 sein, damit nur positive Werte zugelassen werden

    //Soll überprüfen, wieviele Tage bis zu der Deadline verbleiben
    fun checkReminder() {
        if (deadline >=2) {     //Ist sie mehr als zwei Tage entfernt, wird eine Erinnerung ausgegeben
            println("Reminder: $deadline Tage bis zur Deadline!")
        }else if (deadline <= 2) {      //Sind es weniger als zwei Tage, wird ein Exception-Error ausgeworfen
                throw ReminderException("Die Deadline steht kurz bevor! Es sind noch $deadline bis zu der Deadline.")
            }
        }
    }

