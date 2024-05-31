//Klasse Task, erbt von der Klasse WorkUnit
sealed class Task(      //Sealed wird verwendet, damit keine Objekte der Klasse Task erstellt werden können
    title: String,
    description: String,
    deadline: Int,
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
            steps?.let { it <= 7 } == true -> 1.0       //Falls steps nicht null ist, und kleiner gleich 7, ist die Priorität hoch
            steps?.let { it <= 30 } == true -> 2.0      //Falls steps nicht null ist, und kleiner gleich 30, ist die Priorität mittelmäßig
            else -> 3.0     //Ansonsten ist die Priorität gering
        }
    }

    //Die Methode der Oberklasse wird aufgerufen, um eine Zusammenfassung der allgemeinen Informationen zu erhalten
    var suma = super.getSummary()
}