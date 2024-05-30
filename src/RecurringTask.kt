class RecurringTask(    title: String,
                        description: String,
                        deadline: Int,
                        status: Status,
                        var frequency : Int) :Task(title, description, deadline, status,)
{

}