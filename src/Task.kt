import java.time.LocalDate
//Klasse Task, erbt von der Klasse WorkUnit
sealed class Task(      //Sealed wird verwendet, damit keine Objekte der Klasse Task erstellt werden können
    title: String,
    description: String,
    deadline: LocalDate,
    status: Status,
    var steps : Int?=null,
    var estimatedTime : Int?=null
   ) :WorkUnit(title, description,deadline, status)
{

       //Der Init-Block dient als Sicherheitsvorkehrung für die nullable Eigenschaften
    init {
        when {              // Wenn eine nullable Eigenschaft null ist, setze sie auf den Standardwert
            steps == null -> steps = 0
            estimatedTime == null -> estimatedTime = 0
        }
    }
    //Funktion, die den Status ändern soll. Der Zugriff soll nur befugten im selben Modul ermöglicht werden
    internal fun changeStatus(newstatus: Status) {
        status = newstatus
    }

    //Es soll die Priorität gemessen an der Anzahl der verbleibenden Schritte ausgegeben werden
    fun calculateSteps(): Double {
        return when {
            steps?.let { it > 11} == true -> 1.0       //Aufgaben mit mehr Schritten haben eine höhere Priorität
            steps?.let { it in 5..7 }  == true -> 2.0
            else -> 3.0     //Ansonsten ist die Priorität gering
        }
    }

    //Methode für die Zuweisung von Faktoren
    fun calculateEstimatedTime(): Double {
        return when {
            estimatedTime?.let {it < 60} == true -> 1.0
            estimatedTime?.let {it in 60..180} == true -> 2.0
            else -> 3.0
        }
    }

    override fun prioritize(): Double {
        val stepsFactor = calculateSteps()
        val estimatedTimeFactor = calculateEstimatedTime()
        val factor = (stepsFactor + estimatedTimeFactor) / 2.0
        return factor
    }


    //Die Methode der Oberklasse wird aufgerufen, um eine Zusammenfassung der allgemeinen Informationen zu erhalten
    var suma = super.getSummary()
}