//Unterklasse RecurringTask, erbt von der Klasse Task
class RecurringTask(title: String,
                    description: String,
                    deadline: Int,
                    status: Status,
                    steps: Int ?=null,
                    estimatedTime: Int ?=null,
                    var frequency: Int? =null
)   :Task ( title,description, deadline, status, steps, estimatedTime)
{

}