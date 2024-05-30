class SingleTask(    title: String,
                        description: String,
                        deadline: Int,
                        status: Status,
                        steps : Int?=null,
                        estimatedTime : Int?=null) :Task(title, description, deadline, status, steps, estimatedTime)
{
    val reminder: Int
        get() = (deadline - 2).coerceAtLeast(0)

    fun checkReminder() {
        if (deadline > 2) {
            println("Reminder: $deadline Tage bis zur Deadline!")
        }else if (deadline <= 2) {
                throw reminderException("Die Deadline steht kurz bevor! Es sind noch $deadline bis zu der Deadline.")
            }
        }
    }

