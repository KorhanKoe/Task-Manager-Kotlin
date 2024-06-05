import java.time.LocalDate

//Unterklasse RecurringTask, erbt von der Klasse Task
class RecurringTask(title: String,
                    description: String,
                    deadline: LocalDate,
                    status: Status,
                    steps: Int ?=null,
                    estimatedTime: Int ?=null,
                    var frequency: Int? =null
)   :Task ( title,description, deadline, status, steps, estimatedTime)
{
    //Init-Block für die zusätzliche nullable-Eigenschaft
    init {
        when {
            frequency == null -> frequency == 0
        }
    }
}