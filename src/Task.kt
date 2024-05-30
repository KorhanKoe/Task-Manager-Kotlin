sealed class Task(
    title: String,
    description: String,
    deadline: Int,
    status: Status,
    var steps : Int?=null,
    var estimatedTime : Int?=null
   ) :WorkUnit(title, description,deadline, status)
{
    init {
        // Wenn nullableProperty null ist, setze sie auf einen Standardwert
        when {
            steps == null -> steps = 0
            estimatedTime == null -> estimatedTime = 0
        }
    }

        internal fun changeStatus(newstatus: Status) {
        status = newstatus
    }

    override fun prioritize(): Double {

        return super.prioritize()
    }

    fun calculateSteps(): Double {
        return when {
            steps?.let { it <= 7 } == true -> 1.0
            steps?.let { it <= 30 } == true -> 2.0
            else -> 3.0
        }
    }

    var suma = super.getSummary()

 /*   fun getSummary() :String {
        return """"
               Projekt: $title
               Beschreibung: $description
               Deadline: $deadline
               Status: $status
               
                Die Aufgabe ’Einkaufen ’ mit der Beschreibung ’Alle ben ö tigten
                Lebensmittel kaufen ’ muss bis in 7 Tagen erledigt sein . Der
                aktuelle Status ist TODO . Die Arbeitsschritte sind : Pfand
                einpacken , Zum Supermarkt fahren , Einkaufen , Nach Hause fahren ,
                Eink äufe einräumen . Es wird voraussichtlich 90 Minuten dauern .
            """.trimIndent()
    }*/
}